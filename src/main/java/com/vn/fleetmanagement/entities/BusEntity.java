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
@Table(name="TBL_BUSES")
public class BusEntity {
    @Id
    @Column(name="BUS_ID")
    private Long id;
    @Column(name="BUS_MODEL")
    private String model;
    @Column(name="BUS_TYPE")
    private String type;
    @Column(name="BUS_REGISTRATION")
    private String registrationNo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPOT_ID")
    private DepotEntity depotEntity;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="TBL_BUSES_ROUTES",
                joinColumns = @JoinColumn(name="BUS_ID"),
                inverseJoinColumns = @JoinColumn(name="ROUTE_ID"))
    private List<RouteEntity> routeList;


}
