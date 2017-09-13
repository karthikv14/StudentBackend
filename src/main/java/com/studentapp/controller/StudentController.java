package com.studentapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentapp.model.Student;
import com.studentapp.service.StudentService;

@RestController
@RequestMapping("students")
@CrossOrigin("http://localhost:8085")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<Student> create(@Valid @RequestBody Student student) throws Exception {
		return new ResponseEntity<Student>(studentService.create(student), HttpStatus.CREATED);
	}

	@PutMapping
	public Student update(@Valid @RequestBody Student student) throws Exception {
		return studentService.update(student);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws Exception {
		studentService.delete(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@GetMapping
	public List<Student> getAll() throws Exception {
		//throw new Exception("An error occurred");
		return studentService.getAll();
	}

	@GetMapping(value = "/{id}")
	public Student getById(@PathVariable("id") Long id) throws Exception {
		return studentService.getById(id);
	}

}