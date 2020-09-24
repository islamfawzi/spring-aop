package org.spring.aop.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(4)
public class LogginAspect {

	Logger logger = Logger.getLogger(getClass().toString());
	
	/**
	 * PointCut expression language 
	 * To define execution PointCut
	 * "execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?)"
	 * 
	 * 1- modifiers-pattern: (spring-aop support only public or *) to match
	 * 2- return-type-pattern: void - int, String .. etc to match
	 * 3- declaring-type-pattern: Full qualified class name to match
	 * 4- method-name-pattern: method name to match
	 * 5- param-pattern: parameters type to match [() no args - (*) one param of any type - (..) 0 or more params of any type
	 * 6- throws-pattern: Exception type to match
	 * 
	 * ? -> optional
	 * can use wildCard (*) match anything
	 * 
	 * 
	 */
	
//	@Advise("PointCut expression language")
//	@Before("execution(public void org.spring.aop.beans.AccountDao.addAccount())") // match the addAccount method in AccountDao class
//	@Before("execution(public void addAccount())") // match the addAccount method in any class
//	@Before("execution(public void add*())") // match any method in any class starting with "add"
//	@Before("execution(* add*())") // match any modifier (optional), any return type in any class starting with "add"
//	@Before("execution(* *())") // match any modifier (optional), any return type, any method in any class
//	@Before("execution(* *(String))") // match any modifier (optional), any return type, any method in any class with param of String type
//	@Before("execution(* *(org.spring.aop.beans.Account))") // match any modifier (optional), any return type, any method in any class with param of Account type
//	@Before("execution(* *(*))") // match any modifier (optional), any return type, any method in any class with one param of any type
//	@Before("execution(* *(org.spring.aop.beans.Account, boolean))") // match any modifier (optional), any return type, any method in any class with 2 params of Account and boolean types
//	@Before("execution(* *(org.spring.aop.beans.Account, *))") // match any modifier (optional), any return type, any method in any class with 1st param of Account type and any other one param of any type
//	@Before("execution(* *(org.spring.aop.beans.Account, ..))") // match any modifier (optional), any return type, any method in any class with 1st param of Account type and any number of other params of any types
//	@Before("execution(* org.spring.aop.beans.*.*(..))") // match any modifier (optional), any return type, any method in any class with 0 or more params of any type in specific package
	@Before("execution(* *(..))") // match any modifier (optional), any return type, any method in any class with 0 or more params of any type
	private void before(JoinPoint joinpoint) {
		System.out.println("\n >>>>>> @Before advise execution all Methods");
		
		/** getting method signature using JoinPoint **/
		MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
		System.out.println("\n >>>>>> Method Signature: " + methodSignature);
		
		/** getting method arguments using JoinPoint **/
		Object[] args = joinpoint.getArgs();
		for (Object arg : args) {
			System.out.println("\n >>>>>> Method arg: " + arg);
		}
	}
	
	
	/** 
	 * PointCut declaration 
	 * can be reused with many advises 
	 **/
	@Pointcut("execution(* org.spring.aop.beans.AccountDao.addAccount(..))")
	private void allMethodsWithAnyParams() {}
	
	@Before("allMethodsWithAnyParams()")
	private void usingPountutDeclaration() {
		System.out.println("\n >>>>>> @Before Advise, using PointCut Declaration, AccountDao.addAccount()");
	}
	
	/**
	 * Combine PointCuts
	 * PointCuts act as if condition
	 */
	@Pointcut("execution(* org.spring.aop.beans.Account.set*(..))")
	private void setter() {}
	
	@Pointcut("execution(* org.spring.aop.beans.Account.get*(..))")
	private void getter() {}
	
	@Before("setter()")
	private void beforeAllSetterMethods() {
		System.out.println("\n >>>>>> @Before Advise, setter methods");
	}
	
	@Before("getter()")
	private void beforeAllGetterMethods() {
		System.out.println("\n >>>>>> @Before Advise, getter methods");
	}
	
	@Pointcut("execution(* *(..))")
	private void allMethods() {}
	
	@Before("allMethods() && !(getter() || setter())")
	private void allMethodsExceptionGettersAndSetters() {
		System.out.println("\n >>>>>> @Before Advise, allMethodsExceptionGettersAndSetters");
	}
}
