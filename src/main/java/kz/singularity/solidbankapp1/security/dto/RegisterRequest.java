package kz.singularity.solidbankapp1.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Sign up request")
public class RegisterRequest {

    @Schema(description = "Username")
    @Size(min=2, max=50, message = "2 <= name <= 50")
    @NotBlank(message = "cannot be empty")
    private String username;

    @Schema(description = "password")
    @Size(max=255, message = "no more than 255 symbols")
    private String password;
}
