package org.euvsvirus.cac.service;

import org.euvsvirus.cac.model.*;
import org.euvsvirus.cac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
@Service
public class CacUserTeamService {

    private final UserRepository userRepository;

    public CacUserTeamService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Set<User> findUsersByTeamId(String teamId) {
        return userRepository.findAllByTeamId(teamId);
    }

}
