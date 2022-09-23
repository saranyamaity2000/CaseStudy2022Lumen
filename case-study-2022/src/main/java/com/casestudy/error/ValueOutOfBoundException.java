package com.casestudy.error;

public class ValueOutOfBoundException extends Exception {
	private String msg ; 
	private String errorCode ; 
	public ValueOutOfBoundException(String errorCode, String msg){
		super(msg); 
		this.msg = msg ;
		this.errorCode = errorCode ; 
	}
	@Override
	public String getMessage() {
		 return this.errorCode + " : " + this.msg ; 
	}
}
