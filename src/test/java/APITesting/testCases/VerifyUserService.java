package APITesting.testCases;

import framework.Utilities.Constants;
import framework.Utilities.CustomeException;
import framework.base.TestBase;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;


import java.io.IOException;

import static APITesting.apiCalls.SearchUserID.searchUserId;

public class VerifyUserService extends TestBase {

    @Test(description = "verify user service for valid user")
    public void verifyValidUser() throws CustomeException.IDNotFoundException, IOException, ParseException {
        searchUserId(Constants.VALIDUSER, Constants.SUCCESSSTATUSCODE);
    }
/*

    @Test(description = "verify user service for invalid user")
    public void verifyInValidUser() throws CustomeException.IDNotFoundException, IOException, ParseException {
        searchUserId(Constants.INVALIDUSER, Constants.NOTFOUNDSTATUSCODE);
    }

    @Test(description = "verify user service for invalid numeric user")
    public void verifyInvalidNumericUser() throws CustomeException.IDNotFoundException, IOException, ParseException {
        searchUserId(Constants.INVALIDNUMERICUSER, Constants.BADREQUESTSTATUSCODE);
    }

    @Test(description = "verify user service for blank user")
    public void verifyBlankUser() throws CustomeException.IDNotFoundException, IOException, ParseException {
        searchUserId(Constants.BLANKUSER, Constants.BADREQUESTSTATUSCODE);
    }
*/
}
