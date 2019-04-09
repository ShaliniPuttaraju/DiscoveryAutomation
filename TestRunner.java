package runner;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@Test
@CucumberOptions(features = "D:\\AutomationWrkSpace\\DiscoveryApp\\src\\featurefile\\VerifyingFavShows.feature",
glue = "D:\\AutomationWrkSpace\\DiscoveryApp\\src\\stepDefinition",
tags = "@Test")
public class TestRunner extends AbstractTestNGCucumberTests {

}
