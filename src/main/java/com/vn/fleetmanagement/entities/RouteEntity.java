package com.vn.fleetmanagement.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TBL_ROUTES")
public class RouteEntity {
    @Id
    @Column(name="ROUTE_ID")
    private Long id;
    @Column(name="ROUTE_NO")
    private String routeNo;
    @ManyToMany(mappedBy = "routeList",cascade = CascadeType.ALL)
    private List<BusEntity> busEntityList;
}
