package com.ifmg.server.apiServer.service;

import com.ifmg.server.apiServer.dto.LoginResponseDTO;
import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.UsersDTO;
import com.ifmg.server.apiServer.infra.security.TokenService;
import com.ifmg.server.apiServer.model.user.UserRole;
import com.ifmg.server.apiServer.model.user.Users;
import com.ifmg.server.apiServer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository repository;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(UsersDTO usersDTO) {
        if (usersDTO == null || usersDTO.email() == null || usersDTO.password() == null) {
            return null;
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(usersDTO.email().trim(), usersDTO.password().trim());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((Users) auth.getPrincipal());

        System.out.println("AUTENTICADO");
        return new LoginResponseDTO(token);
    }

    public ResponsePatternDTO register(UsersDTO usersDTO, UserRole userRole) {
        if (usersDTO == null) return new ResponsePatternDTO("user is null", 400);
        if (this.repository.findByEmail(usersDTO.email()) != null) return new ResponsePatternDTO("User already exists", 400);
        if (usersDTO.name() == null || usersDTO.name().isBlank()
                || usersDTO.email() == null || usersDTO.email().isBlank()
                || usersDTO.password() == null || usersDTO.password().isBlank()) {
            return new ResponsePatternDTO("Lack of information", 400);
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(usersDTO.password().trim());

        Users users = new Users(usersDTO.name().trim(), usersDTO.email().trim(), encryptedPassword, usersDTO.city() == null ? null : usersDTO.city().trim(), userRole);;

        this.repository.save(users);

        return new ResponsePatternDTO("Registration completed successfully", 200);
    }
}
