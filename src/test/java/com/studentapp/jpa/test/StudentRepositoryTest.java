package com.studentapp.jpa.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.studentapp.dao.StudentRepository;
import com.studentapp.model.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveStudent() {
		Student s = new Student();
		s.setFirstName("John");
		s.setLastName("Doe");
		s = studentRepository.save(s);
		Assert.assertNotNull(s.getId());
	}

	@Test
	public void getAllStudents() {
		Student s = new Student();
		s.setFirstName("John");
		s.setLastName("Doe");
		studentRepository.save(s);
		List<Student> students = studentRepository.findAll();
		Assert.assertEquals(1, students.size());
	}

	@Test
	public void updateStudent() {
		Student s = new Student();
		s.setFirstName("John");
		s.setLastName("Doe");
		s = studentRepository.save(s);
		s.setFirstName("Jane");
		Student updatedstudent = studentRepository.save(s);
		Assert.assertEquals(updatedstudent.getFirstName(), "Jane");
	}

	@Test
	public void deleteStudent() {
		Student s = new Student();
		s.setFirstName("John");
		s.setLastName("Doe");
		s = studentRepository.save(s);
		studentRepository.delete(s);
		Assert.assertEquals(studentRepository.findAll().size(), 0);
	}

}
