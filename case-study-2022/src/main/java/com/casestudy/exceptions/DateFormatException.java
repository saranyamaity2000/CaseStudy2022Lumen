package com.casestudy.exceptions;

/*
 * To handle Date Format Exceptions !
 */
@SuppressWarnings("serial")
public class DateFormatException extends Exception {
	private String msg ; 
	private String errorCode ; 
	public DateFormatException(String errorCode, String msg){
		super(msg); 
		this.msg = msg ;
		this.errorCode = errorCode ; 
	}
	@Override
	public String getMessage() {
		 return this.errorCode + " : " + this.msg ; 
	}
}

