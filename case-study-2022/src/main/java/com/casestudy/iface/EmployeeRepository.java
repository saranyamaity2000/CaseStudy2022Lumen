package com.casestudy.iface;

public interface EmployeeRepository<T, P> extends CRUDRepository<T, P> {
	/*
	 * Update email by primary Key
	 * returns how many rows are updated
	 */
	public int updateEmailByPrimaryKey(P primaryKey, String updatedEmail);
	
	
	/*
	 * update Phone Number By employee object
	 * returns how many rows are updated
	 * 
	 */
	public int updatePhoneNumberByPrimaryKey(P primaryKey, Long updatedPhoneNumber);
	
	
	/* 
	 * delete by Employee Name
	 * return how many rows are deleted
	 * 
	 */
	public int deleteByFirstName(String employeeName);
}
