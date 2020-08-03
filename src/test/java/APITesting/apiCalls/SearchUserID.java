package APITesting.apiCalls;

import framework.Utilities.CustomeException;
import framework.base.BasePageMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SearchUserID extends BasePageMethod {

    // userID is made static to make more memory efficient
    static int userID;

    public static void searchUserId(String user, int responseStatusCode) throws CustomeException.IDNotFoundException, IOException, ParseException {

        Response response = given()
                .spec(SetBaseUri())
                .param("username", user)
                .get("/users");

        AssertStatusCode(response.statusCode(), responseStatusCode);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/test/resources/userAPI.json"));

        // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
        JSONObject jsonObject = (JSONObject) obj;

        log.info("userAPI saved response file  is : " + jsonObject);

        Assert.assertEquals(response.getBody().asString(), jsonObject);
        log.info("schema matched");

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
