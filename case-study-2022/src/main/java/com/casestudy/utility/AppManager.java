package com.casestudy.utility;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.casestudy.error.DateFormatException;
import com.casestudy.error.NotFoundException;
import com.casestudy.model.Employee;
import com.casestudy.services.EmployeeService;

/*
 * AppManager's constructor is private because it should not have an instance
 * It is not supposed to be used using it's instance so I have made it is made abstract 
 * 
 */
public final class AppManager {
	
	private AppManager() {
		super() ; 
	}
	
	/*
	 * service instance which basically operations actual requests/queries 
	 */
	private static EmployeeService service = new EmployeeService() ; 
	
	/*
	 * Scanner instance that helps to take input!
	 */
	private static Scanner sc = new Scanner(System.in) ; 
	
	private static final Logger logger = LogManager.getRootLogger() ; 
	
	/*
	 * Show available option names
	 */
	public static void showAvailableOptions() {
		System.out.println("------------------------------------------------------------------------------");
    	System.out.println("1) Add employee details \r\n"
    			+ "2) Get the List of employees by their firstName. \r\n"
    			+ "3) Get the List of employees with FirstName and Phone Number \r\n"
    			+ "4) Update the email and phoneNumber of a particular employee (by Email). \r\n"
    			+ "5) Delete Details of a Particular employee by firstName \r\n"
    			+ "6) Get a list of employees with their firstName and emailAddress\n"
    			+ "whose Birthday falls on the given date \r\n"
    			+ "7) Get the list of employees with their firstName and phone Number\n"
    			+ "whose Wedding Anniversary falls on the given date \r\n"
		        + "8) Exit");
    	System.out.println("------------------------------------------------------------------------------");
    }
	
	/*
	 * Functions which tells which options are available to be selected!
	 */
	public static void choiceRecommendation() {
		System.out.println("Please Select a number between 1 to 8!") ; 
	}
	
	/*
	 * Alert to be shown when entered wrong choice! 
	 */
	public static void wrongChoiceAlert() {
    	 System.out.println("!!! You have choosen WRONG option!!!");
	}
	
	/*
	 * Handles date inputs
	 */
	public static LocalDate dateInputHandler() {
		 String date = sc.nextLine() ;
		 String[] parts = date.split("/") ;
		 LocalDate actualDate = null ; 
		 try {
			 actualDate =   LocalDate.of(Integer.parseInt(parts[2]) , 
						                        Integer.parseInt(parts[1]) , 
			     		                        Integer.parseInt(parts[0])); 
		 }
		 catch(Exception e) {}
		 
		 try {
			 if(actualDate == null)
				 throw new DateFormatException("ERROR-109","Entered date is not in Correct format \n"
				 		+ "The recommended format is dd/mm/yyyy \n"
						+ "And do not provide extra space in between slashes") ; 
		 }
		 catch(DateFormatException e) {
			 logger.error(e.getMessage()) ;  
			 return dateInputHandler() ; 
		 }
		 return actualDate ; 
	}
	
	
	public static Long numberInputHandler() {
		Long number = null ; 
		try {
			number = Long.parseLong(sc.nextLine()) ; 
		}
		catch(Exception e) {
			logger.error(e.getMessage()) ; 
			System.out.println("Please enter correctly again!");
			return numberInputHandler() ; 
		}
		return number ; 
	}
	
	
	
	/*
	 * handle choice number 1 -> Add employee details
	 * 
	 */
	private static void handleChoice1() {
		 logger.info("User has choosen option number 1") ;  
		
		 System.out.println("Enter Employee's firstName -> ");
		 String firstName = sc.nextLine() ;
		 
		 System.out.println("Enter Employee's lastName");
		 String lastName = sc.nextLine() ; 
		 
		 System.out.println("Enter Employee's address");
		 String address = sc.nextLine() ; 
		 
		 System.out.println("Enter Employee's email");
		 String email = sc.nextLine() ; 
		 
		 System.out.println("Enter Employee's phone number :");
		 Long phoneNumber = numberInputHandler() ; 
		 
		 System.out.println("Enter Employee's date of birth in dd/mm/yyyy format");
		 LocalDate dateOfBirth = dateInputHandler() ; 
		 
		 
		 System.out.println("Enter Employee's wedding date in dd/mm/yyyy format");
		 LocalDate dateOfWedding = dateInputHandler() ; 
		 
		 service.addEmployee(new Employee(firstName, lastName, address, email, phoneNumber, dateOfBirth, dateOfWedding)); 
	}
	
