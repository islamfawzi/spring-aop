package org.spring.aop.beans;

import org.springframework.stereotype.Component;

@Component
public class AccountDao {
	
	public void addAccount(Account account, boolean flag) {
		account.getId();
		account.getAccountNum();
		System.out.println("\n" + getClass() + ".addAccount() Called!");
	}
}
