package APITesting.testCases;

import APITesting.apiCalls.getUserID;
import framework.Utilities.Constants;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class VerifyingEmailID extends TestBase {

    @Test(description = "verify emailid format")
    public void verifyEmailIDFormat()
    {
        getUserID getUserID = new getUserID();

        getUserID.searchUserId(Constants.USER);
    }
}
