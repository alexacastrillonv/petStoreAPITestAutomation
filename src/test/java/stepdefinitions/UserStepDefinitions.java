package stepdefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.UserHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import models.Request.UserRequest;

import java.io.File;
import java.io.IOException;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.Is.is;

public class UserStepDefinitions {

    private  UserHelper userHelper;
    private Response response;
    private UserRequest userRequest;


    @Given("^I create a user$")
    public void i_create_a_user() {
        userHelper = new UserHelper();
        userRequest = UserRequest.builder()
                .id(1)
                .username("laura43")
                .firstName("Laura")
                .lastName("Castillo")
                .email("laura43@gmail.com")
                .password("123456")
                .phone("123456789")
                .userStatus(1)
                .build();

         response =userHelper.createUser(userRequest);
    }

    @Given("^I create a user with invalid data$")
    public void i_create_a_user_with_invalid_data() throws IOException {
        userHelper = new UserHelper();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(objectMapper.readTree(new File("src/test/resources/testData/userRequestInvalidData.json")));

        response = userHelper.createUser(jsonString);
    }

    @Then("^The user should be created$")
    public void the_user_should_be_created() {
        response.then().log().everything()
        .and().assertThat().statusCode(200)
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
    public void the_user_should_not_be_created() {
        response.then().log().everything()
                .and().assertThat().statusCode(400)
                .and().assertThat().body(matchesJsonSchemaInClasspath("schemas/userResponseError.json"));

    }

    @Given("^I create a user with the following data$")
    public void i_create_a_user_with_the_following_data()  {
        userHelper = new UserHelper();
        userRequest = UserRequest.builder()
                .id(1)
                .username("laura43")
                .firstName("Laura")
                .lastName("Castillo")
                .email("laura43@gmail.com")
                .password("123456")
                .phone("123456789")
                .userStatus(1)
                .build();

        response =userHelper.createUser(userRequest);
    }
}
