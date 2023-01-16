package com.vn.fleetmanagement.servicewrapper;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vn.fleetmanagement.dto.BusDTO;
import com.vn.fleetmanagement.models.BusVO;
import com.vn.fleetmanagement.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class BusServiceWrapper {
    @Autowired
    BusService busService;


    public void updateData(BusDTO busDTO){
        busService.update(busDTO);
    }
    public void saveToDB(BusDTO busDTO){
        busService.save(busDTO);
    }
}
