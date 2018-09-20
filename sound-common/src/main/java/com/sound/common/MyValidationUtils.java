package com.sound.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;


public class MyValidationUtils {
	
	public static String parseErrors(BindingResult result) {
		if(result.hasErrors()==false) return null;
		
		List<ObjectError> errorList = result.getAllErrors();
	    StringBuilder errorMsg = new StringBuilder();
	    for(ObjectError error : errorList){
	        	errorMsg.append( error.getDefaultMessage()+"; ");
	    }
	    return (errorMsg.toString());
		
	}
}
