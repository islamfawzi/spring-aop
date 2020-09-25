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
	
	/**
	 * handle the thrown exception 
	 * the MainApp does not know that exception thrown
	 * because it catched here
	 * you can return result or re-throw it again
	 * @throws Throwable 
	 */
	@Around("execution(* org.spring.aop.beans.AccountDao.throwExceptionAndReturn(..))")
	public Object handleTheException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("\n >>>>>> @Around Method Signature: " + proceedingJoinPoint.getSignature().getName());
		
		Object result = null;
		
		try {
			
			result = proceedingJoinPoint.proceed();
			
		} catch (Throwable ex) {	
			
			System.out.println("\n >>>>>> @Around Thrown Exception: " + ex);
			
			// return Error result
			result = "Error";
			
			// re-throw the exception
//			throw ex;
		} 
		
		return result;
	}
}
