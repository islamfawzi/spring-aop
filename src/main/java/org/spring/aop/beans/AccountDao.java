package org.spring.aop.beans;

import org.springframework.stereotype.Component;

@Component
public class AccountDao {
	
	public void addAccount(Account account, boolean flag) {
		System.out.println("\n" + getClass() + ".addAccount() Called!");
	}
}
