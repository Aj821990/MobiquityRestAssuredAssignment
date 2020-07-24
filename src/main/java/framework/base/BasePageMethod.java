package framework.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class BasePageMethod extends TestBase{

    public static RequestSpecification SetBaseUri()
    {
        RequestSpecification requestSpec = new RequestSpecBuilder().build();
        requestSpec.baseUri("https://jsonplaceholder.typicode.com/");

        return requestSpec;
    }

    public static void AssertStatusCode(int actualStatusCode, int expectedStatusCode)
    {
        if(actualStatusCode != expectedStatusCode)
            Assert.fail("Mismatch in status code");
    }
}
