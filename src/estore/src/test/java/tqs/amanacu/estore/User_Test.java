package tqs.amanacu.estore;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:resources/User.feature")
public class User_Test {
    // This is just a runner class, no implementation needed here
}