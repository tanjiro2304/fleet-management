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
@Table(name="TBL_DEPOTS")
public class DepotEntity {
    @Id
    @Column(name="DEPOT_ID")
    private Long id;
    @Column(name="DEPOT_NAME")
    private String depotName;

    @OneToMany(mappedBy = "depotEntity", cascade = CascadeType.ALL)
    private List<BusEntity> busEntities;
}
