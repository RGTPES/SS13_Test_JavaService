package re.edu.hackathon.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductUpdateDTO {
    private String name;
    private String details;
    @Min(value = 1, message = "So luong phai lon hon 0")
    private Integer stock;
    @Positive(message = "Gia phai lon hon 0")
    private Double price;
    private String category;
    private String imageUrl;
    private String status;
}
