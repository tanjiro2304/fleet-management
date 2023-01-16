package com.vn.fleetmanagement.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RouteVO {
    private Long id;
    private String routeNo;

}
