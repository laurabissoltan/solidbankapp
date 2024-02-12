package kz.singularity.solidbankapp1.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response with access token")
public class JwtAuthenticationResponse {
    @Schema(description = "Access token")
    private String token;
}
