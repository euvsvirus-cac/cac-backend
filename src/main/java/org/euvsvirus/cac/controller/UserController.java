package org.euvsvirus.cac.controller;

import org.euvsvirus.cac.model.Login;
import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.response.JWTTokenResponse;
import org.euvsvirus.cac.service.CacUserLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    CacUserLoginService cacUserLoginService;

    public UserController(CacUserLoginService cacUserLoginService) {
        this.cacUserLoginService = cacUserLoginService;
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public User me() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTTokenResponse> login(@RequestBody Login login) {

        return new ResponseEntity<>(cacUserLoginService.generateJWTToken(login.getEmail(), login.getPassword()), HttpStatus.OK);
    }
}
