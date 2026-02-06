package com.ifmg.server.apiServer.controller;

import com.ifmg.server.apiServer.dto.ImageDTO;
import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.SystemPathDTO;
import com.ifmg.server.apiServer.model.system.SystemLocation;
import com.ifmg.server.apiServer.service.SystemPathService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("systemPath")
public class SystemPathController {
    @Autowired
    private SystemPathService systemPathService;

    @Transactional
    @GetMapping("/searchByName")
    public ResponseEntity<List<SystemPathDTO>> searchByName(@RequestParam String name) {
        List<SystemPathDTO> list = systemPathService.searchByName(name);
        return list == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().body(list);
    }

    @GetMapping("/getAll")
    @Transactional
    public ResponseEntity<List<SystemPathDTO>> getAll() {
        return ResponseEntity.ok().body(systemPathService.getAll());
    }

    @Transactional
    @GetMapping("/getByName")
    public ResponseEntity<SystemPathDTO> getByName(@RequestParam String name) {
        SystemPathDTO systemPathDTO = systemPathService.getByName(name);
        return systemPathDTO == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().body(systemPathDTO);
    }

    @PutMapping("/addImageInTrail")
    @Transactional
    public ResponseEntity<ResponsePatternDTO> addImageInTrail(@RequestBody ImageDTO image, @RequestParam Long id) {
        ResponsePatternDTO responsePatternDTO = systemPathService.addImageInTrail(image, id);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @GetMapping("/getById")
    @Transactional
    public ResponseEntity<SystemPathDTO> getById(@RequestParam Long id) {
        SystemPathDTO systemPathDTO = systemPathService.getById(id);
        return systemPathDTO == null ? ResponseEntity.badRequest().body(null) : ResponseEntity.ok().body(systemPathDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponsePatternDTO> add(@RequestBody SystemPathDTO systemPathDTO){
        ResponsePatternDTO responsePatternDTO = systemPathService.add(systemPathDTO);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @PostMapping("/setLocationsPathById")
    public ResponseEntity<ResponsePatternDTO> setLocations(@RequestParam Long id, @RequestBody List<SystemLocation> list) {
        ResponsePatternDTO responsePatternDTO = systemPathService.setLocations(id, list);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<ResponsePatternDTO> delete(@RequestParam Long id) {
        ResponsePatternDTO responsePatternDTO = systemPathService.delete(id);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }

    @PutMapping("/updateById")
    public ResponseEntity<ResponsePatternDTO> update(@RequestParam Long id, @RequestBody SystemPathDTO systemPathDTO) {
        ResponsePatternDTO responsePatternDTO = systemPathService.update(id, systemPathDTO);
        return responsePatternDTO.status() == 200 ? ResponseEntity.ok(responsePatternDTO) : ResponseEntity.badRequest().body(responsePatternDTO);
    }
}