	/*
	 * handle choice number 2 - Get the List of employees by their firstName
	 */
    private static void handleChoice2() {
    	logger.info("User has choosen option number 2") ; 
    	System.out.println("Enter Employee's firstName");
    	String firstName = sc.nextLine() ; 
    	try {
			service.getEmployeesByFirstName(firstName);
		} catch (NotFoundException e) {
			logger.error(e.getMessage()) ; 
		}
	}
    
    /*
	 * handle choice number 3 - Get the List of employees with FirstName and Phone Number
	 */
    private static void handleChoice3() {
    	logger.info("User has choosen option number 3") ; 
		try {
			service.getEmployeesWithFirstNameAndPhoneNumber();
		} catch (NotFoundException e) {
			logger.error(e.getMessage()) ; 
		}
	}
    
    /*
	 * handle choice number 4 -  Update the email and phoneNumber 
	 *                           of a particular employee (by Email).
	 */
    private static void handleChoice4() {
    	logger.info("User has choosen option number 4") ; 
    	System.out.println("Enter the email of the employee that you want to modify");
    	String oldEmail = sc.nextLine() ; 
    	System.out.println("Now enter New Mobile Number");
    	Long phoneNumber = numberInputHandler() ; 
    	System.out.println("Enter New Email id");
    	String newEmail = sc.nextLine();
    	service.updateEmailAndPhoneNumberByEmail(newEmail, phoneNumber, oldEmail);
    }
    
    /*
	 * handle choice number 5 - Delete Details of a Particular employee by firstName
	 */
    private static void handleChoice5() {
    	logger.info("User has choosen option number 5") ; 
    	System.out.println("Enter firstName");
    	String firstName = sc.nextLine() ; 
		service.deleteEmployeeByFirstName(firstName);
    }
    
    /*
	 * handle choice number 6 - Get a list of employees with their firstName and
	 *                          emailAddress  whose Birthday falls on the given date"
	 */
    private static void handleChoice6() {
    	logger.info("User has choosen option number 6") ; 
    	System.out.println("Enter Month Number") ; 
    	int monthOfTheYear = sc.nextInt() ; 
    	System.out.println("Entger Day number of that Month");
    	int dayOfTheMonth = sc.nextInt() ; 
    	try {
			service.getEmployeesFirstNameAndEmailByBirthDate(dayOfTheMonth, monthOfTheYear);
		} catch (NotFoundException e) {
			logger.error(e.getMessage()) ; 
		}
    }
    
    /*
	 * handle choice number 7 - Get the list of employees with their firstName and phone Number whose
	 *                          Wedding Anniversary falls on the given date "
	 */
    private static void handleChoice7() {
    	logger.info("User has choosen option number 7") ; 
    	System.out.println("Enter Month Number") ; 
    	int monthOfTheYear = sc.nextInt() ; 
    	System.out.println("Enter Day number of that Month");
    	int dayOfTheMonth = sc.nextInt() ; 
    	try {
			service.getEmployeesFirstNameAndPhoneNumberByWeddingDate(dayOfTheMonth, monthOfTheYear);
		} catch (NotFoundException e) {
			logger.error(e.getMessage());
		}
	}

    /*
     * Handle choice 
     */
	public static boolean evaluteChoice(int choice) {
	  switch(choice) {
        case 1: handleChoice1(); 
            break ;
        case 2: handleChoice2();
            break ;
        case 3: handleChoice3();
            break ; 
        case 4: handleChoice4();
            break ; 
        case 5: handleChoice5();
            break ; 
        case 6: handleChoice6();
            break ; 
        case 7: handleChoice7();
        	break ; 
        case 8: // exit case 
        	System.out.println("Shutting down.....");
        	System.exit(0);
        default:
        	return false ;
      } 
	  return true ; 
	}
}
