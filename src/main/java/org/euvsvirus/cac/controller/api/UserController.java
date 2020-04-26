package org.euvsvirus.cac.controller.api;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.model.request.AddSkillRequest;
import org.euvsvirus.cac.model.request.UpdateStatusRequest;
import org.euvsvirus.cac.service.CacUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nils Knudsen
 * @since 25.04.20
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final CacUserService cacUserService;

    public UserController(CacUserService cacUserService) {
        this.cacUserService = cacUserService;
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public User me() {
        return cacUserService.getCurrentUser();
    }

    @PutMapping(value = "/me/status")
    public void updateStatus(@RequestBody UpdateStatusRequest request) {
        cacUserService.updateStatus(request.isAvailable());
    }

    @PostMapping(value = "/me/skills")
    public void addSkill(@RequestBody AddSkillRequest request) {
        cacUserService.addSkill(request.getName(), request.getLevel());
    }

}
