import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class Tabs {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    TabsAction tabsPage;


    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\Tabs",
                "Intermediate Extent Report", "Tabs");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);

    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.get("http://demoqa.com/tabs/");
    }


    @Test
    public void Test() throws IOException, InterruptedException {
        ExtentTest extentTest = reportManager.setUpTest();

        tabsPage = new TabsAction(driver);
        extentTest.log(Status.INFO, "Clicking Tabs.");

        tabsPage.tryClick();

        String imagePath1 = ScreenShot.take(driver, "clickingTABS");
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath1);
        extentTest.log(Status.INFO, "Screenshot Taken.");




        try {

            extentTest.pass("Passed");

        } catch (AssertionError e) {
            String details = "Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
        }


    }


    @After
    public void testOver() {

        driver.close();

    }


    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }


}
