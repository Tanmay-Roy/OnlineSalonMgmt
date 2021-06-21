package com.cg.salon.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.salon.dao.IBankAccountDao;
import com.cg.salon.dto.BankAccountDto;
import com.cg.salon.entity.BankAccount;
import com.cg.salon.exceptions.BankAccountNotFoundException;
import com.cg.util.SalonConstants;

/*
 * @Author - Ankush Mukherjee
 * Description - This service class contains the service implementations regarding Bank Account Management
 */
@Service("BankAccount")
public class BankAccountServiceImpl implements IBankAccountService {

	@Autowired
	private IBankAccountDao bankAccountdao;

	/*
	 * Method Name - addBankAccount 
	 * Return Type - Integer 
	 * Parameter - Instance of BankAccountDto 
	 * Description - Adds a new Bank Account
	 */

	@Override
	@Transactional
	public Integer addBankAccount(BankAccountDto dto) {

		BankAccount bankacc = new BankAccount();

		bankacc.setAmount(dto.getAmount());
		bankacc.setBankName(dto.getBankName());
		bankacc.setCardName(dto.getCardName());
		bankacc.setCardNumber(dto.getCardnumber());
		bankacc.setCvvNo(dto.getCvvNo());
		bankacc.setIfscNo(dto.getIfscNo());
		bankacc.setExpiryDate(dto.getExpiryDate());

		BankAccount savedbankacc = bankAccountdao.save(bankacc);
		return savedbankacc.getCvvNo();

	}

	/*
	 * Method Name - viewBankAccountBycvvNo Return Type - BankAccount Parameter -
	 * cvvNo Description - returns the instance for the BankAccount corresponding to the given BankAccount cvvNo 
	 * Throws - BankAccountNotFoundException, if the
	 * Bank Account id does not exist
	 */

	@Override
	public BankAccount viewBankAccountBycvvNo(int cvvNo) throws BankAccountNotFoundException {

		Optional<BankAccount> optservice = bankAccountdao.findById(cvvNo);
		if (!optservice.isPresent())
			throw new BankAccountNotFoundException(SalonConstants.BANK_ACCOUNT_NOT_FOUND);
		return optservice.get();

	}

	/*
	 * Method Name - editBankAccount Return Type - Boolean Parameter -
	 * BankAccountDto dto Description - updates the details of the required Bank
	 * Account Throws - BankAccountNotFoundException, if the Bank Account id does
	 * not exist
	 */
	@Override
	@Transactional
	public boolean editBankAccount(BankAccountDto dto) throws BankAccountNotFoundException {

		Optional<BankAccount> optservice = bankAccountdao.findById(dto.getCvvNo());
		if (!optservice.isPresent())

			throw new BankAccountNotFoundException(SalonConstants.BANK_ACCOUNT_NOT_FOUND);

		BankAccount bankacc = optservice.get();
		bankacc.setAmount(dto.getAmount());
		bankacc.setBankName(dto.getBankName());
		bankacc.setCardName(dto.getCardName());
		bankacc.setCardNumber(dto.getCardnumber());
		bankacc.setCvvNo(dto.getCvvNo());
		bankacc.setIfscNo(dto.getIfscNo());
		bankacc.setExpiryDate(dto.getExpiryDate());
		bankAccountdao.save(bankacc);
		return true;
	}

}
