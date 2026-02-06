package com.ifmg.server.apiServer.dto;

import com.ifmg.server.apiServer.model.user.Users;
import com.ifmg.server.apiServer.model.user.UsersLocation;

import java.time.LocalDate;
import java.util.List;

public record UsersPathDTO(Long usersPathId, String name, LocalDate date, Boolean state, Users users, List<UsersLocation> locations) {
}
