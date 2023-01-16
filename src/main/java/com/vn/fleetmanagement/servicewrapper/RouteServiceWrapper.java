package com.vn.fleetmanagement.servicewrapper;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vn.fleetmanagement.models.RouteVO;
import com.vn.fleetmanagement.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
@SpringComponent
public class RouteServiceWrapper {

    @Autowired
    RouteService routeService;

    public List<RouteVO> getRouteVOs(){
        return routeService.getAllRoutes().stream().
                map(routeDTO -> RouteVO.builder().
                        routeNo(routeDTO.getRouteNo()).
                        id(routeDTO.getId()).build()).collect(Collectors.toList());
    }
}
