package org.euvsvirus.cac.service;

import org.euvsvirus.cac.model.*;
import org.euvsvirus.cac.repository.TeamRepository;
import org.euvsvirus.cac.repository.TeamUserRepository;
import org.euvsvirus.cac.repository.UserRepository;
import org.euvsvirus.cac.repository.UserTeamRepository;
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
    private final UserTeamRepository userTeamRepository;
    private final TeamUserRepository teamUserRepository;

    public CacUserTeamService(TeamRepository teamRepository, UserRepository userRepository, UserTeamRepository userTeamRepository, TeamUserRepository teamUserRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.userTeamRepository = userTeamRepository;
        this.teamUserRepository = teamUserRepository;
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
                userTeam.setId(userTeamKey);
                userTeam.setTeam(team.get());
                userTeam.setUser(user.get());

                userTeamRepository.save(userTeam);
                user.get().getTeams().add(userTeam);

                TeamUser teamUser = new TeamUser();
                teamUser.setId(userTeamKey);
                teamUser.setTeam(team.get());
                teamUser.setUser(user.get());
                teamUserRepository.save(teamUser);

                teamRepository.save(team.get());
                userRepository.save(user.get());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public Set<TeamUser> findUsersByTeamId(String teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        return team.map(Team::getUsers).orElse(null);
    }

    public Set<UserTeam> findTeamsForUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getTeams).orElse(null);
    }
}
