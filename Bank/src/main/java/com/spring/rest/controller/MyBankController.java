package com.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.rest.dto.Response;
import com.spring.rest.model.AccountDetails;
import com.spring.rest.service.AccountService;

@RestController
@RequestMapping("/myBank")
public class MyBankController {

	@Autowired
	private AccountService service;

	@PostMapping("/createAccounts")
	public Response createAccounts(@RequestBody AccountDetails accDetails) {
		Response resp=new Response();
		resp=service.validateBasicDetails(accDetails, 1);
		System.out.println("error1:: "+resp.getErrorMessage());
		return ((null != resp.getErrorMessage() && !"".equalsIgnoreCase(resp.getErrorMessage()))
				?resp:service.createAccounts(accDetails));
		//return resp;
	}


	@PutMapping("/cashDeposits") 
	public Response cashDeposits(@RequestBody AccountDetails accDetails) { 
		Response resp=service.validateBasicDetails(accDetails, 2);
		System.out.println("error:: "+resp.getErrorMessage()); 
		return (null !=resp.getErrorMessage() && !"".equalsIgnoreCase(resp.getErrorMessage()))
				?resp:service.cashDeposits(accDetails); 
	}

	@PutMapping("/cashWithdrawals") 
	public Response cashWithdrawals(@RequestBody AccountDetails accDetails) { 
		Response resp=service.validateBasicDetails(accDetails, 3); 
		return (null != resp.getErrorMessage() && !"".equalsIgnoreCase(resp.getErrorMessage()))
				?resp:service.cashWithdrawals(accDetails); 
	}

	@GetMapping("/balanceEnquiry") 
	public Response balanceEnquiry(@RequestBody AccountDetails accDetails) { 
		Response resp=service.validateBasicDetails(accDetails, 4); 
		return (null != resp.getErrorMessage() && !"".equalsIgnoreCase(resp.getErrorMessage()))
				?resp:service.balanceEnquiry(accDetails); 
	}

}
