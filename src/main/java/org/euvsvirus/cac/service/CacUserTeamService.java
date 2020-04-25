package org.euvsvirus.cac.service;

import org.euvsvirus.cac.model.Team;
import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.model.UserTeam;
import org.euvsvirus.cac.model.UserTeamKey;
import org.euvsvirus.cac.repository.TeamRepository;
import org.euvsvirus.cac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
@Service
public class CacUserTeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public CacUserTeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public Boolean addUserToTeam(String userId, String teamId) {

        try {
            Optional<Team> team = teamRepository.findById(teamId);
            Optional<User> user = userRepository.findById(userId);
            if (team.isPresent() && user.isPresent()) {

                UserTeamKey userTeamKey = new UserTeamKey();
                userTeamKey.setUserId(userId);
                userTeamKey.setTeamId(teamId);

                UserTeam userTeam = new UserTeam();
                userTeam.setTeam(team.get());
                userTeam.setUser(user.get());

                user.get().getTeams().add(userTeam);
                team.get().getUsers().add(userTeam);

                userRepository.save(user.get());
                teamRepository.save(team.get());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Set<UserTeam> findUsersByTeamId(String teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        return team.map(Team::getUsers).orElse(null);
    }

    public Set<UserTeam> findTeamsForUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getTeams).orElse(null);
    }
}
