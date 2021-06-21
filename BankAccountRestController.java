package com.cg.salon.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.salon.dto.BankAccountDto;
import com.cg.salon.dto.BankAccountSuccessMessage;
import com.cg.salon.entity.BankAccount;
import com.cg.salon.exceptions.BankAccountNotFoundException;
import com.cg.salon.exceptions.ValidateBankAccountException;
import com.cg.salon.service.IBankAccountService;
import com.cg.util.SalonConstants;


/*
 * @Author - Ankush Mukherjee
 * Description - This rest controller class contains the service implementations regarding Bank Account Management
 */
@RestController
public class BankAccountRestController {

	@Autowired
	private IBankAccountService service;

	
	/*
	 * Method Name - addBankAccount 
	 * Return Type - Integer 
	 * Parameter - Instance of BankAccountDto 
	 * Description - Adds a new Bank Account
	 */
	@PostMapping("addbankaccountservice")
	public BankAccountSuccessMessage addBankAcc(@Valid @RequestBody BankAccountDto bankdto, BindingResult br)
			throws ValidateBankAccountException {

		if (br.hasErrors())
			throw new ValidateBankAccountException(br.getFieldErrors());
		int bid = service.addBankAccount(bankdto);

		return new BankAccountSuccessMessage(SalonConstants.BANK_ACCOUNT_ADDED + bid);

	}

	
	/*
	 * Method Name - editBankAccount Return Type - Boolean Parameter -
	 * BankAccountDto dto Description - updates the details of the required Bank
	 * Account Throws - BankAccountNotFoundException, if the Bank Account id does
	 * not exist
	 */
	@PutMapping("editbankaccount")
	public BankAccountSuccessMessage editBankAcc(@Valid @RequestBody BankAccountDto bankdto, BindingResult br)
			throws ValidateBankAccountException, BankAccountNotFoundException {
		if (br.hasErrors()) {
			throw new ValidateBankAccountException(br.getFieldErrors());
		}
		service.editBankAccount(bankdto);
		return new BankAccountSuccessMessage(SalonConstants.BANK_ACCOUNT_EDITED);
	}

	
	/*
	 * Method Name - viewBankAccountBycvvNo Return Type - BankAccount Parameter -
	 * cvvNo Description - returns the instance for the BankAccount corresponding to the given BankAccount cvvNo 
	 * Throws - BankAccountNotFoundException, if the
	 * Bank Account id does not exist
	 */
	@GetMapping("viewbybankaccountid/{cvvno}")
	public BankAccount viewBankAccountByCvvNo(@PathVariable("cvvno") int cvvNo) throws BankAccountNotFoundException {
		return service.viewBankAccountBycvvNo(cvvNo);
	}
}
