package com.vn.fleetmanagement.modules.datagrid;

import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vn.fleetmanagement.dto.BusDTO;
import com.vn.fleetmanagement.models.BusVO;
import com.vn.fleetmanagement.models.DepotVO;
import com.vn.fleetmanagement.models.RouteVO;
import com.vn.fleetmanagement.mvputils.BasePresenter;
import com.vn.fleetmanagement.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class DataGridPresenter extends BasePresenter<DataGridView> {

    @Autowired
    BusService busService;

    public List<BusVO> getData(){
        return convertToVO(busService.getAllBusList());
    }

    public List<BusVO> convertToVO(List<BusDTO> busDTOList){
        List<BusVO> busVOS = new ArrayList<>();
        for(BusDTO busDTO : busDTOList){
            busVOS.addAll(busDTO.getRouteList().stream().map(route ->
                    BusVO.builder().
                            busId(busDTO.getId()).
                            model(busDTO.getModel()).
                            type(busDTO.getType()).
                            registrationNo(busDTO.getRegistrationNo()).
                            depotVO(DepotVO.builder().id(busDTO.getDepotDTO().getId()).
                                    depotName(busDTO.getDepotDTO().
                                            getDepotName()).build()).
                            routeVO(RouteVO.builder().id(route.getId()).routeNo(route.getRouteNo()).build()).
                            build()
            ).collect(Collectors.toList()));
        }
        return busVOS;
    }
}
