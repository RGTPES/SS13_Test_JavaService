package re.edu.hackathon.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class ApiResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
    private Object error;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return ApiResponse.<T>builder()
                .statusCode(status.value())
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static ApiResponse<Object> error(HttpStatus status, String message, Object error) {
        return ApiResponse.builder()
                .statusCode(status.value())
                .message(message)
                .error(error)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
