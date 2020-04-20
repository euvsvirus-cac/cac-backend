package org.euvsvirus.cac.service;

import org.euvsvirus.cac.model.User;
import org.euvsvirus.cac.repository.UserRepository;
import org.euvsvirus.cac.response.JWTTokenResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Nils Knudsen
 * @since 20.04.20
 **/
@Service
public class CacUserLoginService {

    private final UserRepository accountRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    public CacUserLoginService(UserRepository accountRepository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public JWTTokenResponse login(String email, String password) {
        User user = accountRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist.");
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            return new JWTTokenResponse(jwtTokenService.generateToken(email));
        } else {
            throw new BadCredentialsException("Wrong.");
        }
    }

}
