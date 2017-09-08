import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class TestShoppingLogin {

    WebDriver driver;
    private static ExtentReportManager reportManager;

    ShoppingElements loginPage;

    private String title;
    private String firstname;
    private String lastname;
    private String email;
    private String pass;
    private String dob;
    private String addLine1;
    private String city;
    private String state;
    private String postcode;
    private String phone;

    Random rand = new Random();

    public TestShoppingLogin(String title, String firstname, String lastname, String email, String pass, String dob, String addLine1, String city, String state, String postcode, String phone) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.pass = pass;
        this.dob = dob;
        this.addLine1 = addLine1;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.phone = phone;
    }

    @BeforeClass
    public static void init() {
        String property = System.getProperty("user.dir");
        ReportDetails reportDetails = new ReportDetails(property + "\\ShoppingLoginTest",
                "Intermediate Extent Report", "Login Test");
        reportDetails.setTheme(Theme.DARK);
        reportManager = new ExtentReportManager(ExtentReportManager.ReportType.HTML, reportDetails);

    }

    @Before
    public void before() {
        driver = new ChromeDriver();
        String startURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

        driver.navigate().to(startURL);
    }

    @Parameterized.Parameters
    public static Collection rowInformation() {
        SpreadSheetReader sheetReader = new SpreadSheetReader(System.getProperty("user.dir") + "/src/main/resources/shoppingLogin.xlsx");
        List<String[]> rows = new ArrayList<String[]>();

        int[] numberOfRows = {0, 1, 2, 3, 4};

        for (int rowNo : numberOfRows) {
            sheetReader.readRow(rowNo, "Input Data");
            List<String> row = sheetReader.readRow(rowNo, "Input Data");
            rows.add(new String[]{
                    row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), row.get(7), row.get(8), row.get(9), row.get(10)});

        }

        return rows;
    }


    @Test
    public void Test() throws IOException, InterruptedException {

        ExtentTest extentTest = reportManager.setUpTest();
        String startURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";

        loginPage = new ShoppingElements(driver);

        loginPage.getWebEmail().click();
        loginPage.getWebEmail().sendKeys(email);



        try {

            loginPage.getCreateAcc().click();


            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(3, SECONDS)
                    .pollingEvery(1, SECONDS)
                    .ignoring(NoSuchElementException.class);
            WebElement foo = wait.until(new Function<WebDriver, WebElement>()
            {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("//*[@id=\"noSlide\"]/h1"));
                }
            });



            extentTest.pass("Passed For " + firstname);

            assertTrue(true);

        } catch (Exception e) {

            extentTest.fail("Failed Email Already Exists: " + email + ". For user: " + firstname);
            assertTrue(false);
        }

        if(title.toLowerCase() == "mr") {
            loginPage.getMr().click();
        } else if(title.toLowerCase() == "mrs") {
            loginPage.getMrs().click();
        }


        double value = Double.parseDouble(dob);
        Date javaDate= DateUtil.getJavaDate(value);
        String dd = new SimpleDateFormat("dd").format(javaDate);
        String mm = new SimpleDateFormat("MMMM").format(javaDate);
        String yyyy = new SimpleDateFormat("yyyy").format(javaDate);


        Thread.sleep(3000);


        loginPage.getFirstname().sendKeys(firstname);
        loginPage.getLastname().sendKeys(lastname);
        loginPage.getPassword().sendKeys(pass);
        loginPage.getAddress().sendKeys(addLine1);
        loginPage.getCity().sendKeys(city);
        loginPage.getState().sendKeys(state);
        loginPage.getPostcode().sendKeys(postcode);
        loginPage.getPhone().sendKeys(phone);
        loginPage.getDay().sendKeys(dd);
        loginPage.getMonth().sendKeys(mm);
        loginPage.getYear().sendKeys(yyyy);

        System.out.println(firstname + lastname);



        Thread.sleep(50000);




    }


    @After
    public void testOver() {

        //driver.quit();


    }


    @AfterClass
    public static void cleanUp() {
        reportManager.clearTests();
    }


}
