package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/Cucumber",glue="SeleniumFrameworkDesign.Automation.stepDefinitions",
monochrome=true,tags = "@Regression",plugin= {"html:target/cucmber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	

}
