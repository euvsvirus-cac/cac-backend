package org.euvsvirus.cac.controller;

import org.euvsvirus.cac.service.*;


public class UserController {

    private final CacUserTeamService cacUserTeamService;

    private final CacUserDetailsService cacUserDetailsService;

    private final AuthenticationService authenticationService;

    private final JwtTokenService jwtTokenService;

    private final CacUserService cacUserService;

    public UserController(CacUserTeamService cacUserTeamService, CacUserDetailsService cacUserDetailsService, AuthenticationService authenticationService, JwtTokenService jwtTokenService, CacUserService cacUserService) {
        this.cacUserTeamService = cacUserTeamService;
        this.authenticationService = authenticationService;
        this.jwtTokenService = jwtTokenService;
        this.cacUserDetailsService = cacUserDetailsService;
        this.cacUserService = cacUserService;

    }


}
