package com.cg.salon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.salon.dao.ISalonServiceDao;
import com.cg.salon.dao.ISalonServiceScheduleDao;
import com.cg.salon.dto.SalonServiceScheduleDto;
import com.cg.salon.entity.SalonService;
import com.cg.salon.entity.SalonServiceSchedule;
import com.cg.salon.exceptions.SalonServiceNotFoundException;
import com.cg.salon.exceptions.SalonServiceScheduleNotFoundException;
import com.cg.salon.exceptions.ScheduleCancelException;
import com.cg.util.SalonConstants;

/*
 * @Author - Ritobina Sinha
 * Description - This service class contains the service implementations regarding Salon Service Schedule
 * Management
 */

@Service("salonserviceschedule")
public class SalonServiceScheduleImpl implements ISalonServiceSchedule {

	@Autowired
	private ISalonServiceScheduleDao salonservicescheduledao;

	@Autowired
	private ISalonServiceDao salonservicedao;

	Logger logger = LoggerFactory.getLogger(SalonServiceScheduleImpl.class);
	
	/*
	 * Method Name - createSchedule
	 *  Return Type - Integer 
	 *  Parameter - instance of SalonServiceScheduleDto
	 *  Description - Creates a schedule according to the
	 *                SalonServiceScheduleId provided by the admin 
	 *  Throws - SalonServiceNotFoundException,if no salon service exists     
	 */  

	@Transactional
	@Override
	public Integer createSchedule(SalonServiceScheduleDto dto) throws SalonServiceNotFoundException {
		Optional<SalonService> optsalon = salonservicedao.findById(dto.getSalonServiceId());

		if (!optsalon.isPresent()) {
			logger.error(SalonConstants.SALON_SERVICE_NOT_FOUND);
			throw new SalonServiceNotFoundException(SalonConstants.SALON_SERVICE_NOT_FOUND);
		}

		SalonServiceSchedule schedule = new SalonServiceSchedule();

		schedule.setScheduleDate(dto.getScheduleDate());
		schedule.setSlot(dto.getSlot());
		schedule.setSalonService(optsalon.get());
		schedule.setNoofappointments(dto.getNoofappointments());
		schedule.setScheduleStatus(SalonConstants.AVAILABLE);

		SalonServiceSchedule savedschedule = salonservicescheduledao.save(schedule);
		logger.info(SalonConstants.SCHEDULE_ADDED);
		return savedschedule.getServiceScheduleId();
	}

	/*
	 * Method Name - viewSalonServiceScheduleById
	 * Return Type - SalonServiceSchedule
	 * Parameter - sid,integer,salon service schedule id
	 * Description - returns instance for the schedule corresponding to the schedule id
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 */

	@Override
	public SalonServiceSchedule viewSalonServiceScheduleById(int sid) throws SalonServiceScheduleNotFoundException {

		Optional<SalonServiceSchedule> optschedule = salonservicescheduledao.findById(sid);
		if (!optschedule.isPresent())
			throw new SalonServiceScheduleNotFoundException(SalonConstants.SALON_SCHEDULE_NOT_EXIST + sid);
		return optschedule.get();
	}
	
	/*
	 * Method Name - viewSalonServiceScheduleByDate
	 * Return Type - List<SalonServiceSchedule>
	 * Parameter - scheduleDate, LocalDate , Schedule date
	 * Description -  returns schedule by date
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 */

	@Override
	public List<SalonServiceSchedule> viewSalonServiceScheduleByDate(LocalDate scheduleDate)
			throws SalonServiceScheduleNotFoundException {

		List<SalonServiceSchedule> lstschedules = salonservicescheduledao.findByScheduleDate(scheduleDate);
		if (lstschedules.isEmpty())
			throw new SalonServiceScheduleNotFoundException(SalonConstants.SALON_SCHEDULE_EMPTY);
		return lstschedules;
	}
	
	/*
	 * Method Name - viewSalonServiceScheduleByServiceId
	 * Return Type - List<SalonServiceSchedule>
	 * Parameter - serid, integer , Service id
	 * Description - returns schedule by service id
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 */

	@Override
	public List<SalonServiceSchedule> viewSalonServiceScheduleByServiceId(int serid)
			throws SalonServiceScheduleNotFoundException {

		List<SalonServiceSchedule> id = salonservicescheduledao.viewSalonServiceScheduleByServiceId(serid);
		if (id.isEmpty())
			throw new SalonServiceScheduleNotFoundException(SalonConstants.SALON_SCHEDULE_NOT_EXIST);
		return id;

	}

	/*
	 * Method Name - cancelSchedule
	 * Return Type - boolean
	 * Parameter - scheduleId, integer , Schedule id
	 * Description - sets the schedule status as cancelled 
	 * Throws - SalonServiceScheduleNotFoundException, if no salon service schedule id is found
	 *          ScheduleCancelException , if Schedule cannot be cancelled as it must be a future date
	 */
	
	@Transactional
	@Override
	public boolean cancelSchedule(int scheduleId)
			throws SalonServiceScheduleNotFoundException, ScheduleCancelException {

		Optional<SalonServiceSchedule> optservice = salonservicescheduledao.findById(scheduleId);
		if (!optservice.isPresent())
			throw new SalonServiceScheduleNotFoundException(SalonConstants.SALON_SCHEDULE_NOT_EXIST);

		SalonServiceSchedule schedule = optservice.get();
		if (schedule.getScheduleDate().isBefore(LocalDate.now()) || schedule.getScheduleDate().isEqual(LocalDate.now()))
			throw new ScheduleCancelException(SalonConstants.SCHEDULE_NOT_CANCEL);
		schedule.setScheduleStatus(SalonConstants.CANCELLED);

		salonservicescheduledao.save(schedule);

		return true;
	}
	
}