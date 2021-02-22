package com.moolah_prog.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "monthDetails")
public class monthDetails  extends moolahUser {
	

	private long monthlySalary;
    private long monthlySavings;
	
    
    

}
