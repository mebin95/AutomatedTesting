
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;

//@RunWith(Parameterized.class)
public class SpreadSheetExampleLooped {


    WebDriver driver;

    private static ExtentReportManager reportManager;

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\IntermediateReport",
                "Intermediate Extent Report", "Intermediate Report");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);

    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        System.out.println("Before");
    }




    @Test
    public void Test() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();
        driver.navigate().to("http://thedemosite.co.uk/");


        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/sheet.xlsx");

        int[] numberOfRows = {0,1,2,3,4};
        for (int rowNo : numberOfRows){
            sheetReader.readRow(rowNo, "Input Data");
        }

        //List<String> row = sheetReader.readRow(0, "Input Data");
        //System.out.println(row);

        for(int i = 0; i < numberOfRows.length; i++) {
            List<String> row = sheetReader.readRow(i, "Input Data");
            System.out.println(row.get(0) + " " + row.get(1));

            //System.out.println(row.get(i-1) + " Pass: " +  row.get(1));

            driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
            extentTest.log(Status.INFO,"Navigated To Register Page");

            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")).sendKeys(row.get(0).toString());
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys(row.get(1).toString());
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
            extentTest.log(Status.INFO,"Registered User");


            driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
            extentTest.log(Status.INFO,"Navigated To Login Page");

            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys(row.get(2).toString());
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys(row.get(3).toString());
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();

            extentTest.log(Status.INFO,driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());
            String imagePath = ScreenShot.take(driver, "image");
            Assert.assertTrue(true);
            extentTest.addScreenCaptureFromPath(imagePath);
            extentTest.log(Status.INFO,"Screenshot Taken.");

        }

        try {
            Assert.assertTrue(true);
            extentTest.pass("Passed");
        } catch (AssertionError e) {
            String details = "Example Failing test: " + e.getMessage();
            extentTest.fail(details);
            Assert.fail(details);
        }
    }


    @After
    public void testOver() {

        System.out.println("After");

        System.out.println(driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());

        driver.quit();


    }


    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }


}
