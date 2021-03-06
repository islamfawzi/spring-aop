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
		account.setname("islam");
		
		
		// should call the @Before advise before calling the addAccount() method
		accountDao.addAccount(account, true);
		
//		System.out.println("\n call it again >>> ");
//		
//		// should call the @Before advise again before calling the addAccount() method
//		accountDao.addAccount(account, false);
		
		/** trigger @AfterReturning, @After advise **/
		Account acc = accountDao.getAccount();
		
		/** trigger @AfterThrowing, @After advise **/
		try { accountDao.throwException(); }
		catch(RuntimeException ex) {}
		
		/** trigger @Around advise **/
		String accountStatus = accountDao.getAccountStatus();
		System.out.println("\n MainApp >>>> Account Status: " + accountStatus);
		
		/** handle exception with @Around advise **/
		String result = accountDao.throwExceptionAndReturn(true);
		System.out.println("\n MainApp >>>> throwExceptionAndReturn Result: " + result);
		
		applicationContext.close();
	}

}
