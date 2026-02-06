package com.ifmg.server.apiServer.service;

import com.ifmg.server.apiServer.dto.ImageDTO;
import com.ifmg.server.apiServer.dto.ResponsePatternDTO;
import com.ifmg.server.apiServer.dto.SystemPathDTO;
import com.ifmg.server.apiServer.model.system.SystemLocation;
import com.ifmg.server.apiServer.model.system.SystemPath;
import com.ifmg.server.apiServer.model.system.SystemPathDescription;
import com.ifmg.server.apiServer.repository.SystemLocationRepository;
import com.ifmg.server.apiServer.repository.SystemPathDescriptionRepository;
import com.ifmg.server.apiServer.repository.SystemPathRepository;
import com.ifmg.server.apiServer.util.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemPathService {
    @Autowired
    private SystemPathRepository pathRepository;

    @Autowired
    private SystemPathDescriptionRepository repositoryDescription;

    @Autowired
    private SystemLocationRepository repositoryLocation;

    public List<SystemPathDTO> searchByName(String name) {
        if (name == null || name.isBlank()) return null;
        return pathRepository.getNameBySimilarity(name).stream().map(SystemPathDTO::fromEntity).toList();
    }

    public List<SystemPathDTO> getAll() {
        return pathRepository.findAll().stream().map(SystemPathDTO::fromEntity).toList();
    }

    public SystemPathDTO getByName(String name) {
        if (name == null || name.isBlank()) return null;
        SystemPath sp = pathRepository.getByName(name);
        if (sp == null) return null;
        return SystemPathDTO.fromEntity(sp);
    }

    public ResponsePatternDTO addImageInTrail(ImageDTO image, Long id) {
        if (image == null || image.image() == null || image.image().isEmpty())
            return new ResponsePatternDTO("The image is empty", 400);
        Optional<SystemPath> systemPathOptional;
        if (id == null || (systemPathOptional = pathRepository.findById(id)).isEmpty())
            return new ResponsePatternDTO("Trail not found", 400);

        SystemPath systemPath = systemPathOptional.get();
        systemPath.addImage(image.image());
        pathRepository.save(systemPath);

        return new ResponsePatternDTO("Image added successfully", 200);
    }

    public SystemPathDTO getById(Long id) {
        if (id == null) return null;
        Optional<SystemPath> op = pathRepository.findById(id);
        return op.map(SystemPathDTO::fromEntity).orElse(null);
    }

    public ResponsePatternDTO add(SystemPathDTO systemPathDTO) {
        if (systemPathDTO == null) return new ResponsePatternDTO("Error while trying to add track.", 400);
        SystemPath sp = SystemPath.fromEntity(systemPathDTO);

        SystemPathDescription save = repositoryDescription.save(sp.getSystemPathDescription());
        SystemLocation startPoint = repositoryLocation.save(sp.getStartPoint());
        SystemLocation endPoint = repositoryLocation.save(sp.getEndPoint());

        sp.setSystemPathDescription(save);
        sp.setStartPoint(startPoint);
        sp.setEndPoint(endPoint);
        pathRepository.save(sp);

        return new ResponsePatternDTO("Track added", 200);
    }

    public ResponsePatternDTO setLocations(Long id, List<SystemLocation> list) {
        if (id == null || list == null || list.isEmpty())
            return new ResponsePatternDTO("Params is null", 400);
        if (pathRepository.findById(id).isEmpty())
            return new ResponsePatternDTO("Path with the id " + id + " doesn't exists", 400);
        SystemPath systemPath = pathRepository.findById(id).get();
        list.forEach(systemLocation -> {
            systemLocation.setSystemPath(systemPath);
            repositoryLocation.save(systemLocation);
        });
        return new ResponsePatternDTO("Set locations completed successfully", 200);
    }

    public ResponsePatternDTO delete(Long id) {
        if (id == null) return new ResponsePatternDTO("id parameter not passed", 400);
        pathRepository.deleteById(id);
        return new ResponsePatternDTO("Delete trail successful", 400);
    }

    public ResponsePatternDTO update(Long id, SystemPathDTO systemPathDTO) {
        if (id == null) return new ResponsePatternDTO("id parameter not passed", 400);

        SystemPath systemPath = pathRepository.findById(id).orElseThrow(() -> new RuntimeException("SystemPath not find"));
        SystemPathDescription systemPathDescription = repositoryDescription.getReferenceById(systemPath.getSystemPathDescription().getId());

        Util util = new Util();

        BeanUtils.copyProperties(systemPathDTO.systemPathDescription(), systemPathDescription, util.getNullPropertyNames(systemPathDTO.systemPathDescription()));
        BeanUtils.copyProperties(systemPathDTO, systemPath, util.getNullPropertyNames(systemPathDTO));
        repositoryDescription.save(systemPathDescription);
        pathRepository.save(systemPath);

        return new ResponsePatternDTO("Update succesfully", 200);
    }
}
