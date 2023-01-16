package com.vn.fleetmanagement.models;

import lombok.*;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusVO {
    private Long busId;

    private String model;

    private String type;

    private String registrationNo;

    private RouteVO routeVO;
    private DepotVO depotVO;

    private Set<RouteVO> routeVOSet;

}
