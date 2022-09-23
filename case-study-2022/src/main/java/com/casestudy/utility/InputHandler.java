package com.casestudy.utility;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.casestudy.error.DateFormatException;
import com.casestudy.error.ValueOutOfBoundException;

public final class InputHandler {
	private static final Scanner sc = new Scanner(System.in) ; 
	private static final Logger logger = LogManager.getRootLogger() ; 
	
	/*
	 * Handles date inputs
	 */
	public static LocalDate getDateInput() {
		 String date = sc.nextLine() ;
		 date = date.trim() ; 
		 String[] parts = null ; 
		 
		 try {
			 parts = date.split("/") ; 
			 if(parts.length != 3)
				 throw new DateFormatException("ERROR-109","Entered date is not in Correct format");
					 	
		 }catch(DateFormatException e) {
			 logger.error(e.getMessage()) ;  
			 System.out.println("The recommended format is dd/mm/yyyy \n"
							+ "And do not provide extra space in between slashes");
			 return getDateInput() ; 
		 }
		 
		 LocalDate actualDate = null ; 
         Integer year = null , month = null , day = null ; 		 
		 
		 try {
			 year = Integer.parseInt(parts[2]) ;
			 month = Integer.parseInt(parts[1]) ; 
			 day = Integer.parseInt(parts[0]) ; 
		 } catch(Exception e) {
			 logger.error("Entered values are not a number") ;
			 System.out.println("Please Enter Correctly Again!");
			 return getDateInput() ; 
		 }
		 
		 try {
			 actualDate = LocalDate.of(year, month, day) ; 
		 }catch(Exception e) {
			 logger.error(e.getMessage()) ; 
			 System.out.println("Please Enter Correctly Again!");
			 return getDateInput() ; 
		 }
		 
		 return actualDate ; 
	}
	
	
	public static Long getNumberInput() {
		String strNumber = null ;
		Long number = null ; 
		try {
			strNumber = getStringInput() ;
			number = Long.parseLong(strNumber) ; 
		}catch(Exception e) {
			logger.error("Enter Value is not a Number!") ; 
			System.out.println("Please enter correctly again!");
			return getNumberInput() ; 
		}
		
		try {
			if(strNumber.length() > 10) 
				throw new ValueOutOfBoundException("ERROR-333", "ValueOutOfBound - number can not exceed 10 digits") ; 
			if(number < 0) {
				throw new ValueOutOfBoundException("ERROR-350", "ValueOutOfBound - number can't be negative") ;
			}
		}
		catch(Exception e) {
			logger.error(e.getMessage()) ; 
			System.out.println("Please enter correctly again!");
			return getNumberInput() ; 
		}
		
		return number ; 
	}
	
	public static String getStringInput() {
		String str = sc.nextLine() ; 
		str = str.trim() ; 
		return str ; 
	}
}
