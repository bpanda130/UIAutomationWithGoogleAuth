package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/Appfeatures"},
		glue= {"stepdefinitions","appHooks"},
		plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				"rerun:target/failedScenarios.txt"
				}
		,tags = "@UserOnBoarding"
		//,tags = "@UserRegistration"
)

public class MyTestRunners1 {
	
}
