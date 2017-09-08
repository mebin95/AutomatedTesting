
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Random;


@RunWith(Parameterized.class)
public class SpreadSheetExample {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    private String username;
    private String pass;
    private String usernameL;
    private String passL;
    private String check;

    Random rand = new Random();




    public SpreadSheetExample(String username, String pass, String usernameL, String passL, String check) {
        this.username = username;
        this.pass = pass;
        this.usernameL = usernameL;
        this.passL = passL;
        this.check = check;
    }



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

    @Parameterized.Parameters
    public static Collection rowInformation() {
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/sheet.xlsx");
        List<String[]> rows = new ArrayList<String[]>();

        int[] numberOfRows = {0, 1, 2, 3, 4};
        for (int rowNo : numberOfRows) {
            sheetReader.readRow(rowNo, "Input Data");
            List<String> row = sheetReader.readRow(rowNo, "Input Data");
            rows.add(new String[]{
                    row.get(0), row.get(1), row.get(2), row.get(3), row.get(4)});


        }

        return rows;
    }


    @Test
    public void Test() throws IOException {
        ExtentTest extentTest = reportManager.setUpTest();

        driver.navigate().to("http://thedemosite.co.uk/");

        driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")).click();
        extentTest.log(Status.INFO, "Navigated To Register Page");

        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")).sendKeys(username);
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")).sendKeys(pass);

        int  n = rand.nextInt(50) + 1;

        String imagePath = ScreenShot.take(driver, String.valueOf(n));
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath);


        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
        extentTest.log(Status.INFO, "Registered User");
        System.out.println(username + " " + pass);


        driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
        extentTest.log(Status.INFO, "Navigated To Login Page");

        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")).sendKeys(usernameL);
        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")).sendKeys(passL);


        String imagePath1 = ScreenShot.take(driver, String.valueOf(n));
        Assert.assertTrue(true);
        extentTest.addScreenCaptureFromPath(imagePath1);
        extentTest.log(Status.INFO, "Screenshot Taken.");

        driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();
        System.out.println(usernameL + " " + passL);

        extentTest.log(Status.INFO, driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());

        extentTest.log(Status.INFO, "Screenshot Taken.");


        try {
           // Assert.assertTrue(true);
            //extentTest.pass("Passed");
            if(driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText().equals(check)) {
                extentTest.pass("Passed");
            } else {
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

        System.out.println("After");

        System.out.println(driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")).getText());

        driver.quit();


    }


    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }



}
