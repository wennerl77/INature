package com.ifmg.server.apiServer.controller;

import com.ifmg.server.apiServer.dto.LoginResponseDTO;
import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.UsersDTO;
import com.ifmg.server.apiServer.model.user.UserRole;
import com.ifmg.server.apiServer.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UsersDTO usersDTO) {
        LoginResponseDTO loginResponseDTO = authenticationService.login(usersDTO);
        return loginResponseDTO == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponsePatternDTO> register(@RequestBody UsersDTO usersDTO) {
        ResponsePatternDTO responsePatternDTO = authenticationService.register(usersDTO, UserRole.USER);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO): ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @PostMapping("/adminRegister")
    public ResponseEntity<ResponsePatternDTO> adminRegister(@RequestBody UsersDTO usersDTO) {
        ResponsePatternDTO responsePatternDTO = authenticationService.register(usersDTO, UserRole.ADMIN);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }
}
