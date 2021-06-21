package com.cg.salon.web;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.salon.dto.SalonServiceScheduleDto;
import com.cg.salon.dto.SalonServiceScheduleSuccessMessage;
import com.cg.salon.entity.SalonServiceSchedule;
import com.cg.salon.exceptions.SalonServiceNotFoundException;
import com.cg.salon.exceptions.SalonServiceScheduleNotFoundException;
import com.cg.salon.exceptions.ScheduleCancelException;
import com.cg.salon.exceptions.ValidateSalonServiceScheduleException;
import com.cg.salon.service.ISalonServiceSchedule;
import com.cg.util.SalonConstants;

/*
 * @Author - Ritobina Sinha
 * Description - This service class contains the service implementations regarding Salon Service Schedule
 * Management
 */

@RestController
public class SalonServiceScheduleRestController {

	@Autowired
	private ISalonServiceSchedule schedule;

	Logger logger = LoggerFactory.getLogger(SalonServiceScheduleRestController.class);
	
	/*
	 * Method Name - createSchedule
	 *  Return Type - Integer 
	 *  Parameter - instance of SalonServiceScheduleDto
	 *  Description - Creates a schedule according to the
	 *                SalonServiceScheduleId provided by the admin 
	 *  Throws - SalonServiceNotFoundException,if no salon service exists     
	 */  

	@PostMapping("createschedule")
	public SalonServiceScheduleSuccessMessage addSchedule(@Valid @RequestBody SalonServiceScheduleDto scheduledto,
			BindingResult br) throws ValidateSalonServiceScheduleException, SalonServiceNotFoundException {
		logger.info("I am in addSchedule");

		if (br.hasErrors())
			throw new ValidateSalonServiceScheduleException(br.getFieldErrors());
		int sid = schedule.createSchedule(scheduledto);

		return new SalonServiceScheduleSuccessMessage(SalonConstants.SCHEDULE_ADDED + sid);

	}

	/*
	 * Method Name - cancelSchedule
	 * Return Type - boolean
	 * Parameter - scheduleId, integer , Schedule id
	 * Description - sets the schedule status as cancelled 
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 *          ScheduleCancelException , if Schedule cannot be cancelled as it must be a future date
	 */
	
	@PutMapping("cancelsalonserviceschedule/{scheduleid}")
	public SalonServiceScheduleSuccessMessage cancelSchedule(@PathVariable("scheduleid") int scheduleId)
			throws  SalonServiceScheduleNotFoundException, ScheduleCancelException {

		schedule.cancelSchedule(scheduleId);

		return new SalonServiceScheduleSuccessMessage(SalonConstants.SCHEDULE_CANCELLED);
	}

	/*
	 * Method Name - viewSalonServiceScheduleById
	 * Return Type - SalonServiceSchedule
	 * Parameter - sid,integer,salon service schedule id
	 * Description - returns instance for the schedule corresponding to the schedule id
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 */
	
	@GetMapping("viewbysalonservicescheduleid/{salonservicescheduleid}")
	public SalonServiceSchedule viewSalonServiceScheduleById(
			@PathVariable("salonservicescheduleid") int salonServiceScheduleId)
			throws SalonServiceScheduleNotFoundException {
		return schedule.viewSalonServiceScheduleById(salonServiceScheduleId);
	}

	/*
	 * Method Name - viewSalonServiceScheduleByDate
	 * Return Type - List<SalonServiceSchedule>
	 * Parameter - scheduleDate, LocalDate , Schedule date
	 * Description -  returns schedule by date
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 */
	
	@GetMapping("viewbysalonservicescheduledate/{salonservicescheduledate}")
	public List<SalonServiceSchedule> viewSalonServiceScheduleByDate(
			@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable("salonservicescheduledate") LocalDate salonServiceScheduleDate)
			throws SalonServiceScheduleNotFoundException {
		return schedule.viewSalonServiceScheduleByDate(salonServiceScheduleDate);
	}

	/*
	 * Method Name - viewSalonServiceScheduleByServiceId
	 * Return Type - List<SalonServiceSchedule>
	 * Parameter - serid, integer , Service id
	 * Description - returns schedule by service id
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 */
	
	@GetMapping("viewbysalonservicescheduleserviceid/{salonservicescheduleserviceid}")
	public SalonServiceSchedule viewSalonServiceScheduleByServiceId(
			@PathVariable("salonservicescheduleserviceid") int salonServiceScheduleServiceId)
			throws SalonServiceScheduleNotFoundException {
		return schedule.viewSalonServiceScheduleById(salonServiceScheduleServiceId);
	}
}