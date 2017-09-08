import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ResizableAction {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"resizable\"]/div[3]")
    WebElement resizable;

    public ResizableAction(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);


    }

    public Action dragAndDrop() {

        Actions builder = new Actions(driver);

        Action resize = builder.clickAndHold(resizable).moveByOffset(300, 230).release().build();

        return resize;

    }


    public Point getLocation() {

        Point loc = resizable.getLocation();

        return loc;

    }


}
