package com.ifmg.server.apiServer.repository;

import com.ifmg.server.apiServer.model.system.SystemPathDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemPathDescriptionRepository extends JpaRepository<SystemPathDescription, Long> {
}
