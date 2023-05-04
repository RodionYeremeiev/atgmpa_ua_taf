package com.epam.testrunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/java/com/epam/features"},
        glue = {"com/epam/stepdefinitions", "com/epam/hooks"},
        plugin = {"pretty"}
)
public class CucumberRunner {
}
