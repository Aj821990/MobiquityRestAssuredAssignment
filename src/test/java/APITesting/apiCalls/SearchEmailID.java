package APITesting.apiCalls;

import framework.utilities.CustomeException;
import framework.base.BasePageMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.validator.routines.EmailValidator;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SearchEmailID extends BasePageMethod {

    public static List<String> searchEmailId(int postID, int responseStatusCode) throws CustomeException.IDNotFoundException
    {
        Response response = given()
                .spec(SetBaseUri())
                .param("postId", postID)
                .get("/comments");

        String responseBody = response.getBody().asString();

        //response schema validation
        assertSchema(responseBody,"schema/commentsAPI.json");

        Assert.assertEquals(responseBody.contains("email") , true , "Response body contains email");

        JsonPath extractor = response.jsonPath();
        // validates whether the response code is as expected
        AssertStatusCode(response.statusCode(), responseStatusCode);
        // adds the list of email ids into list emailID
        List<String> emailID = extractor.getList("email");

        // displaying all thee emails for the specific postid
        log.info("email ids of " + postID + " is = " + emailID);

        return emailID;
    }

    public static void verifyEmailID (String userName, int responseStatusCode) throws CustomeException.IDNotFoundException, IOException, ParseException {
        // creating instance for searchPostID class
        SearchPostID searchPostID = new SearchPostID();
        // all post ids are saved in list postID
        List<Integer> postID = searchPostID.getPostID(userName, responseStatusCode);
        List<String> emailID = null;

        // every post id, searchEmailID is called
        for (int postid : postID){
            emailID = searchEmailId(postid, responseStatusCode);
            //for every email id, validation is done to check the format
            for(String emailid : emailID)
            {
                boolean valid = EmailValidator.getInstance().isValid(emailid);

                if (valid == true)
                {
                    log.info(emailid + " is valid email id");
                } else {
                    log.info(emailid + " is not valid email id");
                }
            }
        }
    }
}
