package com.ifmg.server.apiServer.dto;

import com.ifmg.server.apiServer.model.user.UserRole;
import com.ifmg.server.apiServer.model.user.Users;

public record UsersDTO(String name, String email, String password, String city, String image, Long score, UserRole role) {
    public static UsersDTO fromEntity(Users users) {
        return new UsersDTO(
                users.getName(),
                users.getEmail(),
                users.getPassword(),
                users.getCity(),
                users.getImage(),
                users.getScore(),
                users.getRole());
    }
}
