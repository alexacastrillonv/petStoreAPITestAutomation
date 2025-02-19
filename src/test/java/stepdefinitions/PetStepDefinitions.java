package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.PetHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.Request.PetRequest;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.Is.is;

public class PetStepDefinitions {
    private PetHelper petHelper;
    private Response response;
    private PetRequest petRequest;

    @Given("I create a pet with the following data")
    public void iCreateAPetWithTheFollowingData(List<PetRequest> petRequests) {
        petHelper = new PetHelper();
        petRequest = petRequests.getFirst();
        response =petHelper.createPet(petRequest);
    }

    @And("The pet should be created")
    public void thePetShouldBeCreated()  {
        ObjectMapper objectMapper = new ObjectMapper();
        response.then().log().everything()
                .and().assertThat().body(matchesJsonSchemaInClasspath("schemas/petResponse.json"))
                .and().assertThat().body("id", is(petRequest.getId()))
                .and().assertThat().body("name", is(petRequest.getName()))
                .and().assertThat().body("category.id", is(petRequest.getCategory().getId()))
                .and().assertThat().body("category.name", is(petRequest.getCategory().getName()))
                .and().assertThat().body("photoUrls", is(petRequest.getPhotoUrls()))
                .and().assertThat().body("tags", is(objectMapper.convertValue(petRequest.getTags(), List.class)))
                .and().assertThat().body("status", is(petRequest.getStatus()));
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(int code) {
        response.then().log().everything()
                .and().assertThat().statusCode(code);
    }

    @Given("I create a pet with invalid data")
    public void iCreateAPetWithInvalidData() throws IOException {
        petHelper = new PetHelper();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(objectMapper.readTree(new File("src/test/resources/testData/petRequestInvalidData.json")));
        response = petHelper.createPet(jsonString);
    }

    @And("The pet should not be created")
    public void thePetShouldNotBeCreated() {
        response.then().log().everything()
                .and().assertThat().body(matchesJsonSchemaInClasspath("schemas/petResponseError.json"));
    }

    @When("I get the pet by id")
    public void iGetThePetById() {
        petHelper = new PetHelper();
        response = petHelper.getPetById(petRequest.getId());
    }

    @And("The pet should be returned")
    public void thePetShouldBeReturned() {
        ObjectMapper objectMapper = new ObjectMapper();
        response.then().log().everything()
                .and().assertThat().body("id", is(petRequest.getId()))
                .and().assertThat().body("name", is(petRequest.getName()))
                .and().assertThat().body("category.id", is(petRequest.getCategory().getId()))
                .and().assertThat().body("category.name", is(petRequest.getCategory().getName()))
                .and().assertThat().body("photoUrls", is(petRequest.getPhotoUrls()))
                .and().assertThat().body("tags", is(objectMapper.convertValue(petRequest.getTags(), List.class)))
                .and().assertThat().body("status", is(petRequest.getStatus()));
    }

    @When("I delete the pet by id")
    public void iDeleteThePetById() {
        petHelper = new PetHelper();
        response = petHelper.deletePetById(petRequest.getId());
    }

    @When("I get the pet by {int}")
    public void iGetThePetBy(Integer id) {
        petHelper = new PetHelper();
        response = petHelper.getPetById(id);
    }

}
