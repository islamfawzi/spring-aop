package org.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2) /** lower number is first,  can be negative number **/ 
public class SecondAspect {

	@Before("execution(* org.spring.aop.beans.AccountDao.addAccount(..))")
	private void BeforeAspect() {
		System.out.println("\n >>>>>> Second Aspect Called!!");
	}
	
}
