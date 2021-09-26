package com.coursemanagement.dao;

import java.util.List;

import com.coursemanagement.beans.Course;
import com.coursemanagement.beans.Registration;
import com.coursemanagement.beans.Student;


public interface CourseDao {

	
	Integer addStudent(Student student);
	List<Student> getAllStudents();
	List<Course> getAllCourses();
	public void updateStudent(Student s);
	public void deleteStudent(Integer sId);
	
	public Integer addCourse(Course course);
	public void updateCourse(Course c);
	public void deleteCourse(Integer cId);
	public List<Student> getStudent(String email);
	public Student getStudentById(Integer id);
	public Integer addRegistration(Registration Registration);
	public List<Registration> getBookedCourses(Integer studentid);
	public List<Registration> getStudentsByCouseid(Integer cid);
	public Course getCourseById(Integer id);
	public List<Registration> getCoursesByStudentId(Integer sid);
	public void deleteRegistration(Integer rid);
	public Integer getRegistrationId(Integer sid, Integer cid);
	public Registration getRegistrationById(Integer id);
	public List<Registration> getAllRegistrations();
	

}
