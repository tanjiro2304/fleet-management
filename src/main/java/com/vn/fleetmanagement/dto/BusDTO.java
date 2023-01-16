package com.vn.fleetmanagement.dto;

import lombok.*;

import java.util.List;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO {
    private Long id;
    private String model;
    private String type;
    private String registrationNo;
    private DepotDTO depotDTO;
    private List<RouteDTO> routeList;
}
