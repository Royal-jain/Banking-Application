package com.spring.rest.dao;

import com.spring.rest.dto.Response;
import com.spring.rest.model.AccountDetails;


public interface Dao {

	//List<AccountDetails> getTransactionInfo(String name);
	
	Response createAccount(AccountDetails accDetails);

	Response cashWithdrawals(AccountDetails accDetails);

	Response cashDeposits(AccountDetails accDetails);

	Response balanceEnquiry(AccountDetails accDetails);

}
