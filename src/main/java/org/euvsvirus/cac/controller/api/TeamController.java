package org.euvsvirus.cac.controller.api;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.model.request.UserTeamRequest;
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
@RequestMapping("/api/team")
public class TeamController {

    private final CacUserTeamService cacUserTeamService;
    private final CacUserService cacUserService;

    public TeamController(CacUserTeamService cacUserTeamService, CacUserService cacUserService) {
        this.cacUserTeamService = cacUserTeamService;
        this.cacUserService = cacUserService;
    }

    /**
     * TODO: This will be the entry point for the 'My Team' card
     * with filter options etc.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> myTeam() {
        final String teamId = cacUserService.getCurrentUser().getTeamId();

        if (teamId == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cacUserTeamService.findUsersByTeamId(teamId));
    }

    /**
     * TODO: Only allow access to the own team.
     */
    @GetMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<User>> getUsersForTeam(@RequestParam String teamId) {
        return new ResponseEntity<>(cacUserTeamService.findUsersByTeamId(teamId), HttpStatus.OK);
    }

    /**
     * TODO: Only allow users to join a team with an invitation link / code.
     * TODO: Use userId from SecurityContext.
     */
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUserToTeam(@RequestBody UserTeamRequest userTeamRequest) {
        cacUserService.addUserToTeam(userTeamRequest.getUserId(), userTeamRequest.getTeamId());
    }

}
