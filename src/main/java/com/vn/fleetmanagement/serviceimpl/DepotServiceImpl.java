package com.vn.fleetmanagement.serviceimpl;

import com.vn.fleetmanagement.converter.DTOConvertUtils;
import com.vn.fleetmanagement.dto.DepotDTO;
import com.vn.fleetmanagement.repository.DepotRepository;
import com.vn.fleetmanagement.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepotServiceImpl implements DepotService {
    @Autowired
    DepotRepository depotRepository;
    @Override
    public List<DepotDTO> getList() {
        return depotRepository.findAll().stream().
                map(DTOConvertUtils::convertEntityToDTO).
                collect(Collectors.toList());
    }
}
