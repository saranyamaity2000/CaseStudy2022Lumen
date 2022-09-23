package com.casestudy.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.casestudy.connection.ConnectionFactory;
import com.casestudy.error.NotFoundException;
import com.casestudy.iface.EmployeeRepository;
import com.casestudy.implementation.EmployeeRepositoryImp;
import com.casestudy.model.Employee;

public class EmployeeService {
	private EmployeeRepository<Employee, String> repo ; 
	private Connection con ; 
	private static final Logger logger = LogManager.getRootLogger() ;
	
	public EmployeeService() {
		super(); 
		this.con = ConnectionFactory.getMySqlConnection() ; 
		this.repo = new EmployeeRepositoryImp(con) ; 
	}
	
	public void startConnection() {
		try {
			if(this.con == null  || this.con.isClosed()) {
				this.con = ConnectionFactory.getMySqlConnection() ;  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if(!con.isClosed())
			    con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getEmployeesByFirstName(String firstName) throws NotFoundException {
		 List<Employee> empList = this.repo.findall().stream()
				.filter(emp -> emp.getFirstName().equals(firstName))
				.peek(emp -> logger.info(emp))
				.collect(Collectors.toList()); 
		 if(empList.size() == 0) 
			 throw new NotFoundException("ERROR-100","No Such Rows Are Found!") ; 
	}

	public void getEmployeesWithFirstNameAndPhoneNumber() throws NotFoundException {
		
		List<Employee> empList = this.repo.findall().stream()
				.peek(emp -> logger.info(emp.getFirstName() + " " + emp.getPhoneNumber()))
				.collect(Collectors.toList());  
		 if(empList.size() == 0) 
			 throw new NotFoundException("ERROR-100","No Such Rows Are Found!") ; 
	}
	
    public void getEmployeesFirstNameAndEmailByBirthDate(int dayOfMonth, int monthOfYear) throws NotFoundException{
		List<Employee> empList = this.repo.findall().stream()
				.filter(emp -> {
					return dayOfMonth == emp.getDateOfBirth().getDayOfMonth() &&
							monthOfYear == emp.getDateOfBirth().getMonthValue(); 
				})
				.peek(emp -> logger.info(emp.getFirstName() + " " + emp.getEmail()))
				.collect(Collectors.toList()); 
		if(empList.size() == 0) 
			 throw new NotFoundException("ERROR-100","No Such Rows Are Found!") ; 
	} 
    
    public void getEmployeesFirstNameAndPhoneNumberByWeddingDate(int dayOfMonth, int monthOfYear) throws NotFoundException {
		List<Employee> empList = this.repo.findall().stream()
				.filter(emp -> {
					return dayOfMonth == emp.getDateOfWedding().getDayOfMonth() &&
							monthOfYear == emp.getDateOfWedding().getMonthValue(); 
				})
				.peek(emp -> logger.info(emp.getFirstName() + " " + emp.getPhoneNumber()))
				.collect(Collectors.toList()); 
		if(empList.size() == 0) 
			 throw new NotFoundException("ERROR-100","No Such Rows Are Found!") ; 
	} 
    
    
    public void addEmployee(Employee obj) {
		 if(repo.save(obj)) {
			logger.info("1 new entity added to the lumen_employees table!");  
		 }
	}
	
	public void updateEmailAndPhoneNumberByEmail(String newEmail, Long newPhoneNumber, String oldEmail) {
		int changed = 0 ;
		this.repo.updatePhoneNumberByPrimaryKey(oldEmail, newPhoneNumber) ; 
		changed = this.repo.updateEmailByPrimaryKey(oldEmail, newEmail); 
		
		if(changed == 0) 
			logger.warn("No entry present with email -> " + newEmail) ;
		logger.info("Total " + changed + " rows updated!");
	}
	
	public void deleteEmployeeByFirstName(String firstName) {
		int deleted = 0 ; 
		deleted += this.repo.deleteByFirstName(firstName) ; 
		if(deleted == 0) 
			logger.warn("No entry present with first_name -> " + firstName) ;
		logger.info("Total " + deleted + " rows deleted!");
	}
}
