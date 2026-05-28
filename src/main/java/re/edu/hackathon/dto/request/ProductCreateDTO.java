package re.edu.hackathon.dto.request;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class ProductCreateDTO {
    @NotNull(message = "ten khong duoc de trong")
    private String name;
    @NotNull(message = "chi tiet khong duoc de trong")
    private String details;
    @NotNull(message = "so luong khong duoc de trong")
    @Min(value = 1,message = "So luong phai lon hon 0")
    private Integer stock;
    @NotNull(message = "Gia khong duoc de trong")
    @Positive(message = "Gia phai lon hon 0")
    private Double price;
    @NotNull(message = "danh muc khong duoc de trong")
    private String category;

    private String imageUrl;
}
