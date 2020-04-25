package org.euvsvirus.cac.controller.api;

import org.euvsvirus.cac.model.request.CreateUserRequest;
import org.euvsvirus.cac.model.request.LoginRequest;
import org.euvsvirus.cac.model.response.JWTTokenResponse;
import org.euvsvirus.cac.service.AuthenticationService;
import org.euvsvirus.cac.service.CacUserDetailsService;
import org.euvsvirus.cac.service.CacUserService;
import org.euvsvirus.cac.service.JwtTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nils Knudsen
 * @since 25.04.20
 **/
@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginController {

    private final JwtTokenService jwtTokenService;
    private final AuthenticationService authenticationService;
    private final CacUserService cacUserService;
    private final CacUserDetailsService cacUserDetailsService;

    public LoginController(JwtTokenService jwtTokenService, AuthenticationService authenticationService, CacUserService cacUserService, CacUserDetailsService cacUserDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.authenticationService = authenticationService;
        this.cacUserService = cacUserService;
        this.cacUserDetailsService = cacUserDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = cacUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTTokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(cacUserService.createUser(createUserRequest));
    }

}
