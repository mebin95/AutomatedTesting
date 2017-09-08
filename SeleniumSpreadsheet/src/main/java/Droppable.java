import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Droppable {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    DroppableAction droppablePage;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\Droppable",
                "Intermediate Extent Report", "Droppable");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);

    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.get("http://demoqa.com/droppable/");
    }


    @Test
    public void Test() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();

        droppablePage = new DroppableAction(driver);

        Point beforeDrag = droppablePage.getLocation();
        extentTest.log(Status.INFO, "Preparing to move droppable object from " + beforeDrag.toString());

        String imagePath = ScreenShot.take(driver, "beforeDroppable");
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath);
        extentTest.log(Status.INFO, "Screenshot Taken Before.");

        droppablePage.dragAndDrop().perform();

        String imagePath1 = ScreenShot.take(driver, "afterDroppable");
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath1);
        extentTest.log(Status.INFO, "Screenshot Taken After.");

        Point afterDrag = droppablePage.getLocation();



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
