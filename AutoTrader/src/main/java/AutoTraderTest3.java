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
public class AutoTraderTest3 {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    Random rand = new Random();

    private String reg;
    private String miles;
    private String sellFor;


    String startURL = "https://selling.autotrader.co.uk/find-car?reg=&mileage=&referrer=ATCOUK";

    public AutoTraderTest3(String reg, String miles, String sellFor) {
        this.reg = reg;
        this.miles = miles;
        this.sellFor = sellFor;
    }

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\AutoTraderTest2",
                "Auto Trader", "Auto Trader 2");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);

    }

    @Before
    public void before() {
        driver = new ChromeDriver();


        driver.navigate().to(startURL);
    }

    @Parameterized.Parameters
    public static Collection rowInformation() {
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/regNum.xlsx");
        List<String[]> rows = new ArrayList<String[]>();

        int[] numberOfRows = {0, 1, 2};
        //System.out.println(numberOfRows.length);
        for (int rowNo : numberOfRows) {
            sheetReader.readRow(rowNo, "Input Data");
            List<String> row = sheetReader.readRow(rowNo, "Input Data");
            rows.add(new String[]{
                    row.get(0), row.get(1), row.get(2)});
        }

        return rows;
    }


    @Test
    public void Test() throws IOException, InterruptedException {



        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Navigated To Page.");

        driver.findElement(By.xpath("//*[@id=\"reg\"]")).sendKeys(reg);
        driver.findElement(By.xpath("//*[@id=\"mileage\"]")).sendKeys(miles);
        driver.findElement(By.xpath("//*[@id=\"findDetails\"]")).click();

        extentTest.log(Status.INFO, "Finding Car Details");

        Thread.sleep(1000);

        int n = rand.nextInt(50) + 1;

        String imagePath1 = ScreenShot.take(driver, String.valueOf(n) + "autoTraderTest2");
        extentTest.addScreenCaptureFromPath(imagePath1);
        extentTest.log(Status.INFO, "Screenshot Taken.");

        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"submitPage\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"askingPrice\"]")).sendKeys(sellFor.replaceAll("\\.0*$", ""));

        extentTest.log(Status.INFO, "Filled In Details");

        driver.findElement(By.xpath("//*[@id=\"setPriceButton\"]")).click();

        System.out.println(sellFor.replaceAll("\\.0*$", ""));

        if (driver.getCurrentUrl() != startURL) {

            Assert.assertTrue(true);
            extentTest.pass("Passed: Selling " + reg + " - " + miles.replaceAll("\\.0*$", ""));

        } else {
            extentTest.fail("Failed");
            Assert.fail();
        }

    }


    @After
    public void testOver() {

        driver.quit();


    }


    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }


}
