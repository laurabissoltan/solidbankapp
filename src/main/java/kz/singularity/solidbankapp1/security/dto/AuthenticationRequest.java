package kz.singularity.solidbankapp1.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Authentication request")
public class AuthenticationRequest {
    @Schema(description = "Username")
    @Size(min=2, max=50, message = "The length should be between 2 and 50 symbols")
    @NotBlank(message = "Not empty")
    private String username;

    @Schema(description = "Password")
    @Size(max=255, message = "No more than 255 symbols")
    @NotBlank(message = "Not empty")
    private String password;
}
