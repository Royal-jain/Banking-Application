package com.spring.rest.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*@Entity
@Table(name="Account_table")*/
public class AccountDetails {
	
	/*
	 * @Id
	 * 
	 * @GeneratedValue
	 */
	private int id;
	
	private String name;
	
	private String transactionId;
	private String vendor;
	private BigInteger accountNumber;
	private Long adharNumber;
	private Long mobileNumber;
	private String adressPermanent;
	private String city;
	private String zipCode;
	private String eMail;
	
	public String getAdressPermanent() {
		return adressPermanent;
	}
	public void setAdressPermanent(String adressPermanent) {
		this.adressPermanent = adressPermanent;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setMaxwithdrowLimit(long maxwithdrowLimit) {
		this.maxwithdrowLimit = maxwithdrowLimit;
	}
	private String accountType;
	private BigDecimal ammount;
	private Date created_Date;
	private Date updatedDate;
	private long maxwithdrowLimit;
	private Date paymentDate;
	//private double withdrowlAmmount;
	
	public Long getMaxwithdrowLimit() {
		return maxwithdrowLimit;
	}
	public void setMaxwithdrowLimit(Long maxwithdrowLimit) {
		this.maxwithdrowLimit = maxwithdrowLimit;
	}
	
	
	public BigDecimal getAmmount() {
		return ammount;
	}
	public void setAmmount(BigDecimal ammount) {
		this.ammount = ammount;
	}
	public Date getCreated_Date() {
		return created_Date;
	}
	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}
	public BigInteger getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(BigInteger accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Long getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(Long adharNumber) {
		this.adharNumber = adharNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
