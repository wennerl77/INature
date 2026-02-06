package com.ifmg.server.apiServer.dto;

import com.ifmg.server.apiServer.model.user.UserRole;
import com.ifmg.server.apiServer.model.user.Users;

public record UsersTableDTO(Long id, String name, String email, String city, Long score, UserRole role) {
    public static UsersTableDTO fromEntity(Users users) {
        return new UsersTableDTO(
                users.getUsersId(),
                users.getName(),
                users.getEmail(),
                users.getCity(),
                users.getScore(),
                users.getRole()
        );
    }
}
