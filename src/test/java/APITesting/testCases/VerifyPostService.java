package APITesting.testCases;

import framework.utilities.Constants;
import framework.utilities.CustomeException;
import framework.base.TestBase;
import org.testng.annotations.Test;

import static APITesting.apiCalls.SearchPostID.searchPostId;

public class VerifyPostService extends TestBase {

    @Test(description = "verify postID of valid userid")
    public void verifyPostIDForValidUser() throws CustomeException.IDNotFoundException {
        searchPostId(9, Constants.SUCCESSSTATUSCODE);
    }

    @Test(description = "verify postID of invalid userid")
    public void verifyPostIDForInValidUser() throws CustomeException.IDNotFoundException {
        searchPostId(55, Constants.NOTFOUNDSTATUSCODE);
    }

    @Test(description = "verify postID of non integer userid")
    public void verifyPostIDForNonIntegerUser() throws CustomeException.IDNotFoundException {
        searchPostId(55, Constants.BADREQUESTSTATUSCODE);
    }

    @Test(description = "verify postID of blank userid")
    public void verifyPostIDForBlankUser() throws CustomeException.IDNotFoundException {
        searchPostId(Constants.BLANKID, Constants.BADREQUESTSTATUSCODE);
    }

}
