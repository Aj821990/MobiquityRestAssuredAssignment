package APITesting.testCases;

import framework.utilities.Constants;
import framework.utilities.CustomeException;
import framework.base.TestBase;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static APITesting.apiCalls.SearchEmailID.verifyEmailID;

public class VerifyEmailID extends TestBase {

    @Test(description = "verify emailid of valid user")
    public void verifyEmailIDForValidUser() throws CustomeException.IDNotFoundException, IOException, ParseException {
        verifyEmailID(Constants.VALIDUSER, Constants.SUCCESSSTATUSCODE);
    }

}
