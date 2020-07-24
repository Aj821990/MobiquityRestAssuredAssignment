package APITesting.testCases;

import framework.base.BasePageMethod;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class UserAPI extends BasePageMethod {

    @Test
    public String searchUserId(String name) {

        RequestSpecification requestSpec = new RequestSpecBuilder().build();
        requestSpec.baseUri("https://jsonplaceholder.typicode.com/");

        Response response = given()
                .spec(requestSpec)
                .param("username", name)
                .log()
                .all()
                .get("/users");

        JsonPath extractor = response.jsonPath();
        String userID = extractor.getString("id");
        log.info(userID);

        return userID;
    }
}
