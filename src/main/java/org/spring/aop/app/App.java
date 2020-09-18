package org.spring.aop.app;

import org.spring.aop.beans.AccountDao;
import org.spring.aop.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		AccountDao accountDao = applicationContext.getBean(AccountDao.class);
		
		// should call the @Before advise before calling the addAccount() method
		accountDao.addAccount();
		
		System.out.println("\n call it again >>> ");
		
		// should call the @Before advise again before calling the addAccount() method
		accountDao.addAccount();
		
		applicationContext.close();
	}

}
