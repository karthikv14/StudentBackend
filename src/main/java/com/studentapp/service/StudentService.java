package com.studentapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentapp.dao.StudentRepository;
import com.studentapp.model.Student;

import javassist.NotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student create(Student student) throws Exception {
		return studentRepository.save(student);
	}

	public Student update(Student student) throws Exception {
		checkIfStudentExist(student.getId());
		return studentRepository.save(student);
	}

	public void delete(Long id) throws Exception {
		checkIfStudentExist(id);
		studentRepository.delete(id);
	}

	public List<Student> getAll() throws Exception {
		return studentRepository.findAll();
	}

	public Student getById(Long id) throws Exception {
		Student student = studentRepository.findOne(id);
		if (student == null) {
			throw new NotFoundException("Student with " + id + " Not found");
		}
		return student;
	}

	private void checkIfStudentExist(Long id) throws NotFoundException {
		Student student = studentRepository.findOne(id);
		if (student == null) {
			throw new NotFoundException("Student with " + id + " Not found");
		}
	}
}
