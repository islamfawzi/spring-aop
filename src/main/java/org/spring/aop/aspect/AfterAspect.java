package org.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.spring.aop.beans.Account;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAspect {

	@AfterReturning(pointcut = "execution(* org.spring.aop.beans.AccountDao.getAccount())",
			returning = "result")
	public void afterReturning(JoinPoint joinPoint, Account result) {
		System.out.println("\n >>>>>> @AfterReturning Method signature: " + joinPoint.getSignature());
		System.out.println("\n >>>>>> @AfterReturning Advise, AccountDao.getAccount() Called and return " + result);
		result.setname(result.getName().toUpperCase());
	}
	
	@AfterThrowing(pointcut = "execution(* org.spring.aop.beans.AccountDao.throwException())",
			throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception) {
		System.out.println("\n >>>>>> @AfterThrowing Method signature: " + joinPoint.getSignature());
		System.out.println("\n >>>>>> @AfterThrowing Advise, AccountDao.throwException() Called and throwing " + exception);
	}
	
	/**
	 * @After (finally) advise run regardless success or failure
	 * run before throwing exception (run before @AfterThrowing)
	 * 
	 * Starting from Spring 5.2.7,  
	 * the @After advice method is invoked AFTER any @AfterReturning or @AfterThrowing advice methods in the same aspect class
	 */
	@After("execution(* org.spring.aop.beans.AccountDao.throwException())")
	public void afterFinallyThrowExcetion(JoinPoint joinPoint) {
		System.out.println("\n >>>>>> @After (finally) Method signature: " + joinPoint.getSignature());
		System.out.println("\n >>>>>> @After (finally) Advise, AccountDao.throwException() Called");
	}
	
	/** 
	 * @After (finally) run on success case regardless returning or void
	 * 
	 * Starting from Spring 5.2.7,  
	 * the @After advice method is invoked AFTER any @AfterReturning or @AfterThrowing advice methods in the same aspect class
	 */
	@After("execution(* org.spring.aop.beans.AccountDao.getAccount())")
	public void afterFinallySuccess(JoinPoint joinPoint) {
		System.out.println("\n >>>>>> @After (finally) Method signature: " + joinPoint.getSignature());
		System.out.println("\n >>>>>> @After (finally) Advise, AccountDao.getAccount() Called");
	}
}
