package com.te.ramayan.herocontrolleradviser;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.ramayan.heroentity.Heros;

@ControllerAdvice
public class HeroControllerAdviser {

//   	@ExceptionHandler(value = RuntimeException.class)
//   	public Heros exceptionhandle(RuntimeException e) {
//		return new Heros();
//   		
//   	}
	@ExceptionHandler(value = RuntimeException.class)
	public Object exceptionhandle(RuntimeException e) {
	
		return new Heros();
	}
	
}
