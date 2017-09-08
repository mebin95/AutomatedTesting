import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


public class TabsAction {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"ui-id-1\"]")
    WebElement tab1;

    @FindBy(xpath = "//*[@id=\"ui-id-2\"]")
    WebElement tab2;

    @FindBy(xpath = "//*[@id=\"ui-id-3\"]")
    WebElement tab3;

    public TabsAction(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

    }

    public void tryClick() throws InterruptedException {
        tab1.click();
        //TimeUnit.MINUTES.sleep(3);
        tab2.click();
        //TimeUnit.MINUTES.sleep(3);
        tab3.click();
    }
}
