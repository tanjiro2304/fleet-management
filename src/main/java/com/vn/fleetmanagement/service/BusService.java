package com.vn.fleetmanagement.service;

import com.vn.fleetmanagement.dto.BusDTO;

import java.util.List;

public interface BusService  {
    List<BusDTO> getAllBusList();

    void save(BusDTO busDTO);

    void update(BusDTO busDTO);
}
