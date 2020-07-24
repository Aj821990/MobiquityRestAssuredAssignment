package APITesting.apiCalls;

import framework.base.BasePageMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

import static io.restassured.RestAssured.given;

public class verifyEmailID extends BasePageMethod {

    public static void searchEmailId(int postID) {

        Response response = given()
                .spec(SetBaseUri())
                .param("postId", postID)
                .get("/comments");

        JsonPath extractor = response.jsonPath();
        AssertStatusCode(response.statusCode(), 200);
        List<String> emailID = extractor.getList("email");
        log.info("email ids of " + postID + " is = " + emailID);

        for (String emailid : emailID) {
            validateEmailID(emailid);
        }
    }

    public static boolean validateEmailID(String emailID)
    {
        boolean valid = EmailValidator.getInstance().isValid(emailID);
        if (valid==true)
        {
            getUserID.getCountTrue();
            return true;
        }
        else
        {
            getUserID.getCountFalse();
            return false;
        }
    }
}
