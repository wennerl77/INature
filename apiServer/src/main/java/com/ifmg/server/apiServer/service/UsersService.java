package com.ifmg.server.apiServer.service;

import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.UsersDTO;
import com.ifmg.server.apiServer.dto.UsersScoreDTO;
import com.ifmg.server.apiServer.dto.UsersTableDTO;
import com.ifmg.server.apiServer.model.user.Users;
import com.ifmg.server.apiServer.repository.UsersRepository;
import com.ifmg.server.apiServer.util.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public List<UsersTableDTO> getAll() {
        return usersRepository.findAll().stream().map(UsersTableDTO::fromEntity).toList();
    }

    public UsersDTO getByEmail(String email) {
        if (email == null || email.isBlank()) return null;
        Users user = (Users) usersRepository.findByEmail(email);
        if (user == null) return null;
        user.setPassword(null);

        return UsersDTO.fromEntity(user);
    }

    public ResponsePatternDTO update(UsersDTO usersUpdateDTO) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Util util = new Util();
        BeanUtils.copyProperties(usersUpdateDTO, user, util.getNullPropertyNames(usersUpdateDTO));
        usersRepository.save(user);
        return new ResponsePatternDTO("user has been modified", 200);
    }

    public ResponsePatternDTO updatePassword(UsersDTO usersDTO) {
        String passwordDec = usersDTO.password();

        if (passwordDec == null || passwordDec.isBlank())
            return new ResponsePatternDTO("password is null or blank", 400);
        else if (passwordDec.trim().length() < 8)
            return new ResponsePatternDTO("password is very small, minimal length - 8", 400);

        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String encryptedPassword = new BCryptPasswordEncoder().encode(passwordDec);
        usersRepository.upPassword(user.getUsersId(), encryptedPassword);

        return new ResponsePatternDTO("password has been modified", 200);
    }

    public ResponsePatternDTO addScore(Long score) {
        if (score == null) return new ResponsePatternDTO("Score is null", 400);
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long new_score = score + (user.getScore() == null ? 0L : user.getScore());
        usersRepository.upScore(user.getUsersId(), new_score);
        return new ResponsePatternDTO("Score add", 200);
    }

    public List<UsersScoreDTO> getOrderUsersBestPontuation() {
        return usersRepository.findAll(Sort.by("score").ascending()).stream().map(UsersScoreDTO::fromEntity).toList();
    }
}
