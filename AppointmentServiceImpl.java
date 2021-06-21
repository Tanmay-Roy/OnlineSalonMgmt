package com.cg.salon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.salon.dao.IAppointmentDao;
import com.cg.salon.dao.ICustomerDao;
import com.cg.salon.dao.ISalonServiceScheduleDao;
import com.cg.salon.dto.AppointmentDto;
import com.cg.salon.entity.Appointment;
import com.cg.salon.entity.Customer;
import com.cg.salon.entity.SalonServiceSchedule;
import com.cg.salon.exceptions.AppointmentCancelException;
import com.cg.salon.exceptions.AppointmentNotFoundException;
import com.cg.salon.exceptions.CustomerNotFoundException;
import com.cg.salon.exceptions.SalonServiceScheduleNotFoundException;
import com.cg.util.SalonConstants;

/*
 * @Author - Sreyashi Biswas, Subhajit Saha
 * Description - This service class contains the service implementations regarding Appointment Booking Management
 */

@Service("appointment")
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private IAppointmentDao appointmentdao;

	@Autowired
	private ICustomerDao customerdao;

	@Autowired
	private ISalonServiceScheduleDao scheduledao;

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

	@Override
	public Integer addAppointment(AppointmentDto appdto) throws CustomerNotFoundException,
			SalonServiceScheduleNotFoundException, AppointmentNotFoundException, AppointmentCancelException {

		Optional<Customer> optcust = customerdao.findById(appdto.getCustId());
		if (!optcust.isPresent())
			throw new CustomerNotFoundException(SalonConstants.CUSTOMER_NOT_FOUND);

		Optional<SalonServiceSchedule> optschedule = scheduledao.findById(appdto.getScheduleId());
		if (!optschedule.isPresent())
			throw new SalonServiceScheduleNotFoundException(SalonConstants.SALON_SCHEDULE_NOT_EXIST);

		SalonServiceSchedule schedule = optschedule.get();
		if (schedule.getScheduleStatus().equals(SalonConstants.CANCELLED))
			throw new AppointmentCancelException(SalonConstants.SCHEDULE_CANCELLED_PREVIOUSLY);
		if (schedule.getNoofappointments() <= SalonConstants.ZERO)
			throw new AppointmentNotFoundException(SalonConstants.SCHEDULE_SLOT_FULL);

		Appointment appointment = new Appointment();
		appointment.setAppointmentStatus(SalonConstants.APPOINTMENT_CREATED);
		appointment.setPreferredDate(appdto.getPreferredDate());
		appointment.setCustomer(optcust.get());
		appointment.setSalonServiceSchedule(optschedule.get());
		Appointment savedAppointment = appointmentdao.save(appointment);
		schedule.setNoofappointments(schedule.getNoofappointments() - SalonConstants.ONE);
		scheduledao.save(schedule);
		return savedAppointment.getAppointmentId();

	}

	/*
	 * Method Name - cancelAppointment
	 * Return Type - boolean
	 * Parameter - appId, Integer, appointment Id
	 * Description - sets the appointment status as Cancelled of the given appointment Id 
	 * Throws - AppointmentNotFoundException, if the Appointment id does not exist
	 * 			AppointmentCancelException, if the Preferred Date is in the past or same as the system date
	 */

	@Override
	@Transactional
	public boolean cancelAppointment(int appId) throws AppointmentNotFoundException,
			AppointmentCancelException {

		Optional<Appointment> optapp = appointmentdao.findById(appId);
		if (!optapp.isPresent())
			throw new AppointmentNotFoundException(SalonConstants.APPOINTMENT_NOT_FOUND);

		Appointment appointment = optapp.get();
		if (appointment.getPreferredDate().isBefore(LocalDate.now())
				|| appointment.getPreferredDate().isEqual(LocalDate.now()))
			throw new AppointmentCancelException(SalonConstants.APPOINTMENT_NOT_CANCELLED);
		appointment.setAppointmentStatus(SalonConstants.APPOINTMENT_CANCELLED);

		appointmentdao.save(appointment);
		return true;
	}
	
	/*
	 * Method Name - viewAllAppointment
	 * Return Type - List<Appointment>
	 * Description - returns all the data in cg_appointment table
	 * Throws - AppointmentNotFoundException, if the appointment table is empty 
	 */

	@Override
	public List<Appointment> viewAllAppointment() throws AppointmentNotFoundException {
		List<Appointment> lst = appointmentdao.findAll();
		if (lst.isEmpty()) {
			throw new AppointmentNotFoundException(SalonConstants.APPOINTMENT_NOT_FOUND);
		}
		return lst;
	}
	
	/*
	 * Method Name - viewAppointmentById
	 * Return Type - Appointment
	 * Parameter - appId, Integer, appointment Id
	 * Description - returns the instance for the appointment appointment id
	 * Throws - AppointmentNotFoundException, if the Appointment id does not exist
	 */

	@Override
	public Appointment viewAppointmentById(int appId) throws AppointmentNotFoundException {

		Optional<Appointment> optappointment = appointmentdao.findById(appId);
		if (!optappointment.isPresent())
			throw new AppointmentNotFoundException(SalonConstants.APPOINTMENT_NOT_FOUND + appId);
		return optappointment.get();
	}
	
	/*
	 * Method Name - viewAppointmentByCustomerId
	 * Return Type - List<Appointment>
	 * Parameter - custId, Integer, customer Id
	 * Description - returns all  the appointments made by one customer
	 * Throws - AppointmentNotFoundException, if no appointments exist for that customer id
	 */

	@Override
	public List<Appointment> viewAppointmentByCustomerId(int custId) throws AppointmentNotFoundException {

		List<Appointment> list = appointmentdao.findByCustomerId(custId);
		if (list.isEmpty())
			throw new AppointmentNotFoundException(SalonConstants.NO_APPOINTMENT_FOUND_FOR_CUSTOMER_ID + custId);
		return list;
	}
	
	/*
	 * Method Name - viewAppointmentByScheduleId
	 * Return Type - List<Appointment>
	 * Parameter - serviceScheduleId, Integer, salon service schedule Id
	 * Description - returns all  the appointments made in one schedule
	 * Throws - AppointmentNotFoundException, if no appointments exist for that schedule id
	 */

	@Override
	public List<Appointment> viewAppointmentByScheduleId(int serviceScheduleId) throws AppointmentNotFoundException {

		List<Appointment> list = appointmentdao.findByScheduleId(serviceScheduleId);
		if (list.isEmpty())
			throw new AppointmentNotFoundException(
					SalonConstants.NO_APPOINTMENT_FOUND_FOR_SCHEDULE_ID + serviceScheduleId);
		return list;

	}

}
