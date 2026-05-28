package re.edu.hackathon.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class ProductResponeDTO {
    private Long id;
    private String name;
    private String sku;
    private String details;
    private Integer stock;
    private Double price;
    private String category;
    private String imageUrl;
    private String status;
    private LocalDateTime createdAt;
}
