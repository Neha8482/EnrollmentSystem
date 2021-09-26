package com.coursemanagement.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registration")
public class Registration {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="registrationid")
	int registrationid;
	
	@Column(name="studentid")
	int studentid;
	
	@Column(name="courseid")
	int courseid;
	
	
	
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Registration( int studentid, int courseid) {
		this.studentid = studentid;
		this.courseid = courseid;
	}
	public int getRegistrationid() {
		return registrationid;
	}
	public void setRegistrationid(int registrationid) {
		this.registrationid = registrationid;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	@Override
	public String toString() {
		return "Registration [registrationid=" + registrationid + ", studentid=" + studentid + ", courseid=" + courseid
				+ "]";
	}
	
	

}
