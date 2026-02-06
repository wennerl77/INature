package com.ifmg.server.apiServer.repository;

import com.ifmg.server.apiServer.model.user.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<Users, Long> {
    UserDetails findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.name = :name, u.city = :city, u.image = :image WHERE u.id = :id")
    void upParams(@Param("id") Long id, @Param("name") String name, @Param("city") String city, @Param("image") String image);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.password = :password WHERE u.id = :id")
    void upPassword(@Param("id") Long id, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.score = :score WHERE u.id = :id")
    void upScore(@Param("id") Long id, @Param("score") Long score);
}
