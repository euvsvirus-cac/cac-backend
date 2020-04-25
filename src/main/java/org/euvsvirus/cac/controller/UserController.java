package org.euvsvirus.cac.controller;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.model.request.CreateUserRequest;
import org.euvsvirus.cac.model.request.LoginRequest;
import org.euvsvirus.cac.model.response.JWTTokenResponse;
import org.euvsvirus.cac.service.CacUserDetailsService;
import org.euvsvirus.cac.service.CacUserService;
import org.euvsvirus.cac.service.JwtTokenService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private final CacUserDetailsService cacUserDetailsService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    private final CacUserService cacUserService;

    public UserController(CacUserDetailsService cacUserDetailsService, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, CacUserService cacUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.cacUserDetailsService = cacUserDetailsService;
        this.cacUserService = cacUserService;
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public User me() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = cacUserDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTTokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(cacUserService.createUser(createUserRequest));
    }


    private void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

}
