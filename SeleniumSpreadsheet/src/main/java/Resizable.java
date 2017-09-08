import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class Resizable {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    ResizableAction resizablePage;


    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\Resizable",
                "Intermediate Extent Report", "Resizable");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);

    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.get("http://demoqa.com/resizable/");
    }


    @Test
    public void Test() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();

        resizablePage = new ResizableAction(driver);

        Point beforeDrag = resizablePage.getLocation();
        extentTest.log(Status.INFO, "Preparing to move resizable object from " + beforeDrag.toString());

        String imagePath = ScreenShot.take(driver, "beforeResizable");
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath);
        extentTest.log(Status.INFO, "Screenshot Taken Before.");

        resizablePage.dragAndDrop().perform();


        String imagePath1 = ScreenShot.take(driver, "afterResizable");
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath1);
        extentTest.log(Status.INFO, "Screenshot Taken After.");

        Point afterDrag = resizablePage.getLocation();

        extentTest.log(Status.INFO, "Object moved to " + afterDrag.toString());


        try {

            if (beforeDrag != afterDrag) {

                extentTest.pass("Passed");

            } else{

                extentTest.fail("Failed");

            }
        } catch (AssertionError e) {
            String details = "Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
        }


    }


    @After
    public void testOver() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.close();

    }


    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }


}
