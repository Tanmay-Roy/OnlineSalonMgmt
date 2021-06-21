package com.cg.salon.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.salon.dao.ICustomerDao;
import com.cg.salon.dto.CustomerDto;
import com.cg.salon.entity.Customer;
import com.cg.salon.exceptions.CustomerNotFoundException;
import com.cg.util.SalonConstants;

/*
 * @Author - Madhurima Mallick
 * Description - This service class contains the service implementations regarding Customer Management
 */

@Service("customerservice")
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao customerdao;

	/*
	 * Method Name - addCustomer 
	 * Return Type - Integer 
	 * Parameter - Instance of CustomerDto 
	 * Description - Adds a new Customer
	 */

	@Transactional
	@Override
	public Integer addCustomer(CustomerDto dto) {

		Customer customer = new Customer();

		customer.setName(dto.getName());
		customer.setContactNo(dto.getContactNo());
		customer.setEmail(dto.getEmail());
		customer.setDob(dto.getDob());
		customer.setArea(dto.getArea());
		customer.setCity(dto.getCity());
		customer.setDoorNo(dto.getDoorNo());
		customer.setPincode(dto.getPincode());
		customer.setState(dto.getState());
		customer.setStreet(dto.getStreet());

		Customer savedcustomer = customerdao.save(customer);
		return savedcustomer.getUserId();
	}

	/*
	 * Method Name - viewCustomerById 
	 * Return Type - Customer 
	 * Parameter - customerId
	 * Description - returns the instance for the Customer corresponding to the given customer id 
	 * Throws - CustomerNotFoundException, if the Customer id does not exist
	 */

	@Override
	public Customer viewCustomerById(int cid) throws CustomerNotFoundException {
		Optional<Customer> optservice = customerdao.findById(cid);
		if (!optservice.isPresent())
			throw new CustomerNotFoundException(SalonConstants.CUSTOMER_NOT_EXIST + cid);
		return optservice.get();
	}

	/*
	 * Method Name - viewCustomerByName 
	 * Return Type - List 
	 * Parameter - Customer Name
	 * Description - returns the list of Customer corresponding to the given Customer name 
	 * Throws - CustomerNotFoundException, if the Customer id does not exist
	 */

	@Override
	public List<Customer> viewCustomerByName(String name) throws CustomerNotFoundException {
		List<Customer> lst = customerdao.findByName(name);
		if (lst.isEmpty())
			throw new CustomerNotFoundException(SalonConstants.CUSTOMER_NOT_EXIST);
		return lst;
	}

	/*
	 * Method Name - viewCustomerByCity 
	 * Return Type - List 
	 * Parameter - Customer
	 * City Description - returns the list of Customer corresponding to the given Customer city 
	 * Throws - CustomerNotFoundException, if the Customer id does not exist
	 */

	@Override
	public List<Customer> viewCustomerByCity(String city) throws CustomerNotFoundException {
		List<Customer> lst = customerdao.findByCity(city);
		if (lst.isEmpty())
			throw new CustomerNotFoundException(SalonConstants.CUSTOMER_NOT_EXIST);
		return lst;
	}

	/*
	 * Method Name - viewCustomerByContactNo 
	 * Return Type - List 
	 * Parameter -Customer Contact No. 
	 * Description - returns the list of Customer corresponding to the given Customer Contact no. 
	 * Throws - CustomerNotFoundException, if the Customer id does not exist
	 */

	@Override
	public List<Customer> viewCustomerByContactNo(String contactNo) throws CustomerNotFoundException {
		List<Customer> lst = customerdao.findByContactNo(contactNo);
		if (lst.isEmpty())
			throw new CustomerNotFoundException(SalonConstants.CUSTOMER_NOT_EXIST);
		return lst;
	}

	/*
	 * Method Name - editCustomerDetails 
	 * Return Type - boolean 
	 * Parameter - Instance of CustomerDto 
	 * Description - edits the required fields of an existing Customer 
	 * Throws - CustomerNotFoundException, if the Customer id does not exist
	 */

	@Transactional
	@Override
	public boolean editCustomerDetails(CustomerDto dto) throws CustomerNotFoundException {

		Optional<Customer> optservice = customerdao.findById(dto.getUserId());
		if (!optservice.isPresent())
			throw new CustomerNotFoundException(SalonConstants.CUSTOMER_NOT_EXIST);

		Customer customer = optservice.get();

		customer.setName(dto.getName());
		customer.setContactNo(dto.getContactNo());
		customer.setEmail(dto.getEmail());
		customer.setDob(dto.getDob());
		customer.setArea(dto.getArea());
		customer.setCity(dto.getCity());
		customer.setDoorNo(dto.getDoorNo());
		customer.setPincode(dto.getPincode());
		customer.setState(dto.getState());
		customer.setStreet(dto.getStreet());

		return true;
	}
}
