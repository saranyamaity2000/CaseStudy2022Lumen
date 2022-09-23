package com.casestudy;

import java.util.Scanner;

import com.casestudy.utility.AppManager;


public class App 
{   
   
	public static void main( String[] args )
    {
        System.out.println("Hello!");
        System.out.println("---------------------------------");
       
        boolean running = true ; 
        
        while(running) {
        	AppManager.choiceRecommendation();
        	AppManager.showAvailableOptions();
        	
        	Scanner sc = new Scanner(System.in) ; 
            int choice = sc.nextInt(); 
        	
        	if(!AppManager.evaluteChoice(choice)) {
        		AppManager.wrongChoiceAlert();
        	}
        }
    }
}
