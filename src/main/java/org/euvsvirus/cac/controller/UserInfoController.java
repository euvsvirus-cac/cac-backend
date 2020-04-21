package org.euvsvirus.cac.controller;

import org.euvsvirus.cac.error.exception.ValidationException;
import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController {

    private final UserRepository userRepository;

    public UserInfoController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public Boolean create(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        if (userRepository.existsByEmail(email)){

            throw new ValidationException("Username already existed");

        }

        String password = body.get("password");
        String encodedPassword = new BCryptPasswordEncoder().encode(password);

        User user = new User();
        user.setFirstName("Marvin");
        user.setLastName("Knoll");
        user.setEmail(email);
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return true;
    }

}