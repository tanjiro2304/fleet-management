package com.vn.fleetmanagement.repository;

import com.vn.fleetmanagement.entities.BusEntity;
import com.vn.fleetmanagement.entities.RouteEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class BusInsertRepository {
    @PersistenceContext
    EntityManager entityManager;


    @Transactional
    public void saveEntity(BusEntity busEntity) {
        entityManager.createNativeQuery(
                        "INSERT INTO TBL_BUSES(BUS_ID,BUS_MODEL,BUS_TYPE,BUS_REGISTRATION,DEPOT_ID)" +
                                " VALUES(?,?,?,?,?)").setParameter(1,busEntity.getId()).
                setParameter(2,busEntity.getModel()).
                setParameter(3,busEntity.getRegistrationNo()).
                setParameter(4,busEntity.getRegistrationNo()).
                setParameter(5,busEntity.getDepotEntity().getId()).
                executeUpdate();

        for(RouteEntity entity: busEntity.getRouteList()){
            entityManager.createNativeQuery("INSERT INTO TBL_BUSES_ROUTES(BUS_ID, ROUTE_ID)" +
                            " VALUES(?,?)").
                    setParameter(1,busEntity.getId()).
                    setParameter(2,entity.getId()).
                    executeUpdate();
        }
    }
}
