package org.euvsvirus.cac.controller;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.model.request.CreateUserRequest;
import org.euvsvirus.cac.model.request.LoginRequest;
import org.euvsvirus.cac.model.request.UserTeamRequest;
import org.euvsvirus.cac.model.response.JWTTokenResponse;
import org.euvsvirus.cac.service.CacUserDetailsService;
import org.euvsvirus.cac.service.CacUserService;
import org.euvsvirus.cac.service.CacUserTeamService;
import org.euvsvirus.cac.service.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private final CacUserDetailsService cacUserDetailsService;

    private final CacUserTeamService cacUserTeamService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    private final CacUserService cacUserService;

    public UserController(CacUserTeamService cacUserTeamService, CacUserDetailsService cacUserDetailsService, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, CacUserService cacUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.cacUserDetailsService = cacUserDetailsService;
        this.cacUserService = cacUserService;
        this.cacUserTeamService = cacUserTeamService;

    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public User me() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JWTTokenResponse> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTTokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(cacUserService.createUser(createUserRequest));
    }

    private Authentication authenticate(String username, String password) throws Exception {

        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "/addUserToTeam", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUsertoTeam(@RequestBody UserTeamRequest userTeamRequest) {
        return new ResponseEntity<>(cacUserTeamService.addUserToTeam(userTeamRequest.getUserId(), userTeamRequest.getTeamId()), HttpStatus.OK);
    }
}
