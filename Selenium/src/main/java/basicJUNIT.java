import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class basicJUNIT {

    WebDriver driver;

    @Before
    public void before() {
        driver = new ChromeDriver();
        System.out.println("Before");
    }



    @Test
    public void Test() {
        driver.navigate().to("https://www.qa.com");
    }

    @After
    public void testOver() {

        String Title = driver.getTitle();
        System.out.println(Title);
        driver.quit();
        System.out.println("After");

    }

//    @BeforeClass
//    public static void beforeClass() {
//
//        System.out.println("Before Class");
//    }
//
//    @AfterClass
//    public static void afterClass() {
//
//        System.out.println("After Class");
//    }

}
