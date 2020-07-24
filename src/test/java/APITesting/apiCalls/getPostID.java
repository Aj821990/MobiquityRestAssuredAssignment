package APITesting.apiCalls;

import com.sun.xml.bind.v2.model.core.ID;
import framework.base.BasePageMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class getPostID extends BasePageMethod {

    public static void searchPostId(int userID) {

        Response response = given()
                .spec(SetBaseUri())
                .param("userId", userID)
                .get("/posts");

        //response.prettyPrint();

        JsonPath extractor = response.jsonPath();
        AssertStatusCode(response.statusCode(), 200);
        List IDs = extractor.getList("id");
        log.info("post ids of " + userID + " is = " + IDs);

        for(int i=0; i==IDs.size(); i++)
        {
            //get email and verify
        }

    }
}
