package com.ifmg.server.apiServer.model.system;

import com.ifmg.server.apiServer.dto.SystemPathDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tb_system_path")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class SystemPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long systemPathId;

    @NonNull
    private String name;

    @NonNull
    private Long distance;

    @NonNull
    private Long duration;

    @OneToOne
    @JoinColumn(name = "description_id")
    @NonNull
    private SystemPathDescription systemPathDescription;

    @OneToOne
    @JoinColumn(name = "start_location_id")
    @NonNull
    private SystemLocation startPoint;

    @OneToOne
    @JoinColumn(name = "end_location_id")
    @NonNull
    private SystemLocation endPoint;

    @OneToMany(mappedBy = "systemPath", cascade = CascadeType.ALL, orphanRemoval = true)
    @NonNull
    private List<SystemLocation> locations;

    @NonNull
    @ElementCollection
    @CollectionTable(name = "path_images", joinColumns = @JoinColumn(name = "path_id"))
    @Lob
    @Column(name = "image", columnDefinition = "TEXT")
    private List<String> images;

    public void addImage(String image) {
        this.images.add(image);
    }

    public SystemPath(Long systemPathId, @NonNull String name) {
        this.systemPathId = systemPathId;
        this.name = name;
    }

    public static SystemPath fromEntity(SystemPathDTO spDTO) {
        return new SystemPath(
                spDTO.name(),
                spDTO.distance(),
                spDTO.duration(),
                spDTO.systemPathDescription(),
                spDTO.startPoint(),
                spDTO.endPoint(),
                spDTO.locations(),
                spDTO.images()
        );
    }
}
