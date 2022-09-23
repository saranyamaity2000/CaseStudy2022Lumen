package com.casestudy;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.casestudy.utility.AppManager;


public class App 
{   
    public final static  Logger logger = LogManager.getRootLogger(); 
    
	public static void main( String[] args )
    {
        System.out.println("Hello! Hope You are Having a Good Day!");
       
        boolean running = true ; 
        
        while(running) {
        	AppManager.choiceRecommendation();
        	AppManager.showAvailableOptions();
        	
        	Scanner sc = new Scanner(System.in) ;
        	
            Integer choice = null ; 
            
            try {
            	choice = Integer.parseInt(sc.nextLine()) ; 
            	if(!AppManager.evaluteChoice(choice)) {
            		AppManager.wrongChoiceAlert();
            	}
            }catch(Exception e) {
            	logger.error("Entered value is not a number!") ; 
            	AppManager.wrongChoiceAlert();
            }
        }
    }
}
