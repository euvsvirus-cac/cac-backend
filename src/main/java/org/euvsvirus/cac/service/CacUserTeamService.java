package org.euvsvirus.cac.service;

import org.euvsvirus.cac.model.*;
import org.euvsvirus.cac.model.response.MyTeamResponse;
import org.euvsvirus.cac.repository.SkillRepository;
import org.euvsvirus.cac.repository.TeamRepository;
import org.euvsvirus.cac.repository.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
@Service
public class CacUserTeamService {

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    private final SkillRepository skillRepository;

    public CacUserTeamService(UserRepository userRepository, TeamRepository teamRepository, SkillRepository skillRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.skillRepository = skillRepository;
    }

    public Set<User> findUsersByTeamId(String teamId) {
        return userRepository.findAllByTeamId(teamId);
    }

    public Set<Skill> getTeamSkills() {
        return getTeamSkills(null);
    }

    public Set<Skill> getTeamSkills(@Nullable String filter) {
        final String teamId = CacUserService.getCurrentUser().getTeamId();

        Set<Skill> skills;
        if (StringUtils.isEmpty(filter)) {
            skills = skillRepository.findAllByTeamId(teamId);
        } else {
            skills = skillRepository.findAllByTeamIdAndNameStartsWith(teamId, filter);
        }

        return skills;
    }

    @Transactional
    public MyTeamResponse getMyTeam() {
        final String teamId = CacUserService.getCurrentUser().getTeamId();

        final Team team = teamRepository.findById(teamId).get();
        final Set<User> users = userRepository.findAllByTeamId(teamId);

        MyTeamResponse myTeamResponse = new MyTeamResponse();
        myTeamResponse.setTeam(team);
        myTeamResponse.setUsers(users);
        myTeamResponse.setSkills(getTeamSkills());
        return myTeamResponse;
    }

}
