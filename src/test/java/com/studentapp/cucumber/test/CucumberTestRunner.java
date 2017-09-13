package com.studentapp.cucumber.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",
	glue={"com.studentapp.test.stepdefinitions"},
	format ={"html:target/cucumber-html-report", "json:target/cucumber-json-report.json"})
public class CucumberTestRunner {

}
