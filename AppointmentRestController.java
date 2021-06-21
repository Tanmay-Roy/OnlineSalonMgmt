package com.cg.salon.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.salon.dto.AppointmentDto;
import com.cg.salon.dto.AppointmentSuccessMessage;
import com.cg.salon.entity.Appointment;
import com.cg.salon.exceptions.AppointmentCancelException;
import com.cg.salon.exceptions.AppointmentNotFoundException;
import com.cg.salon.exceptions.CustomerNotFoundException;
import com.cg.salon.exceptions.SalonServiceScheduleNotFoundException;
import com.cg.salon.exceptions.ValidateAppointmentException;
import com.cg.salon.service.IAppointmentService;
import com.cg.util.SalonConstants;

/*
 * @Author - Sreyashi Biswas, Subhajit Saha
 * Description - This rest controller class contains the service implementations regarding Appointment Booking Management
 */

@RestController
public class AppointmentRestController {

	@Autowired
	private IAppointmentService service;
	
	/*
	 * Method Name - viewAppointmentById
	 * Return Type - Appointment
	 * Parameter - appId, Integer, appointment Id
	 * Description - returns the instance for the appointment appointment id
	 * Throws - AppointmentNotFoundException, if the Appointment id does not exist
	 */

	@GetMapping("viewappointmentbyid/{appId}")
	public Appointment viewAppointmentById(@PathVariable("appId") int appId) throws AppointmentNotFoundException {
		return service.viewAppointmentById(appId);
	}
	
	/*
	 * Method Name - viewAppointmentByCustomerId
	 * Return Type - List<Appointment>
	 * Parameter - custId, Integer, customer Id
	 * Description - returns all  the appointments made by one customer
	 * Throws - AppointmentNotFoundException, if no appointments exist for that customer id
	 */

	@GetMapping("viewappointmentbycustomerid/{custid}")
	public List<Appointment> viewAppointmentByCustomerId(@PathVariable("custid") int custId)
			throws AppointmentNotFoundException {
		return service.viewAppointmentByCustomerId(custId);
	}
	
	/*
	 * Method Name - viewAppointmentByScheduleId
	 * Return Type - List<Appointment>
	 * Parameter - serviceScheduleId, Integer, salon service schedule Id
	 * Description - returns all  the appointments made in one schedule
	 * Throws - AppointmentNotFoundException, if no appointments exist for that schedule id
	 */

	@GetMapping("viewappointmentbyscheduleid/{sid}")
	public List<Appointment> viewAppointmentByScheduleId(@PathVariable("sid") int serviceScheduleId)
			throws AppointmentNotFoundException {
		return service.viewAppointmentByScheduleId(serviceScheduleId);
	}
	
	/*
	 * Method Name - addAppointment 
	 * Return Type - Integer
	 * Parameter - appDto, Instance of AppointmentDto
	 * Description - Adds an appointment according to the CustomerId and
	 * SalonServiceScheduleId provided by the User 
	 * Throws - CustomerNotFoundException, if Customer id not found
	 * 			SalonServiceScheduleNotFoundException, if SalonServiceSchedule id not found
	 * 			AppointmentNotFoundException, if the Schedule id does not have any more appointment availability 
	 * 			AppointmentCancelException, if the Schedule id has already been Cancelled previously
	 */

	@PostMapping("addappointment")
	public AppointmentSuccessMessage addAppointment(@Valid @RequestBody AppointmentDto appdto, BindingResult br)
			throws ValidateAppointmentException, CustomerNotFoundException, SalonServiceScheduleNotFoundException,
			AppointmentNotFoundException, AppointmentCancelException {
		if (br.hasErrors())
			throw new ValidateAppointmentException(br.getFieldErrors());

		int aid = service.addAppointment(appdto);

		return new AppointmentSuccessMessage(SalonConstants.APPOINTMENT_CREATED + aid);

	}
	
	/*
	 * Method Name - cancelAppointment
	 * Return Type - boolean
	 * Parameter - appId, Integer, appointment Id
	 * Description - sets the appointment status as Cancelled of the given appointment Id 
	 * Throws - AppointmentNotFoundException, if the Appointment id does not exist
	 * 			AppointmentCancelException, if the Preferred Date is in the past or same as the system date
	 */

	@PutMapping("cancelappointment/{appid}")
	public AppointmentSuccessMessage cancelAppointment(@PathVariable("appid") int appointmentId)
			throws AppointmentNotFoundException, AppointmentCancelException {

		service.cancelAppointment(appointmentId);
		return new AppointmentSuccessMessage(SalonConstants.APPOINTMENT_CANCELLED);
	}
	
	/*
	 * Method Name - viewAllAppointment
	 * Return Type - List<Appointment>
	 * Description - returns all the data in cg_appointment table
	 * Throws - AppointmentNotFoundException, if the appointment table is empty 
	 */

	@GetMapping("viewallappointment")
	public ResponseEntity<List<Appointment>> viewAllAppointments() throws AppointmentNotFoundException {
		List<Appointment> list = service.viewAllAppointment();
		if (list.size() <= 0) {
			ResponseEntity<List<Appointment>> resp = new ResponseEntity<List<Appointment>>(list, HttpStatus.NOT_FOUND);
			return resp;
		} else {
			ResponseEntity<List<Appointment>> resp = new ResponseEntity<List<Appointment>>(list, HttpStatus.OK);
			return resp;
		}
	}

}
