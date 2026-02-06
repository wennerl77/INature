package com.ifmg.server.apiServer.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_users_path")
@Data
@RequiredArgsConstructor
public class UsersPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usersPathId;

    private String name;

    private LocalDate date;

    private boolean state;

    @ManyToOne
    @JoinColumn(name = "users_id" ,nullable = false)
    @JsonIgnore
    private Users users;

    @OneToMany(mappedBy = "usersPath", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsersLocation> locations;

    public UsersPath(String name, LocalDate date, boolean state, Users users, List<UsersLocation> locations) {
        this.name = name;
        this.date = date;
        this.state = state;
        this.users = users;
        this.locations = locations;
    }
}
