package com.studentapp.test.stepdefinitions;

import java.nio.charset.Charset;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.studentapp.StudentCrudApplication;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = StudentCrudApplication.class)
@WebAppConfiguration
@SpringBootTest
public abstract class AbstractStepDefs {
	
	//the output content type
	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	protected MockMvc mockMvc;

	
	protected ResultActions resultActions;
	
	

}