package com.cg.salon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.salon.entity.SalonService;

@Repository("salonservicedao")
public interface ISalonServiceDao extends JpaRepository<SalonService, Integer> {

	public List<SalonService> findByServiceName(String serviceName);

	public List<SalonService> findBySalonLocation(String salonLocation);
}
