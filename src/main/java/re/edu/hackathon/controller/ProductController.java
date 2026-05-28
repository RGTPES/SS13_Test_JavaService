package re.edu.hackathon.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.hackathon.dto.request.ProductCreateDTO;
import re.edu.hackathon.dto.request.ProductUpdateDTO;
import re.edu.hackathon.service.ProductServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        return productService.addProduct(productCreateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO productUpdateDTO
    ) {
        return productService.updateProduct(id, productUpdateDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO productUpdateDTO
    ) {
        return productService.patchProduct(id, productUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
