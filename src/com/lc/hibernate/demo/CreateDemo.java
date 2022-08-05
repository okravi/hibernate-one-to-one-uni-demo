package com.lc.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lc.hibernate.demo.entity.Instructor;
import com.lc.hibernate.demo.entity.InstructorDetail;
import com.lc.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {		
			//create the objects
			Instructor tempInstructor = 
					new Instructor("John", "Mcclain", "mcclain@mail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail("www.youtube.com/channel", "coding");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save the instructor
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();;
			System.out.println("Done");
		} finally {
			factory.close();
		}
	}

}
