package org.spring.aop.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogginAspect {

	Logger logger = Logger.getLogger(getClass().toString());
	
	//Advise //PointCut expression language
	@Before("execution(public void addAccount())")
	public void before() {
		
		System.out.println("\n >>>>>> @Before advise execution addAccount()");
	}
}
