package com.vn.fleetmanagement.converter;

import com.vn.fleetmanagement.dto.BusDTO;
import com.vn.fleetmanagement.dto.DepotDTO;
import com.vn.fleetmanagement.dto.RouteDTO;
import com.vn.fleetmanagement.entities.BusEntity;
import com.vn.fleetmanagement.entities.DepotEntity;
import com.vn.fleetmanagement.entities.RouteEntity;
import com.vn.fleetmanagement.models.BusVO;
import com.vn.fleetmanagement.models.DepotVO;
import com.vn.fleetmanagement.models.RouteVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DTOConvertUtils {
    public static BusDTO convertEntityToDTO(BusEntity busEntity){
        return BusDTO.builder().
                id(busEntity.getId()).
                model(busEntity.getModel()).
                type(busEntity.getType()).
                registrationNo(busEntity.getRegistrationNo()).
                depotDTO(convertEntityToDTO(busEntity.getDepotEntity())).
                routeList(busEntity.getRouteList().stream().
                        map(DTOConvertUtils::convertEntityToDTO).
                        collect(Collectors.toList())).
                build();
    }

    public static DepotDTO convertEntityToDTO(DepotEntity depotEntity){
        return DepotDTO.builder().
                id(depotEntity.getId()).
                depotName(depotEntity.getDepotName()).
                build();
    }

    public static RouteDTO convertEntityToDTO(RouteEntity routeEntity){
        return RouteDTO.builder().
                id(routeEntity.getId()).
                routeNo(routeEntity.getRouteNo()).
                build();
    }

    public static BusEntity convertDTOtoEntity(BusDTO busDTO ){
        return BusEntity.builder().
                id(busDTO.getId()).
                model(busDTO.getModel()).
                registrationNo(busDTO.getRegistrationNo()).
                type(busDTO.getType()).
                depotEntity(convertDTOtoEntity(busDTO.getDepotDTO())).
                routeList(busDTO.getRouteList().
                        stream().
                        map(DTOConvertUtils::convertDTOtoEntity).
                        collect(Collectors.toList())).
            build();

    }

    public static RouteEntity convertDTOtoEntity(RouteDTO routeDTO){
        return RouteEntity.builder().
                id(routeDTO.getId()).
                routeNo(routeDTO.getRouteNo()).build();
    }

    public static DepotEntity convertDTOtoEntity(DepotDTO depotDTO){
        return DepotEntity.builder().
                id(depotDTO.getId()).
                depotName(depotDTO.getDepotName()).build();
    }

    public static BusDTO convertVOtoDTO(BusVO busVO){


        return BusDTO.builder().
                id(busVO.getBusId()).
                model(busVO.getModel()).
                registrationNo(busVO.getRegistrationNo()).
                type(busVO.getType()).
                depotDTO(convertVOtoDTO(busVO.getDepotVO())).
                routeList(busVO.getRouteVOSet().stream().
                        map(DTOConvertUtils::convertVOtoDTO).
                        collect(Collectors.toList())).
        build()
        ;
    }

    public static DepotDTO convertVOtoDTO(DepotVO depotVO){
        return DepotDTO.builder().
                id(depotVO.getId()).
                depotName(depotVO.getDepotName()).
                build();
    }

    public static RouteDTO convertVOtoDTO(RouteVO routeVO){
        return RouteDTO.builder().
                id(routeVO.getId()).
                routeNo(routeVO.getRouteNo()).
                build();
    }
}
