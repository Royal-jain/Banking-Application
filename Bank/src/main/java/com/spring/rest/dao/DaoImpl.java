package com.spring.rest.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.spring.rest.dto.Response;
import com.spring.rest.model.AccountDetails;

@Repository("Dao")
public class DaoImpl implements Dao{
	
	
	@Autowired 
	private SessionFactory factory;


	Response response=new Response();
	/*
	 * private Session getSession() { Session session = null; try { session
	 * =factory.openSession(); System.out.println("SessionFactory loaded"); } catch
	 * (HibernateException ex) { session =factory.openSession(); } return session; }
	 */
	public Response createAccount(AccountDetails accDetails) {
		boolean rs=false;
		 //Transaction transaction = null;
		Session session = null;
		int isCreated;
		try {
			Random rand = new Random();
			String account_no = rand.nextInt(99999999) + accDetails.getAdharNumber().toString().substring(8, 12)  + rand.nextInt(99999999) ;
			System.out.println(account_no);
			session=factory.openSession();
			Query q=session.createSQLQuery("INSERT INTO ACCOUNT_DETAILS (ID , NAME, "
					+ "ACC_NUMBER , ADHAR_ID, MOBILE_NUMBER, ACC_TYPE,AVL_BALANCE, "
					+ " ADRESS,CITY,PIN_CODE, EMAIL_ID, LAST_TRAN_DATE , CREATED_DATE,"
					+ " UPDATED_DATE ) VALUES (ACCOUNT_DETAILS_SEQ.NEXTVAL, ?,?,?,"
					+ ""+accDetails.getMobileNumber()+",?,"+accDetails.getAmmount()+","+accDetails.getAdressPermanent()+""
					+ ","+accDetails.getCity()+","+accDetails.getZipCode()+","
					+ ""+accDetails.geteMail()+",sysdate,sysdate,sysdate)");
			q.setParameter(0, accDetails.getName());
			q.setDouble(1, Double.valueOf(account_no));
			q.setLong(2, accDetails.getAdharNumber());
			//q.setBigInteger(3, (BigInteger) ((null !=accDetails.getMobileNumber())?accDetails.getMobileNumber(): BigInteger.ZERO));
			q.setParameter(3, accDetails.getAccountType());
			
			isCreated=q.executeUpdate();
			System.out.println("isCreated:: "+isCreated);
			if(isCreated>0)
			{
				response.setName(accDetails.getName());
				response.setAccountNumber(account_no);
				response.setTransactionId(accDetails.getTransactionId());
				response.setErrorStatus("0");
				response.setMessage("Your Account has been created succsessfully");
			}else {
				response.setName(accDetails.getName());
				response.setTransactionId(accDetails.getTransactionId());
				response.setErrorStatus("1");
				response.setMessage("Incorrect Details.");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setName(accDetails.getName());
			response.setTransactionId(accDetails.getTransactionId());
			response.setErrorStatus("1");
			response.setMessage("Internal error");
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return response;
		// TODO Auto-generated method stub		
	}


	public Response cashWithdrawals(AccountDetails accDetails) {
		boolean rs=false;
		//Transaction transaction = null;
		Session session = null;
		int isWithdrowal=0;
		BigDecimal currentBalance;
		try {
			response.setName(accDetails.getName());
			response.setTransactionId(accDetails.getTransactionId());
			response.setAccountNumber(String.valueOf(accDetails.getAccountNumber()));
			session=factory.openSession();			
			Query q1=session.createSQLQuery("select AVL_BALANCE from ACCOUNT_DETAILS where ACC_NUMBER =? and ADHAR_ID=? and ACC_TYPE =?");
			q1.setBigInteger(0, accDetails.getAccountNumber());
			q1.setLong(1, accDetails.getAdharNumber());
			q1.setParameter(2, accDetails.getAccountType());
			List list=q1.list(); 
			System.out.println(list.size()); 
			if(!list.isEmpty()) {
				BigDecimal availableAmmount=(BigDecimal) list.get(0); 
				if(availableAmmount.compareTo(accDetails.getAmmount())>0) {
					currentBalance=availableAmmount.subtract(accDetails.getAmmount());
					Query q=session.createSQLQuery("update ACCOUNT_DETAILS set AVL_BALANCE=?, LAST_TRAN_DATE=UPDATED_DATE, UPDATED_DATE=SYSDATE where ACC_NUMBER =? and ADHAR_ID=? and ACC_TYPE =?");
					q.setBigDecimal(0, currentBalance);
					q.setBigInteger(1, accDetails.getAccountNumber());
					q.setLong(2, accDetails.getAdharNumber());
					q.setParameter(3, accDetails.getAccountType());
					isWithdrowal=q.executeUpdate();
					if(isWithdrowal>0) {
						response.setAvailableBalance(currentBalance);
						response.setErrorStatus("0");
						response.setMessage("Your Balance is added succsessfully.");
						return response;
					}
				}else {
					response.setAvailableBalance(availableAmmount);
					response.setErrorStatus("1");
					response.setErrorMessage("no Sufficient fund");
				}
			}else {
				response.setErrorStatus("1");
				response.setMessage("Incorrect account Details");
			}
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setErrorStatus("1");
			response.setMessage("Bank Internal Error");
		}finally {
			session.close();
		}
		return response;
	}

		public Response cashDeposits(AccountDetails accDetails) {
			boolean rs=false;
			//Transaction transaction = null;
			Session session = null;
			int isUpadated = 0;
			try {
				response.setName(accDetails.getName());
				response.setAccountNumber(String.valueOf(accDetails.getAccountNumber()));
				response.setTransactionId(accDetails.getTransactionId());
				session=factory.openSession();
				//transaction=session.beginTransaction();
				System.out.println("accDetails.getAmount():: "+accDetails.getAmmount());
				Query q=session.createSQLQuery("update ACCOUNT_DETAILS set AVL_BALANCE=AVL_BALANCE+?, LAST_TRAN_DATE=UPDATED_DATE, UPDATED_DATE=SYSDATE where ACC_NUMBER =? and ADHAR_ID=?");
				q.setBigDecimal(0, accDetails.getAmmount());
				q.setBigInteger(1, accDetails.getAccountNumber());
				q.setLong(2, accDetails.getAdharNumber());
				isUpadated=q.executeUpdate();
				//transaction.commit();
				System.out.println("isUpadated1:: "+isUpadated+"  q::: "+q);
				if(isUpadated ==1) {
					response.setErrorStatus("0");
					response.setMessage("Your Balance is added succsessfully.");
				}else {
					response.setErrorStatus("1");
					response.setMessage("Please Check your Details.");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				response.setErrorStatus("1");
				response.setMessage("Bank Internal Error");
				e.printStackTrace();
				
			}finally {
				session.close();
			}

			return response;
			// TODO Auto-generated method stub}
		}


		public Response balanceEnquiry(AccountDetails accDetails) {
			//Transaction transaction = null;
			Session session = null;
			List list=new ArrayList();
			try {
				response.setName(accDetails.getName());
				response.setTransactionId(accDetails.getTransactionId());
				response.setAccountNumber(String.valueOf(accDetails.getAccountNumber()));
				session=factory.openSession();			
				Query q1=session.createSQLQuery("select AVL_BALANCE FROM ACCOUNT_DETAILS where ACC_NUMBER=? and ACC_TYPE=?");
				q1.setBigInteger(0, accDetails.getAccountNumber());
				//q1.setLong(1, accDetails.getAdharNumber());
				q1.setParameter(1, accDetails.getAccountType());
				list=q1.list(); 
				System.out.println(list.size()); 
				if(!list.isEmpty()) {
					BigDecimal availableAmmount=(BigDecimal) list.get(0);
					response.setAvailableBalance(availableAmmount);
					response.setErrorStatus("0");
					//response.setMessage("");
					return response;

				}else {
						response.setErrorStatus("1");
						response.setErrorMessage("Invalid Account details");
				return response;
			}
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.setErrorStatus("1");
				response.setMessage("Bank Internal Error");
			}finally {
				session.close();
			}
			return response;
		}
		
}
