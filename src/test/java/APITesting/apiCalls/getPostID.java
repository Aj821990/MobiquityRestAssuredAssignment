package APITesting.apiCalls;

import framework.base.BasePageMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class getPostID extends BasePageMethod {

    public static void searchPostId(int userID) {

        Response response = given()
                .spec(SetBaseUri())
                .param("userId", userID)
                .get("/posts");

        JsonPath extractor = response.jsonPath();
        AssertStatusCode(response.statusCode(), 200);

        List<Integer> postID = extractor.getList("id");
        log.info("post ids of " + userID + " is = " + postID);

        for(int postid : postID)
        {
            verifyEmailID.searchEmailId(postid);
        }
    }
}
