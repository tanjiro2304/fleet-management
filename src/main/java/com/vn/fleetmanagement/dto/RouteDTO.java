package com.vn.fleetmanagement.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO {
    private Long id;
    private String routeNo;

    private List<BusDTO> busDTOList;

}
