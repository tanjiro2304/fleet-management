package com.vn.fleetmanagement.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepotDTO {
    private Long id;
    private String depotName;
}
