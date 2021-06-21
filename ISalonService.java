package com.cg.salon.service;

import java.util.List;

import com.cg.salon.dto.SalonServiceDto;
import com.cg.salon.entity.SalonService;
import com.cg.salon.exceptions.SalonServiceNotFoundException;

public interface ISalonService {

	public Integer addSalonService(SalonServiceDto dto);

	public SalonService viewSalonServiceById(int sid) throws SalonServiceNotFoundException;

	public List<SalonService> viewSalonServiceByName(String serviceName) throws SalonServiceNotFoundException;

	public List<SalonService> viewSalonServiceByLocation(String salonLocation) throws SalonServiceNotFoundException;

	public boolean editSalonService(SalonServiceDto dto) throws SalonServiceNotFoundException;
}