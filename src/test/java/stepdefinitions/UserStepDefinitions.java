package stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.UserHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Request.UserRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.Is.is;

public class UserStepDefinitions {

    private  UserHelper userHelper;
    private Response response;
    private UserRequest userRequest;


    @Given("^I create a user with invalid data$")
    public void iCreateAuserWithInvalidData() throws IOException {
        userHelper = new UserHelper();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(objectMapper.readTree(new File("src/test/resources/testData/userRequestInvalidData.json")));

        response = userHelper.createUser(jsonString);
    }

    @Then("^The user should be created$")
    public void theUserShouldBeCreated() {
        response.then().log().everything()
                .and().assertThat().body(matchesJsonSchemaInClasspath("schemas/userResponse.json"))
                .and().assertThat().body("id", is(userRequest.getId()))
                .and().assertThat().body("username", is(userRequest.getUsername()))
                .and().assertThat().body("firstName", is(userRequest.getFirstName()))
                .and().assertThat().body("lastName", is(userRequest.getLastName()))
                .and().assertThat().body("email", is(userRequest.getEmail()))
                .and().assertThat().body("password", is(userRequest.getPassword()))
                .and().assertThat().body("phone", is(userRequest.getPhone()))
                .and().assertThat().body("userStatus", is(userRequest.getUserStatus()));
    }

    @Then("^The user should not be created$")
    public void theUserShouldNotBeCreated() {
        response.then().log().everything()
                .and().assertThat().body(matchesJsonSchemaInClasspath("schemas/userResponseError.json"));

    }


    @Then("status code is {int}")
    public void statusCodeIs(int code) {
        response.then().log().everything()
                .and().assertThat().statusCode(code);
    }

    @Given("I create a user with the following data")
    public void iCreateAUserWithTheFollowingData(List<UserRequest> userRequests) {
        userHelper = new UserHelper();
        userRequest = userRequests.getFirst();
        response =userHelper.createUser(userRequest);
    }

    @When("I get the user by username")
    public void iGetTheUserByUsername() {
        userHelper = new UserHelper();
        response =userHelper.getUserByUsername(userRequest.getUsername());
    }

    @And("The user should be returned")
    public void theUserShouldBeReturned() {
        response.then().log().everything()
                .and().assertThat().body("id", is(userRequest.getId()))
                .and().assertThat().body("username", is(userRequest.getUsername()))
                .and().assertThat().body("firstName", is(userRequest.getFirstName()))
                .and().assertThat().body("lastName", is(userRequest.getLastName()))
                .and().assertThat().body("email", is(userRequest.getEmail()))
                .and().assertThat().body("password", is(userRequest.getPassword()))
                .and().assertThat().body("phone", is(userRequest.getPhone()))
                .and().assertThat().body("userStatus", is(userRequest.getUserStatus()));
    }

    @When("I delete the user by username")
    public void iDeleteTheUserByUsername() {
        userHelper = new UserHelper();
        response =userHelper.deleteUserByUsername(userRequest.getUsername());
    }

    @When("I get the user by {string}")
    public void iGetTheUserByCamila(String username) {
        userHelper = new UserHelper();
        response =userHelper.getUserByUsername(username);
    }


}
