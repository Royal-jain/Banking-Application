package com.spring.rest.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.rest.dao.Dao;
import com.spring.rest.dto.Response;
import com.spring.rest.model.AccountDetails;

@Service
public class AccountService {
	
	@Autowired
	private Dao dao;
	
	Response response = new Response();

	/*
	 * public Response pay(AccountDetails dlts) { dlts.setPaymentDate(new Date());
	 * String message = "";//dao.payNow(dlts);
	 * 
	 * response.setMessage("success"); response.setMessage(message);
	 * //response.setTxDate(new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a").format(new
	 * Date())); return response; }
	 */

	public Response getTx(String vendor) {
		Response response = new Response();
		//List<Payment> payments = dao.getTransactionInfo(vendor);
		response.setMessage("succes");
		//response.setPayments(payments);
		return response;
	}

	public Response createAccounts(AccountDetails accDetails) {
		// TODO Auto-generated method stub
		response=dao.createAccount(accDetails);
		return response;
			
	}

	public Response cashDeposits(AccountDetails accDetails) {
		// TODO Auto-generated method stub
		
		response=dao.cashDeposits(accDetails);
		return response;
	}

	public Response cashWithdrawals(AccountDetails accDetails) {
		// TODO Auto-generated method stub
		response=dao.cashWithdrawals(accDetails);
		return response;
	}

	public Response balanceEnquiry(AccountDetails accDetails) {
		// TODO Auto-generated method stub
		response=dao.balanceEnquiry(accDetails);
		return response;
	}

	public Response validateBasicDetails(AccountDetails accDetails, int operation) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		List<String> errorList=new ArrayList<String>();
		System.out.println("inside validateBasicDetails:: "+accDetails.getAccountNumber()+" name:: "+accDetails.getName()+" adhar:: "+accDetails.getAdharNumber());
		if(null == accDetails.getName() || "".equalsIgnoreCase(accDetails.getName()))
			errorList.add("Name");
		if(null == accDetails.getAccountType() || "".equalsIgnoreCase(accDetails.getAccountType()))
			errorList.add("Account Type");
		
		switch(operation){
		case 1://createAccounts
			if(null == accDetails.getAdharNumber()  || 12 != accDetails.getAdharNumber().toString().length())
				errorList.add("Valid Adhar Number"); 	
			break;
		case 2 & 3://cashDeposits	//cashWithdrawals		
			if(null == accDetails.getAdharNumber()  || 12 != accDetails.getAdharNumber().toString().length())
				errorList.add("Valid Adhar Number");
			System.out.println("case 2 & 3::> "+accDetails.getAccountNumber()+" ammount:: "+accDetails.getAmmount());
			if(  20 > String.valueOf(accDetails.getAccountNumber()).length())
				errorList.add("Valid Account Number");
			if(accDetails.getAmmount().compareTo(BigDecimal.ZERO)<0)
				errorList.add("Ammount and should be greater than 0");
			break;
		
		case 4://balanceEnquiry
			if(20 > String.valueOf(accDetails.getAccountNumber()).length())
				errorList.add("Account Number");
			break;

		}
		System.out.println("errorList:: "+errorList.size());
		if(!errorList.isEmpty()) {
			sb.append("Please provide ");
			String prefix="";
			for(String msg:errorList) {
				sb.append(prefix);
				prefix = ", ";
				sb.append(msg);
			}
			System.out.println("sb:: "+sb);
			response.setErrorMessage(sb.append(".").toString());
			response.setErrorStatus("1");
			response.setTransactionId(accDetails.getTransactionId());
		}
		
		return response;


	}

	
}
