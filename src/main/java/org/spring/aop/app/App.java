package org.spring.aop.app;

import org.spring.aop.beans.Account;
import org.spring.aop.beans.AccountDao;
import org.spring.aop.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		AccountDao accountDao = applicationContext.getBean("accountDao", AccountDao.class);
		
		Account account = applicationContext.getBean("account", Account.class);
		account.setId(9999);
		account.setAccountNum("123456789");
		
		
		// should call the @Before advise before calling the addAccount() method
		accountDao.addAccount(account, true);
		
//		System.out.println("\n call it again >>> ");
//		
//		// should call the @Before advise again before calling the addAccount() method
//		accountDao.addAccount(account, false);
		
		applicationContext.close();
	}

}
