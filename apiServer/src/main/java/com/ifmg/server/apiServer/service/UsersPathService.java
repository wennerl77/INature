package com.ifmg.server.apiServer.service;

import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.UsersPathDTO;
import com.ifmg.server.apiServer.infra.security.TokenService;
import com.ifmg.server.apiServer.model.user.Users;
import com.ifmg.server.apiServer.model.user.UsersLocation;
import com.ifmg.server.apiServer.model.user.UsersPath;
import com.ifmg.server.apiServer.repository.UsersLocationRepository;
import com.ifmg.server.apiServer.repository.UsersPathRepository;
import com.ifmg.server.apiServer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersPathService {
    @Autowired
    UsersPathRepository usersPathRepository;

    @Autowired
    UsersLocationRepository usersLocationRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsersRepository usersRepository;

    public List<UsersPath> getAllPaths() {
        return usersPathRepository.findAll();
    }

    public List<UsersPath> getAllPathsOfUser() {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersPathRepository.getAllUsersPathsById(user.getUsersId());
    }

    public ResponsePatternDTO addPath(UsersPathDTO upDTO) {
        if (upDTO == null) return new ResponsePatternDTO("userPath is null", 400);
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsersPath usersPath = new UsersPath(upDTO.name(), upDTO.date(), upDTO.state(), user, upDTO.locations());

        UsersPath usersPathWithId = usersPathRepository.save(usersPath);
        usersPathWithId.getLocations().forEach(l -> usersLocationRepository.updateUsersPathId(usersPathWithId, l.getUsersLocationId()));
        return new ResponsePatternDTO("Path add", 200);
    }

    public ResponsePatternDTO setLocations(Long id, List<UsersLocation> list) {
        if (id == null || list == null || list.isEmpty()) return new ResponsePatternDTO("Params is null", 400);
        if (usersPathRepository.findById(id).isEmpty())
            return new ResponsePatternDTO("Path with the id " + id + " doesn't exists", 400);
        UsersPath usersPath = usersPathRepository.findById(id).get();
        list.forEach(location -> {
            location.setUsersPath(usersPath);
            usersLocationRepository.save(location);
        });
        return new ResponsePatternDTO("Set locations completed successfully", 200);
    }

    public ResponsePatternDTO deletePath(Long id) {
        if (id == null) return new ResponsePatternDTO("id is null", 400);
        usersPathRepository.deleteById(id);
        return new ResponsePatternDTO("Path deleted", 200);
    }

    public ResponsePatternDTO updatePath(UsersPathDTO upDTO) {
        if (upDTO.name() == null || upDTO.name().isBlank()) return new ResponsePatternDTO("Name is empty or null", 400);
        usersPathRepository.updateName(upDTO.name(), upDTO.usersPathId());
        return new ResponsePatternDTO("Updated path", 200);
    }
}
