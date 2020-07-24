package APITesting.testCases;

import APITesting.testCases.UserAPI;
import framework.base.TestBase;
import org.testng.annotations.Test;

public class VerifyingEmailID extends TestBase {

    @Test(description = "verify emailid format")
    public void verifyEmailIDFormat()
    {
        UserAPI userAPI = new UserAPI();

        userAPI.searchUserId("Delphine");
    }
}
