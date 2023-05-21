package tqs.amanacu.estore;

import tqs.amanacu.estore.repositories.UserRepository;
import tqs.amanacu.estore.security.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest 
@AutoConfigureMockMvc
public class AuthorizationControllerSteps {
   
  private ResultActions resultActions;
  private MvcResult mvcResult;
  private String requestBody;

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private AuthenticationManager authenticationManager;

  @MockBean
  private PasswordEncoder encoder;
 

  @Given("^a login request with username \"(.*)\" and password \"(.*)\"$")
  public void createLoginRequest(String username, String password) {
    requestBody = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);
  }

  @When("^the login request is sent$")
  public void sendLoginRequest() throws Exception {
    when(authenticationManager.authenticate(any())).thenReturn(null);

    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post("/api/auth/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestBody);

    resultActions = mockMvc.perform(requestBuilder);
    mvcResult = resultActions.andReturn();
  }

  @Then("^the login response status code should be (\\d+)$")
  public void verifyLoginResponseStatusCode(int expectedStatusCode) throws Exception {
    assertEquals(expectedStatusCode, mvcResult.getResponse().getStatus());
  }

  // Add more step definitions for other scenarios and endpoints as needed

}
