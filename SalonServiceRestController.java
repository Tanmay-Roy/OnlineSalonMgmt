package com.cg.salon.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.salon.dto.SalonServiceDto;
import com.cg.salon.dto.SalonServiceSuccessMessage;
import com.cg.salon.entity.SalonService;
import com.cg.salon.exceptions.SalonServiceNotFoundException;
import com.cg.salon.exceptions.ValidateSalonServiceException;
import com.cg.salon.service.ISalonService;
import com.cg.util.SalonConstants;


/*
 * @Author - Tanmay Roy
 * Description - This rest controller class contains the service implementations regarding Salon Service Management
 */

@RestController
public class SalonServiceRestController {

	@Autowired
	private ISalonService service;

	Logger logger = LoggerFactory.getLogger(SalonServiceRestController.class);

	/*
	 * Method Name - addSalonService 
	 * Return Type - Integer
	 * Parameter - Instance of SalonServiceDto
	 * Description - Adds a new Salon Service 
	 */
	
	@PostMapping("addsalonservice")
	public SalonServiceSuccessMessage addSalonService(@Valid @RequestBody SalonServiceDto salondto, BindingResult br)
			throws ValidateSalonServiceException {
		logger.info("I am in add salon service");

		if (br.hasErrors())
			throw new ValidateSalonServiceException(br.getFieldErrors());
		int sid = service.addSalonService(salondto);

		return new SalonServiceSuccessMessage(SalonConstants.SALON_SERVICE_ADDED + sid);

	}

	/*
	 * Method Name - editSalonService
	 * Return Type - boolean
	 * Parameter - Instance of SalonServiceDto
	 * Description - edits the required fields of an existing salon Service 
	 * Throws - SalonServiceNotFoundException, if the SalonService id does not exist
	 */
	
	@PutMapping("editsalonservice")
	public SalonServiceSuccessMessage editSalonService(@Valid @RequestBody SalonServiceDto salondto, BindingResult br)
			throws ValidateSalonServiceException, SalonServiceNotFoundException {
		if (br.hasErrors()) {
			throw new ValidateSalonServiceException(br.getFieldErrors());
		}
		service.editSalonService(salondto);
		return new SalonServiceSuccessMessage(SalonConstants.SALON_SERVICE_EDITED);
	}

	/*
	 * Method Name - viewSalonServiceByName
	 * Return Type - List of SalonService
	 * Parameter - SalonService Name
	 * Description - returns the list of SalonService corresponding to the given SalonService name
	 * Throws - SalonServiceNotFoundException, if the SalonService id does not exist
	 */
	
	@GetMapping("viewbysalonservice/{salonservicename}")
	public List<SalonService> viewSalonServiceByName(@PathVariable("salonservicename") String salonServiceName)
			throws SalonServiceNotFoundException {
		return service.viewSalonServiceByName(salonServiceName);
	}

	/*
	 * Method Name - viewSalonServiceById
	 * Return Type - SalonService
	 * Parameter - Salon Service Id
	 * Description - returns the instance for the Salon Service corresponding to the given Salon Service id
	 * Throws - SalonServiceNotFoundException, if the Salon Service id does not exist
	 */
	
	@GetMapping("viewbysalonserviceid/{salonserviceid}")
	public SalonService viewSalonServiceById(@PathVariable("salonserviceid") int salonServiceId)
			throws SalonServiceNotFoundException {
		return service.viewSalonServiceById(salonServiceId);
	}
	
	/*
	 * Method Name - viewSalonServiceByLocation
	 * Return Type - List of SalonService
	 * Parameter - SalonService Location
	 * Description - returns the list of SalonService corresponding to the given SalonService location
	 * Throws - SalonServiceNotFoundException, if the SalonService id does not exist
	 */
	
	@GetMapping("viewbysalonservicelocation/{salonservicelocation}")
	public List<SalonService> viewSalonServiceByLocation(
			@PathVariable("salonservicelocation") String salonServiceLocation) throws SalonServiceNotFoundException {
		return service.viewSalonServiceByLocation(salonServiceLocation);
	}
}
