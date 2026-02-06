package com.ifmg.server.apiServer.repository;

import com.ifmg.server.apiServer.model.system.SystemPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SystemPathRepository extends JpaRepository<SystemPath, Long> {
    @Query("SELECT sp FROM SystemPath sp WHERE sp.name = :name")
    SystemPath getByName(@Param("name") String name);

    @Query(value = "SELECT * FROM tb_system_path sp ORDER BY similarity(:name, sp.name) DESC;", nativeQuery = true)
    List<SystemPath> getNameBySimilarity(@Param("name") String name);
}
