package org.euvsvirus.cac.service;

import org.euvsvirus.cac.error.exception.ValidationException;
import org.euvsvirus.cac.model.*;
import org.euvsvirus.cac.model.request.CreateUserRequest;
import org.euvsvirus.cac.repository.SkillRepository;
import org.euvsvirus.cac.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Nils Knudsen
 * @since 22.04.20
 **/
@Service
public class CacUserService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public CacUserService(SkillRepository skillRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public boolean createUser(CreateUserRequest createUserRequest){

        String email = createUserRequest.getEmail();
        if (userRepository.existsByEmail(email)){
            throw new ValidationException("Username already existed");
        }
        String password = createUserRequest.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        User user = new User();
        user.setEmail(email);
        user.setFullName(createUserRequest.getFullName());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return true;
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    @Transactional
    public void updateStatus(boolean available) {
        final String id = getCurrentUser().getId();
        userRepository.updateStatus(id, available);
    }

    @Transactional
    public void addSkill(String skillName, Level level) {
        final String userId = getCurrentUser().getId();

        skillName = skillName.trim().toLowerCase().replace(' ', '-');
        if (skillName.isBlank()) {
            throw new IllegalArgumentException("Skill name is required.");
        }

        Skill skill = skillRepository.findByName(skillName);
        if (skill == null) {
            Skill newSkill = new Skill(skillName);
            skill = skillRepository.save(newSkill);
        }

        skillRepository.addUserSkill(userId, skill.getId(), level.toString());
    }

}
