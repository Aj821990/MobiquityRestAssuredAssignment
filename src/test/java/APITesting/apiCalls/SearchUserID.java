package APITesting.apiCalls;

import framework.utilities.CustomeException;
import framework.base.BasePageMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class SearchUserID extends BasePageMethod {

    // userID is made static to make more memory efficient
    static int userID;

    public void searchUserId(String user, int responseStatusCode) throws CustomeException.IDNotFoundException, IOException, ParseException {

        Response response = given()
                .spec(SetBaseUri())
                .param("username", user)
                .get("/users");

        AssertStatusCode(response.statusCode(), responseStatusCode);

        String responseBody = response.getBody().asString();

        assertThat(responseBody,matchesJsonSchemaInClasspath("userAPI.json"));

        JsonPath extractor = response.jsonPath();
        String ID = extractor.getString("id");
        ID = ID.replace("[", "");
        ID = ID.replace("]", "");
        userID = Integer.parseInt(ID);

        // validates whether key "id" is present or not in the response
        if (userID == 0) {
            throw new CustomeException.IDNotFoundException("Not found : '" + user + "' user");
        }

        log.info("user id of " + user + " is = " + userID);
    }

    public int getUserID(String username, int responseStatusCode) throws CustomeException.IDNotFoundException, IOException, ParseException {
        // searchUserID is called
        searchUserId(username, responseStatusCode);
        return userID;
    }
}
