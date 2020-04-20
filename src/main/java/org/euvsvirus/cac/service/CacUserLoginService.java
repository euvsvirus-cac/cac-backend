package org.euvsvirus.cac.service;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.repository.UserRepository;
import org.euvsvirus.cac.response.JWTTokenResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author Nils Knudsen
 * @since 20.04.20
 **/
@Service
public class CacUserLoginService {


    private UserRepository accountRepository;
    private JwtTokenService jwtTokenService;
    private PasswordEncoder passwordEncoder;

    public CacUserLoginService(UserRepository accountRepository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public JWTTokenResponse generateJWTToken(String email, String password) {
        User user = accountRepository.findByEmail(email);
        if(passwordEncoder.matches(password, user.getPassword())) {
            return new JWTTokenResponse(jwtTokenService.generateToken(email));
        } else {
            throw new EntityNotFoundException("Account not found");
        }
    }

}
