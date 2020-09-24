package org.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
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
		System.out.println("\n >>>>>> @@AfterThrowing Method signature: " + joinPoint.getSignature());
		System.out.println("\n >>>>>> @@AfterThrowing Advise, AccountDao.throwException() Called and throwing " + exception);
	}
}
