import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.base.Function;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;

import java.util.*;


import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class AutoTraderTest1 {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    Random rand = new Random();

    AutoTraderElements salesPage;

    private String postcode;
    private String distance;
    private String make;
    private String model;
    private String priceFrom;
    private String priceTo;

    String startURL = "https://www.autotrader.co.uk/used-cars";

    public AutoTraderTest1(String postcode, String distance, String make, String model, String priceFrom, String priceTo) {
        this.postcode = postcode;
        this.distance = distance;
        this.make = make;
        this.model = model;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\AutoTraderTest1",
                "Auto Trader", "Auto Trader 1");
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
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/autotraderSales.xlsx");
        List<String[]> rows = new ArrayList<String[]>();

        int[] numberOfRows = {0,1};
        //System.out.println(numberOfRows.length);
        for (int rowNo : numberOfRows) {
            sheetReader.readRow(rowNo, "Input Data");
            List<String> row = sheetReader.readRow(rowNo, "Input Data");
            rows.add(new String[]{
                    row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5)});

        }

        return rows;
    }


    @Test
    public void Test() throws IOException, InterruptedException {

        ExtentTest extentTest = reportManager.setUpTest();

        extentTest.log(Status.INFO, "Navigated To Page.");

        salesPage = new AutoTraderElements(driver);

        salesPage.getPostcode().sendKeys(postcode.toUpperCase());

        Select distanceFrom = new Select(salesPage.getDistance());
        distanceFrom.selectByValue(distance.replaceAll("\\.0*$", ""));

        Select make1 = new Select(salesPage.getMake());
        make1.selectByValue(make.toUpperCase().replaceAll("\\.0*$", ""));

        Select model1 = new Select(salesPage.getModel());
        model1.selectByValue(model.toUpperCase().replaceAll("\\.0*$", ""));

        Select priceFrom1 = new Select(salesPage.getPriceFrom());
        priceFrom1.selectByValue(priceFrom.replaceAll("\\.0*$", ""));

        Select priceTo1 = new Select(salesPage.getPriceTo());
        priceTo1.selectByValue(priceTo.replaceAll("\\.0*$", ""));

        extentTest.log(Status.INFO, "Filled In Details");

        int  n = rand.nextInt(50) + 1;

        String imagePath1 = ScreenShot.take(driver, String.valueOf(n) + "autoTrader");
        extentTest.addScreenCaptureFromPath(imagePath1);
        extentTest.log(Status.INFO, "Screenshot Taken.");

        driver.findElement(By.xpath("//*[@id=\"js-known-search-advanced\"]/div[2]/button")).click();

        if(driver.getCurrentUrl() != startURL) {

            Assert.assertTrue(true);
            extentTest.pass("Passed: Search For: " + make + " - " + model);

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
