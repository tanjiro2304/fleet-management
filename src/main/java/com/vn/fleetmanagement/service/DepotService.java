package com.vn.fleetmanagement.service;

import com.vn.fleetmanagement.dto.DepotDTO;
import com.vn.fleetmanagement.entities.DepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepotService {
    List<DepotDTO> getList();
}
