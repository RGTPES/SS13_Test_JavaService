package re.edu.hackathon.service;

import org.springframework.http.ResponseEntity;
import re.edu.hackathon.dto.request.ProductCreateDTO;
import re.edu.hackathon.dto.request.ProductUpdateDTO;
import re.edu.hackathon.dto.response.ProductResponeDTO;
import re.edu.hackathon.entity.Product;

public interface IProductService {
    ResponseEntity<?> getAll();

    ProductResponeDTO toResponse(Product product);

    ResponseEntity<?> addProduct(ProductCreateDTO productCreateDTO);

    ResponseEntity<?> updateProduct(Long id, ProductUpdateDTO productUpdateDTO);

    ResponseEntity<?> patchProduct(Long id, ProductUpdateDTO productUpdateDTO);

    ResponseEntity<?> deleteProduct(Long id);

    Product searchById(Long id);
}
