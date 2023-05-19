package tqs.amanacu.estore;

import tqs.amanacu.estore.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserSteps {

    private User user;
    private String username;
    private String email;
    private String password;
    private Validator validator;

    @Given("^a user with username \"(.*)\" and email \"(.*)\" and password \"(.*)\"$")
    public void createUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @When("^the user is created$")
    public void createUser() {
        user = new User(username, email, password);
    }

    @Then("^the user's username should be \"(.*)\"$")
    public void verifyUsername(String expectedUsername) {
        assertEquals(expectedUsername, user.getUsername());
    }

    @Then("^the user's email should be \"(.*)\"$")
    public void verifyEmail(String expectedEmail) {
        assertEquals(expectedEmail, user.getEmail());
    }

    @Then("^the user's password should be \"(.*)\"$")
    public void verifyPassword(String expectedPassword) {
        assertEquals(expectedPassword, user.getPassword());
    }
}
