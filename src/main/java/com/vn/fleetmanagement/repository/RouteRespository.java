package com.vn.fleetmanagement.repository;

import com.vn.fleetmanagement.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRespository extends JpaRepository<RouteEntity, Long> {
}
