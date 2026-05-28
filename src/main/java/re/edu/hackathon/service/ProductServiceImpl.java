package re.edu.hackathon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import re.edu.hackathon.common.ApiResponse;
import re.edu.hackathon.dto.request.ProductCreateDTO;
import re.edu.hackathon.dto.request.ProductUpdateDTO;
import re.edu.hackathon.dto.response.ProductResponeDTO;
import re.edu.hackathon.entity.Product;
import re.edu.hackathon.exception.NotFoundException;
import re.edu.hackathon.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK,
                "Lay danh sach product thanh cong",
                productRepository.findAll()
                .stream()
                .map(this::toResponse)
                        .toList()
        ));
    }

    @Override
    public ProductResponeDTO toResponse(Product product) {
        return ProductResponeDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .details(product.getDetails())
                .price(product.getPrice())
                .sku(product.getSku())
                .status(product.getStatus())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Override
    public ResponseEntity<?> addProduct(ProductCreateDTO productCreateDTO) {
        Product newProduct = Product.builder()
                .name(productCreateDTO.getName())
                .category(productCreateDTO.getCategory())
                .details(productCreateDTO.getDetails())
                .price(productCreateDTO.getPrice())
                .stock(productCreateDTO.getStock())
                .imageUrl(productCreateDTO.getImageUrl())
                .sku(generateSku())
                .status("ACTIVE")
                .createdAt(LocalDateTime.now())
                .build();
        Product saveProduct = productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(
                HttpStatus.CREATED,
                "Them product thanh cong",
                toResponse(saveProduct)
        ));
    }

    @Override
    public ResponseEntity<?> updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        Product product = searchById(id);
        updateProductFields(product, productUpdateDTO);
        Product saveProduct = productRepository.save(product);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK,
                "Cap nhat product thanh cong",
                toResponse(saveProduct)
        ));
    }

    @Override
    public ResponseEntity<?> patchProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        Product product = searchById(id);
        updateProductFields(product, productUpdateDTO);
        Product saveProduct = productRepository.save(product);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK,
                "Cap nhat mot phan product thanh cong",
                toResponse(saveProduct)
        ));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        Product product = searchById(id);
        ProductResponeDTO productResponeDTO = toResponse(product);
        productRepository.delete(product);
        return ResponseEntity.ok(ApiResponse.success(
                HttpStatus.OK,
                "Xoa product thanh cong",
                productResponeDTO
        ));
    }

    @Override
    public Product searchById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Khong ton tai Product co id " + id));
    }

    private void updateProductFields(Product product, ProductUpdateDTO productUpdateDTO) {
        if (productUpdateDTO.getName() != null) {
            product.setName(productUpdateDTO.getName());
        }
        if (productUpdateDTO.getDetails() != null) {
            product.setDetails(productUpdateDTO.getDetails());
        }
        if (productUpdateDTO.getStock() != null) {
            product.setStock(productUpdateDTO.getStock());
        }
        if (productUpdateDTO.getPrice() != null) {
            product.setPrice(productUpdateDTO.getPrice());
        }
        if (productUpdateDTO.getCategory() != null) {
            product.setCategory(productUpdateDTO.getCategory());
        }
        if (productUpdateDTO.getImageUrl() != null) {
            product.setImageUrl(productUpdateDTO.getImageUrl());
        }
        if (productUpdateDTO.getStatus() != null) {
            product.setStatus(productUpdateDTO.getStatus());
        }
    }

    private String generateSku() {
        return "SKU-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
