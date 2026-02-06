package com.ifmg.server.apiServer.repository;

import com.ifmg.server.apiServer.model.system.SystemLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLocationRepository extends JpaRepository<SystemLocation, Long> {
}
