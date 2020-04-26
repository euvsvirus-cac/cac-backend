package org.euvsvirus.cac.controller.api;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.model.UserTeam;
import org.euvsvirus.cac.model.request.UpdateStatusRequest;
import org.euvsvirus.cac.service.CacUserService;
import org.euvsvirus.cac.service.CacUserTeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Nils Knudsen
 * @since 25.04.20
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final CacUserTeamService cacUserTeamService;
    private final CacUserService cacUserService;

    public UserController(CacUserService cacUserService, CacUserTeamService cacUserTeamService) {
        this.cacUserTeamService = cacUserTeamService;
        this.cacUserService = cacUserService;
    }

    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserTeam>> getTeamsForUser(@RequestParam String userId) {
        return new ResponseEntity<>(cacUserTeamService.findTeamsForUser(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public User me() {
        return cacUserService.getCurrentUser();
    }

    @PutMapping(value = "/me/status")
    public void updateStatus(@RequestBody UpdateStatusRequest request) {
        cacUserService.updateStatus(request.isAvailable());
    }

}
