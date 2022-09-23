package com.casestudy.iface;

import java.util.*;

/*
 * T - type of main object , based on which the repository is created
 * P - type of the Primary Key
 */
public interface CRUDRepository<T,P> {
	/*
	 * save a instance
	 */
	public boolean save(T obj);
	
	/*
	 * find all the available instances 
	 */
	public Collection<T> findall() ;
	
	
	/*
	 * delete object by primaryKey
	 */
	public int deleteByPrimaryKey(P obj);
}
