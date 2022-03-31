package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/Appfeatures/OnboardingFlow.feature"},
		//features = {"src/test/resources/Appfeatures/Registration.feature"},
		glue= {"stepdefinitions","appHooks"},
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedScenarios.txt"
				})

public class MyTestRunners {
	
}
