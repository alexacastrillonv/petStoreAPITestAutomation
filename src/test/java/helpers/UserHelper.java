package helpers;

import client.RestAssuredClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import config.PropertyManager;
import io.restassured.response.Response;
import models.Request.PetRequest;
import models.Request.UserRequest;
import models.Response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserHelper extends RestAssuredClient {
    private static Logger LOGGER = LoggerFactory.getLogger(UserHelper.class);

    private ObjectMapper objectMapper;

    public UserHelper() {
        super(PropertyManager.getProperty("baseUrl"));
        this.objectMapper = new ObjectMapper();

    }

    public Response createUser(UserRequest userRequest){
        try {
            return post(PropertyManager.getProperty("createUserPath"), objectMapper.writeValueAsString(userRequest));
        } catch (JsonProcessingException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }
    public Response createUser(String userRequest){
        return post(PropertyManager.getProperty("createUserPath"), userRequest);

    }

    public Response getUserByUsername(String username){
        return get(PropertyManager.getProperty("getUserByUsernamePath").replace("{username}", username));
    }

    public Response deleteUserByUsername(String username){
        return delete(PropertyManager.getProperty("deleteUserByUsernamePath").replace("{username}", username));
    }

}
