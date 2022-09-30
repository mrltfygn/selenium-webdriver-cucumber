package com.academy;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

/**
 * This class is responsible for running all features/scenario's on Chrome.
 * You can include either a feature or a scenario in the test suite by adding the @chrome tag.
 * The test runner is picked up by the maven-surefire-plugin by calling 'mvn test -Dbrowser=chrome'.
 */
@Suite
@SuiteDisplayName("Cucumber Integration Tests - CHROME")
@IncludeEngines("cucumber")
@SelectClasspathResource("nl/example")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "nl.example")
@IncludeTags("chrome")
public class TestRunnerChrome {
}
