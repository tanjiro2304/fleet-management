package com.vn.fleetmanagement.serviceimpl;

import com.vn.fleetmanagement.converter.DTOConvertUtils;
import com.vn.fleetmanagement.dto.RouteDTO;
import com.vn.fleetmanagement.repository.RouteRespository;
import com.vn.fleetmanagement.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRespository routeRespository;
    @Override
    public List<RouteDTO> getAllRoutes() {
        return routeRespository.findAll().stream().
                map(DTOConvertUtils::convertEntityToDTO).
                collect(Collectors.toList());
    }

    @Override
    public RouteDTO getRouteById(Long id) {
        return DTOConvertUtils.convertEntityToDTO(routeRespository.findById(id).get());
    }

    @Override
    public void updateData(RouteDTO routeDTO) {
        routeRespository.save(DTOConvertUtils.convertDTOtoEntity(routeDTO));
    }
}
