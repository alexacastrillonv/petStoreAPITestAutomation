package runners;

import io.cucumber.java.DataTableType;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import models.Request.Category;
import models.Request.PetRequest;
import models.Request.Tags;
import models.Request.UserRequest;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {"pretty"}
)
public class RunCucumberTest {
    @DataTableType
    public UserRequest userEntryTransformer(Map<String, String> entry) {
        return new UserRequest(
                Integer.parseInt(entry.get("id")),
                entry.get("username"),
                entry.get("firstName"),
                entry.get("lastName"),
                entry.get("email"),
                entry.get("password"),
                entry.get("phone"),
                Integer.parseInt(entry.get("userStatus"))
        );
    }
    @DataTableType
    public PetRequest petEntryTransformer(Map<String, String> entry) {
        return new PetRequest(
                Integer.parseInt(entry.get("id")),
                entry.get("name"),
                new Category(Integer.parseInt(entry.get("categoryId")), entry.get("categoryName")),
                Arrays.asList(entry.get("photoUrls").split(",")),
                Arrays.asList(entry.get("tags").split(",")).stream().map(x->{
                    return new Tags(Integer.parseInt(x.split(":")[0]), x.split(":")[1]);
                }).collect(Collectors.toList()),
                entry.get("status")
        );
    }
}
