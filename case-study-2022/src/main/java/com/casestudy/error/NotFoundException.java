package com.casestudy.error;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {
	private String msg ; 
	private String errorCode ; 
	public NotFoundException(String errorCode, String msg){
		super(msg); 
		this.msg = msg ;
		this.errorCode = errorCode ; 
	}
	@Override
	public String getMessage() {
		 return this.errorCode + " : " + this.msg ; 
	}
}
