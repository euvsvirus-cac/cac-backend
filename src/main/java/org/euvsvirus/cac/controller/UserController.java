package org.euvsvirus.cac.controller;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.service.CacUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private final CacUserService cacUserService;

    public UserController(CacUserService cacUserService) {
        this.cacUserService = cacUserService;
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public User me() {
        return cacUserService.getCurrentUser();
    }

}
