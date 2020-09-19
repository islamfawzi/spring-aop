package org.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) /** lower number is first,  can be negative number **/ 
public class FirstAspect {

	@Before("execution(* org.spring.aop.beans.AccountDao.addAccount(..))")
	private void BeforeAspect() {
		System.out.println("\n >>>>>> First Aspect Called!!");
	}
	
}
