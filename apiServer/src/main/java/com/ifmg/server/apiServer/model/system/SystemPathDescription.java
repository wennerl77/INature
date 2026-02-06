package com.ifmg.server.apiServer.model.system;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "tb_system_path_description")
@Data
@RequiredArgsConstructor
public class SystemPathDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private String formatter;

    private String signaling;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String supportStructure;

    private String driver;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String targetAudience;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String intendedUse;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String monitoring;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String maintenance;

    private String restrictions;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String preRequisits;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String equipament;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String danger;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String environmentalEducation;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String routeCharacteristics;

    private String carryingCapacity;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String detailSignaling;

    private String severity;

    private String physicalEffort;

    private String routeGuidance;

    private String terrainConditions;

    private String time;

    private String groupSize;
}


