package com.cg.salon.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.salon.dao.ISalonServiceDao;
import com.cg.salon.dto.SalonServiceDto;
import com.cg.salon.entity.SalonService;
import com.cg.salon.exceptions.SalonServiceNotFoundException;
import com.cg.util.SalonConstants;

/*
 * @Author - Tanmay Roy
 * Description - This service class contains the service implementations regarding Salon Service Booking Management
 */

@Service("salonservice")
public class SalonServiceImpl implements ISalonService {

	@Autowired
	private ISalonServiceDao salonservicedao;

	/*
	 * Method Name - addSalonService 
	 * Return Type - Integer
	 * Parameter - Instance of SalonServiceDto
	 * Description - Adds a new Salon Service 
	 */
	
	@Transactional
	@Override
	public Integer addSalonService(SalonServiceDto dto) {
		SalonService service = new SalonService();

		service.setDiscount(dto.getDiscount());
		service.setSalonAddress(dto.getSalonAddress());
		service.setSalonCentreName(dto.getSalonCentreName());
		service.setSalonLocation(dto.getSalonLocation());
		service.setServiceDuration(dto.getServiceDuration());
		service.setServiceName(dto.getServiceName());
		service.setServicePrice(dto.getServicePrice());

		SalonService savedservice = salonservicedao.save(service);
		return savedservice.getServiceId();
	}

	/*
	 * Method Name - viewSalonServiceById
	 * Return Type - SalonService
	 * Parameter - salonServiceId
	 * Description - returns the instance for the SalonService corresponding to the given salonService id
	 * Throws - SalonServiceNotFoundException, if the SalonService id does not exist
	 */
	
	@Override
	public SalonService viewSalonServiceById(int sid) throws SalonServiceNotFoundException {

		Optional<SalonService> optservice = salonservicedao.findById(sid);
		if (!optservice.isPresent())
			throw new SalonServiceNotFoundException(SalonConstants.SALON_SERVICE_NOT_EXIST + sid);
		return optservice.get();
	}

	/*
	 * Method Name - viewSalonServiceByName
	 * Return Type - List of SalonService
	 * Parameter - SalonService Name
	 * Description - returns the list of SalonService corresponding to the given SalonService name
	 * Throws - SalonServiceNotFoundException, if the SalonService id does not exist
	 */
	
	@Override
	public List<SalonService> viewSalonServiceByName(String serviceName) throws SalonServiceNotFoundException {

		List<SalonService> lst = salonservicedao.findByServiceName(serviceName);
		if (lst.isEmpty())
			throw new SalonServiceNotFoundException(SalonConstants.SALON_SERVICE_NOT_EXIST);
		return lst;
	}
	
	/*
	 * Method Name - viewSalonServiceByLocation
	 * Return Type - List of SalonService
	 * Parameter - SalonService Location
	 * Description - returns the list of SalonService corresponding to the given SalonService location
	 * Throws - SalonServiceNotFoundException, if the SalonService id does not exist
	 */

	@Override
	public List<SalonService> viewSalonServiceByLocation(String serviceLocation) throws SalonServiceNotFoundException {

		List<SalonService> lst = salonservicedao.findBySalonLocation(serviceLocation);
		if (lst.isEmpty())
			throw new SalonServiceNotFoundException(SalonConstants.SALON_SERVICE_NOT_EXIST);
		return lst;

	}

	/*
	 * Method Name - editSalonService
	 * Return Type - boolean
	 * Parameter - Instance of SalonServiceDto
	 * Description - edits the required fields of an existing salon Service 
	 * Throws - SalonServiceNotFoundException, if the SalonService id does not exist
	 */
	
	@Transactional
	@Override
	public boolean editSalonService(SalonServiceDto dto) throws SalonServiceNotFoundException {

		Optional<SalonService> optservice = salonservicedao.findById(dto.getServiceId());
		if (!optservice.isPresent())
			throw new SalonServiceNotFoundException(SalonConstants.SALON_SERVICE_NOT_EXIST);

		SalonService service = optservice.get();
		service.setServiceName(dto.getServiceName());
		service.setServicePrice(dto.getServicePrice());
		service.setDiscount(dto.getDiscount());
		service.setSalonAddress(dto.getSalonAddress());
		service.setSalonCentreName(dto.getSalonCentreName());
		service.setSalonLocation(dto.getSalonLocation());
		service.setServiceDuration(dto.getServiceDuration());

		return true;
	}
}