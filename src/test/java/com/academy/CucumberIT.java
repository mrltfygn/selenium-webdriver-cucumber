package com.academy;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith( Cucumber.class )
@CucumberOptions(features = {"src/test/resources/features"}, plugin = {"pretty"})
public class CucumberIT {

}