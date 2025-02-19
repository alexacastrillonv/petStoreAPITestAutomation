package helpers;

import client.RestAssuredClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.PropertyManager;
import io.restassured.response.Response;
import models.Request.PetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PetHelper extends RestAssuredClient {

    private static Logger LOGGER = LoggerFactory.getLogger(PetHelper.class);

    private ObjectMapper objectMapper;

    public PetHelper() {
        super(PropertyManager.getProperty("baseUrl"));
        this.objectMapper = new ObjectMapper();

    }
    public Response createPet(PetRequest petRequest){
        try {
            return post(PropertyManager.getProperty("createPetPath"), objectMapper.writeValueAsString(petRequest));
        } catch (JsonProcessingException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }
    public Response createPet(String petRequest){
        return post(PropertyManager.getProperty("createPetPath"), petRequest);

    }

    public Response getPetById(Integer id){
        return get(PropertyManager.getProperty("getPetByIdPath").replace("{id}", id.toString()));
    }
    public Response deletePetById(Integer id){
        return delete(PropertyManager.getProperty("deletePetByIdPath").replace("{id}", id.toString()));
    }
}
