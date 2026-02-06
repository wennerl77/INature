package com.ifmg.server.apiServer.controller;

import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.UsersPathDTO;
import com.ifmg.server.apiServer.infra.security.TokenService;
import com.ifmg.server.apiServer.model.user.Users;
import com.ifmg.server.apiServer.model.user.UsersLocation;
import com.ifmg.server.apiServer.model.user.UsersPath;
import com.ifmg.server.apiServer.repository.UsersLocationRepository;
import com.ifmg.server.apiServer.repository.UsersPathRepository;
import com.ifmg.server.apiServer.repository.UsersRepository;
import com.ifmg.server.apiServer.service.UsersPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usersPath")
public class UsersPathController {
    @Autowired
    private UsersPathService usersPathService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UsersPath>> getAllPaths() {
        return ResponseEntity.ok(usersPathService.getAllPaths());
    }

    @GetMapping("/getAllPathsOfUser")
    public ResponseEntity<List<UsersPath>> getAllPathsOfUser(){
        return ResponseEntity.ok(usersPathService.getAllPathsOfUser());
    }

    @PostMapping("/addPath")
    public ResponseEntity<ResponsePatternDTO> addPath(@RequestBody UsersPathDTO upDTO) {
        ResponsePatternDTO responsePatternDTO = usersPathService.addPath(upDTO);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @PostMapping("/setLocationsPathById")
    public ResponseEntity<ResponsePatternDTO> setLocations(@RequestParam Long id, @RequestBody List<UsersLocation> list) {
        ResponsePatternDTO responsePatternDTO = usersPathService.setLocations(id, list);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }
    @DeleteMapping("/deletePathById")
    public ResponseEntity<ResponsePatternDTO> deletePath(@RequestParam Long id) {
        ResponsePatternDTO responsePatternDTO = usersPathService.deletePath(id);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @PutMapping("/updatePath")
    public ResponseEntity<ResponsePatternDTO> updatePath(@RequestBody UsersPathDTO upDTO) {
        ResponsePatternDTO responsePatternDTO = usersPathService.updatePath(upDTO);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }
}
