package com.ifmg.server.apiServer.dto;

import com.ifmg.server.apiServer.model.system.SystemLocation;
import com.ifmg.server.apiServer.model.system.SystemPath;
import com.ifmg.server.apiServer.model.system.SystemPathDescription;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.List;

public record SystemPathDTO(Long systemPathId, String name, List<SystemLocation> locations, Long distance, Long duration, SystemPathDescription systemPathDescription, SystemLocation startPoint, SystemLocation endPoint, List<String> images) {
    @Transactional
    public static SystemPathDTO fromEntity(@NonNull SystemPath sp) {
        return new SystemPathDTO(
                sp.getSystemPathId(),
                sp.getName(),
                sp.getLocations(),
                sp.getDistance(),
                sp.getDuration(),
                sp.getSystemPathDescription(),
                sp.getStartPoint(),
                sp.getEndPoint(),
                List.copyOf(sp.getImages())
        );
    }
}
