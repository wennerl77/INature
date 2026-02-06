package com.ifmg.server.apiServer.dto;

import java.time.LocalTime;

public record UsersLocationDTO(Long usersLocationId, Double lat, Double lon, Double altitude, LocalTime time, UsersPathDTO usersPathDTO) {
}
