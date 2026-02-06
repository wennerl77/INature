package com.ifmg.server.apiServer.repository;

import com.ifmg.server.apiServer.model.user.UsersPath;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersPathRepository extends JpaRepository<UsersPath, Long> {
    @Query("SELECT up FROM UsersPath up WHERE up.users.usersId = :id")
    List<UsersPath> getAllUsersPathsById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UsersPath up SET up.name = :name WHERE up.usersPathId = :id")
    String updateName(@Param("name") String name, @Param("id") Long id);
}
