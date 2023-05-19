package tqs.amanacu.estore;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:resources/User.feature")
public class UserTest {
    // This is just a runner class, no implementation needed here
}
