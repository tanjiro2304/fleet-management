package com.vn.fleetmanagement.servicewrapper;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vn.fleetmanagement.models.DepotVO;
import com.vn.fleetmanagement.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@SpringComponent
public class DepotServiceWrapper {

    @Autowired
    DepotService depotService;

    public List<DepotVO> getAllDepot(){
        return depotService.getList().stream().
                map(depotDTO -> DepotVO.builder().
                        id(depotDTO.getId()).
                        depotName(depotDTO.getDepotName()).
                        build()
                        ).collect(Collectors.toList());
    }
}
