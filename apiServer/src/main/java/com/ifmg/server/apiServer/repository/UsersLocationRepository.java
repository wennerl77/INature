package com.ifmg.server.apiServer.repository;

import com.ifmg.server.apiServer.model.user.UsersLocation;
import com.ifmg.server.apiServer.model.user.UsersPath;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersLocationRepository extends JpaRepository<UsersLocation, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE UsersLocation ul SET ul.usersPath = :usersPath WHERE ul.usersLocationId = :id")
    void updateUsersPathId(@Param("usersPath")UsersPath usersPath, @Param("id") Long id);
}
