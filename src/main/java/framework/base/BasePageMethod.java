package framework.base;

import framework.utilities.Constants;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

public class BasePageMethod extends TestBase{

    public static RequestSpecification SetBaseUri()
    {
        RequestSpecification requestSpec = new RequestSpecBuilder().build();
        requestSpec.baseUri(Constants.BASEURL);

        return requestSpec;
    }

    public static void AssertStatusCode(int actualStatusCode, int expectedStatusCode)
    {
        if(actualStatusCode != expectedStatusCode)
        {
            Assert.assertEquals(actualStatusCode, expectedStatusCode);
        }
    }

    public static void assertSchema(String jsonBody, String path)
    {
        assertThat(jsonBody,matchesJsonSchemaInClasspath(path));
    }
}
