package org.euvsvirus.cac.service;

import org.euvsvirus.cac.error.exception.ValidationException;
import org.euvsvirus.cac.model.*;
import org.euvsvirus.cac.model.response.MyTeamResponse;
import org.euvsvirus.cac.repository.SkillRepository;
import org.euvsvirus.cac.repository.TeamRepository;
import org.euvsvirus.cac.repository.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
@Service
public class CacUserTeamService {

    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    private final SkillRepository skillRepository;

    private final CacUserService cacUserService;

    public CacUserTeamService(UserRepository userRepository, TeamRepository teamRepository, SkillRepository skillRepository, CacUserService cacUserService) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.skillRepository = skillRepository;
        this.cacUserService = cacUserService;
    }

    public List<User> findUsersByTeamId(User currentUser, String teamId) {
        if(currentUser.getTeamId().equals(teamId)) {
            return userRepository.findAllByTeamIdOrderById(teamId);
        } else {
            throw new ValidationException("You're not a member of the team you requested for");
        }
    }

    public List<Skill> getTeamSkills() {
        return getTeamSkills(null);
    }

    public List<Skill> getTeamSkills(@Nullable String filter) {
        final String teamId = CacUserService.getCurrentUser().getTeamId();

        List<Skill> skills;
        if (StringUtils.isEmpty(filter)) {
            skills = skillRepository.findAllByTeamIdOrderById(teamId);
        } else {
            skills = skillRepository.findAllByTeamIdAndNameStartsWithOrderByNameAsc(teamId, filter);
        }

        return skills;
    }

    @Transactional
    public MyTeamResponse getMyTeam(@Nullable String filter) {
        final String teamId = CacUserService.getCurrentUser().getTeamId();

        if (teamId == null) {
            return null;
        }

        final Team team = teamRepository.findById(teamId).get();
        List<User> users = userRepository.findAllByTeamIdOrderById(teamId);

        // Filter users by skill
        if (!StringUtils.isEmpty(filter)) {
            users = users.stream()
                    .filter(u -> u.getSkills().stream().anyMatch(s -> s.getSkill().getName().startsWith(filter)))
                    .collect(Collectors.toList());
        }

        MyTeamResponse myTeamResponse = new MyTeamResponse();
        myTeamResponse.setTeam(team);
        myTeamResponse.setUsers(users);
        // Unfiltered skills in the skill list
        myTeamResponse.setSkills(getTeamSkills());
        return myTeamResponse;
    }

}
