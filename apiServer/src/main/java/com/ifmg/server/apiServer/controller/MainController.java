package com.ifmg.server.apiServer.controller;

import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ResponseEntity<ResponsePatternDTO> home() {
        return ResponseEntity.ok(new ResponsePatternDTO("Hello World", 200));
    }
}
