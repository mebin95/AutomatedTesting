import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DroppableAction {


    WebDriver driver;

    @FindBy(id = "draggableview")
    WebElement draggaleView;

    @FindBy(id = "droppableview")
    WebElement droppableView;


    public DroppableAction(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);


    }

    public Action dragAndDrop() {

        Actions builder = new Actions(driver);

        Action dragAndDrop = builder.dragAndDrop(draggaleView, droppableView).build();

        return dragAndDrop;

    }


    public Point getLocation() {

        Point loc = draggaleView.getLocation();

        return loc;

    }



}
