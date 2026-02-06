package com.ifmg.server.apiServer.dto;

import com.ifmg.server.apiServer.model.user.Users;

public record UsersScoreDTO (String name, Long score) {
    public static UsersScoreDTO fromEntity(Users users) {
        return new UsersScoreDTO(users.getName(), users.getScore());
    }
}
