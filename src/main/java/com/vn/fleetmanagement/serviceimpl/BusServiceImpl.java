package com.vn.fleetmanagement.serviceimpl;

import com.vn.fleetmanagement.converter.DTOConvertUtils;
import com.vn.fleetmanagement.dto.BusDTO;
import com.vn.fleetmanagement.dto.RouteDTO;
import com.vn.fleetmanagement.entities.RouteEntity;
import com.vn.fleetmanagement.repository.BusInsertRepository;
import com.vn.fleetmanagement.repository.BusRepository;
import com.vn.fleetmanagement.service.BusService;
import com.vn.fleetmanagement.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusServiceImpl implements BusService {


    @Autowired
    BusRepository busRepository;

    @Autowired
    RouteService routeService;

    @Autowired
    BusInsertRepository busInsertRepository;

    @Override
    public List<BusDTO> getAllBusList() {
        return busRepository.findAll().stream().map(DTOConvertUtils::convertEntityToDTO).
                collect(Collectors.toList());
    }

    @Override
    public void save(BusDTO busDTO) {
//        BusDTO newBus = busDTO;
//
//        busDTO.getRouteList().stream().map(routeDTO -> {
//            RouteDTO route = routeService.getRouteById(routeDTO.getId());
//            route.getBusDTOList().add(busDTO);
//            return routeDTO;
//        });

        busRepository.save(DTOConvertUtils.convertDTOtoEntity(busDTO));
    }

    @Override
    public void update(BusDTO busDTO) {
        busRepository.save(DTOConvertUtils.convertDTOtoEntity(busDTO));
    }
}
