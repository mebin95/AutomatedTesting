
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class Draggable {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    DraggableAction draggablePage;


    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\Draggable",
                "Intermediate Extent Report", "Draggable");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);

    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.get("http://demoqa.com/draggable/");
    }


    @Test
    public void Test() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();

        draggablePage = new DraggableAction(driver);

        Point beforeDrag = draggablePage.getLocation();
        extentTest.log(Status.INFO, "Preparing to move draggable object from " + beforeDrag.toString());

        String imagePath = ScreenShot.take(driver, "draggedFrom");
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath);
        extentTest.log(Status.INFO, "Screenshot Taken Before.");

        draggablePage.dragAndDrop().perform();

        String imagePath1 = ScreenShot.take(driver, "draggedAfter");
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath1);
        extentTest.log(Status.INFO, "Screenshot Taken After.");

        Point afterDrag = draggablePage.getLocation();

        extentTest.log(Status.INFO, "Object dragged & dropped to " + afterDrag.toString());


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
