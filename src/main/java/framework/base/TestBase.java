package framework.base;

import framework.listener.Listener;
import framework.utilities.ReTryTestCase;
import framework.extentFactory.ReportFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import static framework.extentFactory.ReportFactory.createReportFile;

/**
 * This is test base where testNG annotation sequence are defined and
 * which controls the flow of scripts
 */

@Listeners({Listener.class})
public class TestBase {

    public static int RETRY;
    String testNameFromXML = null;
    public static final Logger log = Logger.getLogger("rootLogger");

    public static void initializeConfig(int reTry) {
        RETRY = reTry;
    }

    @Parameters(value = {"reTry"})
    @BeforeSuite
    public void beforeSuite(ITestContext context,
                            @Optional int reTry) {

        initializeConfig(reTry);
        log.info("before creating report");
        createReportFile();
        log.info("after creating report");

        for(ITestNGMethod method : context.getSuite().getAllMethods()) {
            method.setRetryAnalyzer(new ReTryTestCase());
        }
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        org.apache.log4j.PropertyConfigurator.configure("log4j.properties");
        testNameFromXML = this.getClass().getName();
        ReportFactory.createTest(testNameFromXML);
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        ReportFactory.createChildTest(testNameFromXML, method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        IRetryAnalyzer retry = result.getMethod().getRetryAnalyzer();
        if (retry == null) {
            return;
        }
        result.getTestContext().getSkippedTests().removeResult(result.getMethod());
    }
}
