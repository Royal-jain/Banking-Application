package com.spring.rest.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.dto.Response;
import com.spring.rest.model.AccountDetails;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/application-context.xml","file:src/main/webapp/WEB-INF/springRest-servlet.xml"})
@WebAppConfiguration
public class MyBankTest {

	private MockMvc mockMvc;
	 
    @Autowired
    private WebApplicationContext context;
    ObjectMapper om = new ObjectMapper();
    
    @Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

    @Test
	public void createAccounts() throws Exception {
    	System.out.println("inside createAccounts");
    	AccountDetails accountDetails = new AccountDetails();
    	accountDetails.setName("Royal jain");
    	accountDetails.setAdharNumber(987698769876l);
    	accountDetails.setAccountType("savings");
    	
		String jsonRequest = om.writeValueAsString(accountDetails);
		MvcResult result = mockMvc.perform(post("/myBank/createAccounts")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		System.out.println("Response:: "+resultContent );
		Assert.assertTrue(response.getErrorStatus().equalsIgnoreCase("0"));

	}
    
    @Test
	public void cashDeposits() throws Exception {
    	System.out.println("inside cashDeposits");
    	AccountDetails accountDetails = new AccountDetails();
    	accountDetails.setName("Royal jain");
    	accountDetails.setAdharNumber(987698769876l);
    	accountDetails.setAccountType("savings");
    	accountDetails.setAccountNumber(new BigInteger("40290617987671900000"));
    	accountDetails.setAmmount(new BigDecimal("5656565.45"));
    	
		String jsonRequest = om.writeValueAsString(accountDetails);
		MvcResult result = mockMvc.perform(put("/myBank/cashDeposits")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		System.out.println("Response:: "+resultContent );
		Assert.assertTrue(response.getErrorStatus().equalsIgnoreCase("0"));

	}

    
    @Test
	public void balanceEnquiry() throws Exception {
    	System.out.println("inside balanceEnquiry");
    	AccountDetails accountDetails = new AccountDetails();
    	accountDetails.setName("Royal jain");
    	accountDetails.setAdharNumber(987698769876l);
    	accountDetails.setAccountType("savings");
    	accountDetails.setAccountNumber(new BigInteger("40290617987671900000"));
    	//accountDetails.setAmmount(new BigDecimal("565.45"));
    	
		String jsonRequest = om.writeValueAsString(accountDetails);
		MvcResult result = mockMvc.perform(get("/myBank/balanceEnquiry")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		System.out.println("Response balanceEnquiry:: "+resultContent );
		Assert.assertTrue(response.getErrorStatus().equalsIgnoreCase("0"));

	}

    
    @Test
	public void cashWithdrawals() throws Exception {
    	System.out.println("inside cashWithdrawals");
    	AccountDetails accountDetails = new AccountDetails();
    	accountDetails.setName("Royal jain");
    	accountDetails.setAdharNumber(987698769876l);
    	accountDetails.setAccountType("savings");
    	accountDetails.setAccountNumber(new BigInteger("40290617987671900000"));
    	accountDetails.setAmmount(new BigDecimal("565.45"));
    	
		String jsonRequest = om.writeValueAsString(accountDetails);
		MvcResult result = mockMvc.perform(put("/myBank/cashWithdrawals")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		System.out.println("Response cashWithdrawals:: "+resultContent );
		Assert.assertTrue(response.getErrorStatus().equalsIgnoreCase("0"));

	}
}
