package com.vn.fleetmanagement.service;

import com.vn.fleetmanagement.dto.RouteDTO;
import com.vn.fleetmanagement.entities.RouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteService {


    List<RouteDTO> getAllRoutes();

    RouteDTO getRouteById(Long id);

    void updateData(RouteDTO routeDTO);
}
