package org.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundAspect {

	@Around("execution(* org.spring.aop.beans.AccountDao.getAccountStatus(..))")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) {
		
		System.out.println("\n >>>>>> @Around Method signature: " + proceedingJoinPoint.getSignature().getName());
		
		// get start time in milliseconds
		long start = System.currentTimeMillis();
		
		/** execute the method, and return the result **/
		Object result = null;
		try { result = proceedingJoinPoint.proceed(); }
		catch (Throwable e) {}	
		
		// get end time in milliseconds
		long end = System.currentTimeMillis();
		
		// get method execution duration
		long duration = end - start;
		System.out.println("\n >>>>>> @Around Duration: " + duration/1000 + " seconds");
		System.out.println("\n >>>>>> @Around Result: " + result);
		
		
		return result;
	}
}
