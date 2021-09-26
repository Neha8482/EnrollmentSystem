package com.coursemanagement.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="courseid")
	int courseid;
	
	@Column(name="coursename")
	String coursename;
	
	@Column(name="credits")
	int credits;
	
	@Column(name="instructor_name")
	String instructor_name;
	
	
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Course(String coursename, int credits, String instructor_name) {
		super();
		this.coursename = coursename;
		this.credits = credits;
		this.instructor_name = instructor_name;
	}


	public int getCourseid() {
		return courseid;
	}


	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}


	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getInstructor_name() {
		return instructor_name;
	}
	public void setInstructor_name(String instructor_name) {
		this.instructor_name = instructor_name;
	}


	@Override
	public String toString() {
		return "Course [courseid=" + courseid + ", coursename=" + coursename + ", credits=" + credits
				+ ", instructor_name=" + instructor_name + "]";
	}
	
	
	
}
