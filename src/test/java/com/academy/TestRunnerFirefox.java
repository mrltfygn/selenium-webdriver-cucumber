package com.academy;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

/**
 * This class is responsible for running all features/scenario's on Firefox.
 * You can include either a feature or a scenario in the test suite by adding the @firefox tag.
 * The test runner is picked up by the maven-surefire-plugin by calling 'mvn test -Dbrowser=firefox'.
 */
@Suite
@SuiteDisplayName("Cucumber Integration Tests - FIREFOX")
@IncludeEngines("cucumber")
@SelectClasspathResource("nl/example")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "nl.example")
@IncludeTags("firefox")
public class TestRunnerFirefox {
}
