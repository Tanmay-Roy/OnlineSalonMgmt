package com.cg.salon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.salon.entity.Customer;

@Repository("customerdao")
public interface ICustomerDao extends JpaRepository<Customer, Integer> {

	public List<Customer> findByName(String name);

	public List<Customer> findByCity(String city);

	public List<Customer> findByContactNo(String contactNo);

}
