package com.coursemanagement.beans;

import javax.persistence.GenerationType;

//import java.util.Date;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="enrollment_number")
	int enrollment_number;
	
	@Column(name="firstname")
	String firstname;
	
	@Column(name="lastname")
	String lastname;
	
	@Column(name="dateofbirth")
	Date dateofbirth;
	
	public Student(String firstname, String lastname, Date dateofbirth) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateofbirth = dateofbirth;
	}
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getEnrollment_number() {
		return enrollment_number;
	}
	public void setEnrollment_number(int enrollment_number) {
		this.enrollment_number = enrollment_number;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	@Override
	public String toString() {
		return "Student [enrollment_number=" + enrollment_number + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", dateofbirth=" + dateofbirth + "]";
	}
	
	
	
}

