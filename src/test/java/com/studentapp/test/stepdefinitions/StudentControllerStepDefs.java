package com.studentapp.test.stepdefinitions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentapp.controller.StudentController;
import com.studentapp.model.Student;
import com.studentapp.service.StudentService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StudentControllerStepDefs extends AbstractStepDefs {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@Given("^the web context is set$")
	public void the_web_context_is_set() throws Throwable {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}

	@When("^client request POST /students with json data:$")
	public void client_request_POST_Students_with_json_data(String studentJson) throws Throwable {
		Student savedStudent = new Student(1L, "John", "Doe");
		Mockito.when(studentService.create(Mockito.isA(Student.class))).thenReturn(savedStudent);
		resultActions = this.mockMvc.perform(post("/students").contentType(contentType).content(studentJson));
	}

	@Then("^the post response code should be (\\d+)$")
	public void the_post_response_code_should_be(int statusCode) throws Throwable {
		resultActions.andExpect(status().is(statusCode));
	}
	
	@When("^client request PUT /students with json data:$")
	public void client_request_PUT_students_with_json_data(String studentJson) throws Throwable {
		Student savedStudent = new Student(1L, "Jane", "Doe");
		Mockito.when(studentService.update(Mockito.isA(Student.class))).thenReturn(savedStudent);
		resultActions = this.mockMvc.perform(put("/students").contentType(contentType).content(studentJson));
	}
	
	@Then("^the put response code should be (\\d+)$")
	public void the_put_response_code_should_be(int statusCode) throws Throwable {
		resultActions.andExpect(status().is(statusCode));
	}
	
	
	@When("^client request DELETE /students/\\$(\\d+)$")
	public void client_request_DELETE_students_$(int studentId) throws Throwable {
		Mockito.doNothing().when(studentService).delete(Mockito.isA(Long.class));
		resultActions = this.mockMvc.perform(delete("/students/"+studentId));
	}

	@Then("^the delete response code should be (\\d+)$")
	public void the_delete_response_code_should_be(int statusCode) throws Throwable {
		resultActions.andExpect(status().is(statusCode));
	}
	
	@When("^client request GET /students$")
	public void client_request_GET_students() throws Throwable {
		   List<Student>students = Arrays.asList(new Student(1L, "John", "Doe"),new Student(2L, "Mark", "Henry"));
		   Mockito.when(studentService.getAll()).thenReturn(students);
		   resultActions = this.mockMvc.perform(get("/students"));
	}

	@Then("^the Get All response should be (\\d+)$")
	public void the_Get_All_response_should_be(int statusCode) throws Throwable {
		resultActions.andExpect(status().is(statusCode));
	}

	@Then("^the JSON response should be an array with (\\d+) students$")
	public void the_JSON_response_should_be_an_array_with_students(int size) throws Throwable {
		ObjectMapper objectMapper = new ObjectMapper();
	    String response = resultActions.andReturn().getResponse().getContentAsString();
	    TypeReference<List<Student>> mapType = new TypeReference<List<Student>>() {};
    	List<Student> jsonToStudentList = objectMapper.readValue(response, mapType);
    	Assert.assertEquals(size, jsonToStudentList.size());
	}
	
	@When("^client request GET /students/(\\d+)$")
	public void client_request_GET_students(int studentId) throws Throwable {
	   Student student = new Student(1L, "John", "Doe");
	   Mockito.when(studentService.getById(1L)).thenReturn(student);
	   resultActions = this.mockMvc.perform(get("/students/"+studentId));
	}

	@Then("^the Get by ID response should be (\\d+)$")
	public void the_Get_by_ID_response_should_be(int statusCode) throws Throwable {
		resultActions.andExpect(status().is(statusCode));
	}

	@Then("^the JSON response should be a customer object$")
	public void the_JSON_response_should_be_a_customer_object() throws Throwable {
		ObjectMapper objectMapper = new ObjectMapper();
	    String response = resultActions.andReturn().getResponse().getContentAsString();
	    TypeReference<Student> mapType = new TypeReference<Student>() {};
	    Student student = objectMapper.readValue(response, mapType);
	    Assert.assertEquals("John", student.getFirstName());
	    
	   
	}

}
