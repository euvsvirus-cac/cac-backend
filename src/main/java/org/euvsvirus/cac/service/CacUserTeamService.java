package org.euvsvirus.cac.service;

import org.euvsvirus.cac.model.Team;
import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.repository.TeamRepository;
import org.euvsvirus.cac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
@Service
public class CacUserTeamService {

    TeamRepository teamRepository;

    UserRepository userRepository;

    public CacUserTeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public String addUserToTeam(String userId, String teamId) {

        Optional<Team> team = teamRepository.findById(teamId);
        Optional<User> user = userRepository.findById(userId);
        /*if (team.isPresent()) {

team.get().getUsers().add()

            if (user.isPresent()) {
                user.get().setTeams(team.get());
                userRepository.save(user.get());
            }
        }*/
        return "true";
    }
}
