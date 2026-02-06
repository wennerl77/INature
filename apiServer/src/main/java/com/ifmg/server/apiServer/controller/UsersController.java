package com.ifmg.server.apiServer.controller;

import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.UsersDTO;
import com.ifmg.server.apiServer.dto.UsersScoreDTO;
import com.ifmg.server.apiServer.dto.UsersTableDTO;
import com.ifmg.server.apiServer.model.user.Users;
import com.ifmg.server.apiServer.repository.UsersRepository;
import com.ifmg.server.apiServer.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UsersTableDTO>> getAll() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<UsersDTO> getByEmail(@RequestParam String email) {
        UsersDTO usersDTO = usersService.getByEmail(email);
        return usersDTO == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok(usersDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponsePatternDTO> update(@RequestBody UsersDTO usersDTO) {
        ResponsePatternDTO responsePatternDTO = usersService.update(usersDTO);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<ResponsePatternDTO> updatePassword(@RequestBody UsersDTO usersDTO) {
        ResponsePatternDTO responsePatternDTO = usersService.updatePassword(usersDTO);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @PutMapping("/addScore")
    public ResponseEntity<ResponsePatternDTO> addScore(@RequestParam Long score) {
        ResponsePatternDTO responsePatternDTO = usersService.addScore(score);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @GetMapping("/getOrderBestPontuation")
    public ResponseEntity<List<UsersScoreDTO>> getOrderUsersBestPontuation() {
        return ResponseEntity.ok(usersService.getOrderUsersBestPontuation());
    }
}
