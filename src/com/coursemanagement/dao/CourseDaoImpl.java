package com.coursemanagement.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.coursemanagement.beans.Course;
import com.coursemanagement.beans.Registration;
import com.coursemanagement.beans.Student;


@Component("UserDao")
public class CourseDaoImpl implements CourseDao {
private SessionFactory factory;
	
	public CourseDaoImpl() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
		factory = meta.getSessionFactoryBuilder().build();
	}

	@Override
	public Integer addStudent(Student student) {
		Integer studId = null;
		Integer user = null;
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		System.out.println(student);
		studId = (Integer) session.save(student);
		System.out.println(studId);
		txn.commit();
		session.close();
		return studId;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> sts = null;
		Session session =  factory.openSession();
		Transaction txn = session.beginTransaction();
		String hql = "FROM Student";
		TypedQuery<Student> query = session.createQuery(hql);
		sts= query.getResultList();
		session.close();
	    return sts;
	}


	@Override
	public void updateStudent(Student s) {
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
	    Student student = session.get(Student.class, s.getEnrollment_number());
	    student.setFirstname(s.getFirstname());
	    student.setLastname(s.getLastname());
	    student.setDateofbirth(s.getDateofbirth());
	    session.update(student);
	    txn.commit();
		session.close();
		
	}

	@Override
	public void deleteStudent(Integer sId) {
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		Student stud = session.get(Student.class, sId);
		session.delete(stud);
		txn.commit();
		session.close();
		
	}

	@Override
	public Integer addCourse(Course course) {
		Integer courseId = null;
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		courseId = (Integer) session.save(course);
		txn.commit();
		session.close();
		return courseId;
	}

	@Override
	public void updateCourse(Course c) {
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
	    Course course = session.get(Course.class, c.getCourseid());
	    course.setCoursename(c.getCoursename());
	    course.setCredits(c.getCredits());
	    course.setInstructor_name(c.getInstructor_name());
	 	session.update(course);
	    txn.commit();
		session.close();
		
	}

	@Override
	public void deleteCourse(Integer cId) {
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		Course c = session.get(Course.class, cId);
		session.delete(c);
		txn.commit();
		session.close();
	}
	
	@Override
	public List<Course> getAllCourses() {
		List<Course> guest = null;
		Session session =  factory.openSession();
		Transaction txn = session.beginTransaction();
		String hql = "FROM Course";
		TypedQuery<Course> query = session.createQuery(hql);
		guest= query.getResultList();
		session.close();
	    return guest;
	}

	@Override
	public List<Student> getStudent(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentById(Integer id) {
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		Student student = session.get(Student.class, id);
		txn.commit();
		session.close();
		return student;
	}
	
	

	@Override
	public Integer addRegistration(Registration registration) {
		Integer registerid = null; 
		Session session =  factory.openSession();
		Transaction txn = session.beginTransaction();
	     registerid= (Integer)session.save(registration);
		txn.commit();
		session.close();
		return registerid;
	}

	@Override
	public List<Registration> getBookedCourses(Integer studentid) {
		List<Registration> bookedcourses = null;
		Session session =  factory.openSession();
		Transaction txn = session.beginTransaction();
		TypedQuery<Registration> query= session.createQuery("FROM Registration b where b.studentid='"+studentid+"'");
		bookedcourses =query.getResultList();
		txn.commit();
		session.close();
		return bookedcourses;
	}

	@Override
	public List<Registration> getStudentsByCouseid(Integer cid) {
		List<Registration> studentCourses=null;
		Session session =  factory.openSession();
		Transaction txn = session.beginTransaction();
		TypedQuery<Registration> query= session.createQuery("FROM Registration b where b.courseid='"+cid+"'");
		studentCourses= query.getResultList();
		return studentCourses;
	}
	
	@Override
	public List<Registration> getCoursesByStudentId(Integer sid) {
		List<Registration> studentCourses=null;
		Session session =  factory.openSession();
		Transaction txn = session.beginTransaction();
		TypedQuery<Registration> query= session.createQuery("FROM Registration b where b.studentid='"+sid+"'");
		studentCourses= query.getResultList();
		return studentCourses;
	}
	
	@Override
	public Integer getRegistrationId(Integer sid, Integer cid) {
		List<Registration> rgs=null;
		Session session =  factory.openSession();
		Transaction txn = session.beginTransaction();
		TypedQuery<Registration> query= session.createQuery("FROM Registration b where b.studentid='"+sid+"'and b.courseid='"+cid+"'");
		rgs= query.getResultList();
		return rgs.get(0).getRegistrationid();
	}


	@Override
	public Course getCourseById(Integer id) {
			Session session = factory.openSession();
			Transaction txn = session.beginTransaction();
			Course course = session.get(Course.class, id);
			txn.commit();
			session.close();
			return course;
	}
	
	@Override
	public Registration getRegistrationById(Integer id) {
			Session session = factory.openSession();
			Transaction txn = session.beginTransaction();
			Registration reg = session.get(Registration.class, id);
			txn.commit();
			session.close();
			return reg;
	}
	
	@Override
	public void deleteRegistration(Integer rid) {
		Session session = factory.openSession();
		Transaction txn = session.beginTransaction();
		Registration c = session.get(Registration.class, rid);
		session.delete(c);
		txn.commit();
		session.close();
	}

	@Override
	public List<Registration> getAllRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}




}
