import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DraggableAction {


    WebDriver driver;

    @FindBy(id = "draggable")
    WebElement draggable;

    public DraggableAction(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);


    }

    public Action dragAndDrop() {

        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.dragAndDropBy(draggable, 300, 100).build();

        return dragAndDrop;

    }


    public Point getLocation() {

        Point loc = draggable.getLocation();

        return loc;

    }



}
