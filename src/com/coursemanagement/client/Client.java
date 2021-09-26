package com.coursemanagement.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletRequest;

//import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.coursemanagement.beans.Course;
import com.coursemanagement.beans.Registration;
import com.coursemanagement.beans.Student;
import com.coursemanagement.dao.CourseDaoImpl;


@RestController
@ComponentScan(basePackages = "com.coursemanagement.beans")
public class Client {	
	
	@RequestMapping(value="/student",method=RequestMethod.GET)
	 public @ResponseBody List<Student> getStudents(){ 
		 ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
		List<Student> studList= guestDao.getAllStudents();
		ModelAndView modelAndView = new ModelAndView("Students");
		modelAndView.addObject("studentList",studList);
		System.out.println(studList);
		return studList;
	}
	
	@RequestMapping(value="/student",method=RequestMethod.POST)
	  public @ResponseBody Student createStudent(@RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("dateofbirth") String dateofbirth)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
		
		 Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(dateofbirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		 //java.util.Date utilDate = new java.util.Date();
		   java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
		Student student = new Student(firstname,lastname,sqlDate);
		Integer id = guestDao.addStudent(student);
		System.out.println(id);
		
	
		ModelAndView modelAndView = new ModelAndView("Students");
		return student;
	}
	  @PutMapping(value = "/student")
	    public @ResponseBody Student updateStudent(@RequestParam("enrollment_number") int enrollment_number , @RequestParam("firstname") String firstname,@RequestParam("lastname") String lastname,@RequestParam("dateofbirth") String dateofbirth)
		 {
	        //Project userResponse = userService.updateProject(project);
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = null;
			try {
				date = simpleDateFormat.parse(dateofbirth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //java.util.Date utilDate = new java.util.Date();
			   java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	        System.out.println("Updated");
	        Student student = guestDao.getStudentById(enrollment_number);
	        student.setFirstname(firstname);
	        student.setLastname(lastname);
	        student.setDateofbirth(sqlDate);
		    guestDao.updateStudent(student);
	        
	        return student;
	    }
	 

	    @RequestMapping(value = "/student", method = RequestMethod.DELETE)
	    public @ResponseBody Student deleteStudent(@RequestParam("id") int id) {
	       // Project userResponse = userService.deleteProject(id);
	        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
			Student s= guestDao.getStudentById(id);
			System.out.println(s);
	        guestDao.deleteStudent(id);
	        System.out.println("DELETED");
	        return s;
	    }
	
		@RequestMapping(value="/course",method=RequestMethod.GET)
		 public @ResponseBody List<Course> getCourses(){ 
			 ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
			List<Course> courseList= guestDao.getAllCourses();
			ModelAndView modelAndView = new ModelAndView("Students");
			modelAndView.addObject("studentList",courseList);
			System.out.println(courseList);
			return courseList;
		}
		
		@RequestMapping(value="/course",method=RequestMethod.POST)
		  public @ResponseBody Course createCourse(@RequestParam("coursename") String coursename,@RequestParam("credits") int credits,@RequestParam("instructor_name") String instructor_name)
		{
		
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
			Course course = new Course(coursename,credits,instructor_name);
			System.out.println(course);
			Integer id = guestDao.addCourse(course);
			System.out.println(id);
			
		
			ModelAndView modelAndView = new ModelAndView("Students");
			return course;
		}
		 @PutMapping(value = "/course")
		    public @ResponseBody Course updateCourse(@RequestParam("courseid") int courseid, @RequestParam("coursename") String coursename,@RequestParam("credits") int credits,@RequestParam("instructor_name") String instructor_name)
			{
		        //Project userResponse = userService.updateProject(project);
				ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
				
		        System.out.println("Updated");
		    	Course course = guestDao.getCourseById(courseid);
		    	course.setCoursename(coursename);
		    	course.setCredits(credits);
		    	course.setInstructor_name(instructor_name);
			    guestDao.updateCourse(course);
		        
		        return course;
		    }
		 
		    
		    @RequestMapping(value = "/course", method = RequestMethod.DELETE)
		    public @ResponseBody Course deleteCourse(@RequestParam("id") int id) {
		       // Project userResponse = userService.deleteProject(id);
		        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
				Course s= guestDao.getCourseById(id);
		        guestDao.deleteCourse(id);
		        System.out.println("DELETED");
		        System.out.println(s);
		        return s;
		    }
		    
		  
		    @RequestMapping(value = "/register", method = RequestMethod.POST)
		    public @ResponseBody Registration addgistration(@RequestParam("courseid") int courseid,@RequestParam("studentid") int studentid) {
		       // Project userResponse = userService.deleteProject(id);
		        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
				Student scheck =null;
				scheck=guestDao.getStudentById(studentid);
				Course ccheck =null;
				ccheck= guestDao.getCourseById(courseid);
				Registration re= null;
				
				int flag =0;
				if(ccheck != null && scheck != null) {
					List<Registration> rs = new ArrayList<>();
					rs = guestDao.getCoursesByStudentId(studentid);
					re = new Registration(studentid, courseid);
					for(Registration r : rs) {
						if(r.getCourseid() == courseid) {
							flag=1;
							break;
						}
					}
					
					if(flag == 0) {
					Integer id = guestDao.addRegistration(re);
					}
				}	
				
				
				System.out.println(ccheck);
				System.out.println(scheck);
		        return re;
		    }
		    
		    @RequestMapping(value="/register/students",method=RequestMethod.GET)
			 public @ResponseBody List<Student> getStudents(@RequestParam("courseid") int courseid){ 
				 ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
				
				
				List<Registration> res = guestDao.getStudentsByCouseid(courseid);
				List<Student> studs = new ArrayList<>();
				for(Registration r : res) {
					studs.add(guestDao.getStudentById(r.getStudentid()));
				}
				
				ModelAndView modelAndView = new ModelAndView("Students");
				modelAndView.addObject("studentList",res);
				System.out.println(res);
				return studs;
			}
		    @RequestMapping(value="/register/courses",method=RequestMethod.GET)
			 public @ResponseBody List<Course> getCoursesSelected(@RequestParam("studentid") int studid){ 
				 ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
				
				
				List<Registration> res = guestDao.getCoursesByStudentId(studid);
				List<Course> cs = new ArrayList<>();
				for(Registration r : res) {
					cs.add(guestDao.getCourseById(r.getCourseid()));
				}
				
				ModelAndView modelAndView = new ModelAndView("Students");
				modelAndView.addObject("studentList",res);
				System.out.println(res);
				return cs;
			}
		    
		    @RequestMapping(value = "/register/deleteentry", method = RequestMethod.DELETE)
		    public @ResponseBody Registration deleteregistration(@RequestParam("studentid") int studentid, @RequestParam("courseid") int courseid) {
		       // Project userResponse = userService.deleteProject(id);
		        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				CourseDaoImpl guestDao = (CourseDaoImpl) context.getBean("UserDao");
				
				Integer rid = guestDao.getRegistrationId(studentid, courseid);
				Registration r = guestDao.getRegistrationById(rid);
				guestDao.deleteRegistration(rid);
				
		     
		        System.out.println("DELETED");
		        System.out.println(r);
		        return r;
		    }
	
}
