package org.euvsvirus.cac.controller.api;

import org.euvsvirus.cac.model.UserTeam;
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

    public UserController(CacUserTeamService cacUserTeamService) {
        this.cacUserTeamService = cacUserTeamService;
    }

    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserTeam>> getTeamsForUser(@RequestParam String userId) {
        return new ResponseEntity<>(cacUserTeamService.findTeamsForUser(userId), HttpStatus.OK);
    }

}
