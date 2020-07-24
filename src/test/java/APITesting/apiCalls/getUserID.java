package APITesting.apiCalls;

import framework.base.BasePageMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class getUserID extends BasePageMethod {

    private static int countTrue = 0;
    private static int countFalse = 0;

    public void searchUserId(String user) {

        Response response = given()
                .spec(SetBaseUri())
                .param("username", user)
                .get("/users");

        JsonPath extractor = response.jsonPath();
        AssertStatusCode(response.statusCode(), 200);
        String ID = extractor.getString("id");
        ID = ID.replace("[", "");
        ID = ID.replace("]", "");
        int userID = Integer.parseInt(ID);
        log.info("user id of " + user + " is = " + userID);

        getPostID.searchPostId(userID);

        log.info("total successful email id's = " + countTrue);
        log.info("total unsuccessful email id's = " + countFalse);

    }

    public static void getCountTrue() {
        countTrue++;
    }

    public static void getCountFalse() {
        countFalse++;
    }
}
