package com.ifmg.server.apiServer.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "tb_system_location")
@Data
@RequiredArgsConstructor
public class SystemLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemLocationId;

    private Double lat;

    private Double lon;

    private Double altitude;

    private String reference;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "system_path_id")
    @JsonIgnore
    private SystemPath systemPath;
}
