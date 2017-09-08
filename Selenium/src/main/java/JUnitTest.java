
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//import utils.reporting.ExtentReportManager;
//import utils.reporting.ReportDetails;

import java.io.IOException;

public class JUnitTest {
    private WebDriver webDriver;
    private static ExtentReportManager reportManager;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\TestReport",
                "Basic Extent Report", "Basic Report");
//        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);
    }

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }


    @Test
    public void passingLogLevelTest() {
        ExtentTest passingLogLevelTest = reportManager.setUpTest();
        passingLogLevelTest.log(Status.INFO, "Info level message to show information that allows a NON-TECHNICAL" +
                " person to understand what the test is doing");
        passingLogLevelTest.log(Status.FATAL,
                "Debug level message to display any information a TECHNICAL person might need to know");
        passingLogLevelTest.pass("Training.Example passing test");
    }

    @Test
    public void failingLogLevelTest() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();
        extentTest.log(Status.WARNING, "Used to report an issue that may cause problems within a system");
        webDriver.navigate().to("http://www.facebook.com");
        String imagePath = ScreenShot.take(webDriver, "image");
        Assert.assertTrue(true);
        extentTest.log(Status.ERROR, "Used to report an issue that will cause problems within a system");
        extentTest.addScreenCaptureFromPath(imagePath);
        extentTest.log(Status.FATAL, "Used to report an issue that will fail/break the system");
        try {
            Assert.assertTrue(false);
            extentTest.pass("Passed");
        } catch (AssertionError e) {
            String details = "Training.Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
        }
    }


}
