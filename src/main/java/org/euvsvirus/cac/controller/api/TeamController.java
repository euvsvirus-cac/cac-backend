package org.euvsvirus.cac.controller.api;

import org.euvsvirus.cac.model.TeamUser;
import org.euvsvirus.cac.model.request.UserTeamRequest;
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
@RequestMapping("/api/team")
public class TeamController {

    private final CacUserTeamService cacUserTeamService;
    public TeamController(CacUserTeamService cacUserTeamService) {

        this.cacUserTeamService = cacUserTeamService;
    }

    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TeamUser>> getUsersForTeam(@RequestParam String teamId) {
        return new ResponseEntity<>(cacUserTeamService.findUsersByTeamId(teamId), HttpStatus.OK);
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> addUsertoTeam(@RequestBody UserTeamRequest userTeamRequest) {
        return new ResponseEntity<>(cacUserTeamService.addUserToTeam(userTeamRequest.getUserId(), userTeamRequest.getTeamId()), HttpStatus.OK);
    }

}
