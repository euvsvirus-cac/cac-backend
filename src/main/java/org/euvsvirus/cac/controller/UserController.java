package org.euvsvirus.cac.controller;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.model.request.LoginRequest;
import org.euvsvirus.cac.model.request.UserTeamRequest;
import org.euvsvirus.cac.model.response.JWTTokenResponse;
import org.euvsvirus.cac.service.CacUserLoginService;
import org.euvsvirus.cac.service.CacUserTeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private final CacUserLoginService cacUserLoginService;

    private final CacUserTeamService cacUserTeamService;

    public UserController(CacUserLoginService cacUserLoginService, CacUserTeamService cacUserTeamService) {
        this.cacUserLoginService = cacUserLoginService;
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

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTTokenResponse> login(@RequestBody LoginRequest login) {

        return new ResponseEntity<>(cacUserLoginService.login(login.getEmail(), login.getPassword()), HttpStatus.OK);
    }

    @PostMapping(value = "/addUserToTeam", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUsertoTeam(@RequestBody UserTeamRequest userTeamRequest) {
        return new ResponseEntity<>(cacUserTeamService.addUserToTeam(userTeamRequest.getUserId(), userTeamRequest.getTeamId()), HttpStatus.OK);
    }
}
