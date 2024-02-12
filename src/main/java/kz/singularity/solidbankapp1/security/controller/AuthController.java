package kz.singularity.solidbankapp1.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kz.singularity.solidbankapp1.security.dto.AuthenticationRequest;
import kz.singularity.solidbankapp1.security.dto.JwtAuthenticationResponse;
import kz.singularity.solidbankapp1.security.dto.RegisterRequest;
import kz.singularity.solidbankapp1.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name="Authentication controller")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "registration")
    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @Operation(summary = "authentication")
    @PostMapping("/authenticate")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody @Valid AuthenticationRequest request) {
   //     try {
            return ResponseEntity.ok(authenticationService.signIn(request));
/*        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        }*/

        // return ResponseEntity.ok(authenticationService.signIn(request));


    }
}
