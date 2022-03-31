package testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = {"src/test/resources/Appfeatures/CarParks.feature"},
		glue= {"stepdefinitions","appHooks"},
		plugin = {"pretty"})


public class SerenityTestRunner {

}
