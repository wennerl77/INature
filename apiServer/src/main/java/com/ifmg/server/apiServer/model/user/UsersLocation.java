package com.ifmg.server.apiServer.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "tb_users_location")
@Data
@RequiredArgsConstructor
public class UsersLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersLocationId;

    private Double lat;

    private Double lon;

    private Double altitude;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "users_path_id")
    @JsonIgnore
    private UsersPath usersPath;
}
