package org.spring.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3) /** lower number is first,  can be negative number **/ 
public class ThirdAspect {

	@Before("execution(* org.spring.aop.beans.AccountDao.addAccount(..))")
	private void BeforeAspect() {
		System.out.println("\n >>>>>> Third Aspect Called!!");
	}
	
}
