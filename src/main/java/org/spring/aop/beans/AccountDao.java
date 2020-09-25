package org.spring.aop.beans;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class AccountDao {
	
	Account account;
	
	public void addAccount(Account account, boolean flag) {
		this.account = account;
		account.getId();
		account.getName();
		System.out.println("\n" + getClass() + ".addAccount() Called!");
	}
	
	public Account getAccount() {
		System.out.println("\n" + getClass() + ".getAccount() Called!");
		return account;
	}
	
	public void throwException() {
		throw new RuntimeException("Manual thrown exception");
	}
	
	public String getAccountStatus() {
		
		try { TimeUnit.SECONDS.sleep(5); }
		catch (InterruptedException e) {}
		
		return "Account is active";
	}
}
