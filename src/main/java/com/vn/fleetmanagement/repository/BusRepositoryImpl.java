package com.vn.fleetmanagement.repository;

import com.vn.fleetmanagement.entities.BusEntity;
import com.vn.fleetmanagement.entities.RouteEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public abstract class BusRepositoryImpl implements BusRepository{

}
