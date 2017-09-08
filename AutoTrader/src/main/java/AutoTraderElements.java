import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class AutoTraderElements {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[1]/input")
    WebElement postcode;

    @FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[1]/div/select")
    WebElement distance;

    @FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[6]/div/select")
    WebElement make;

    @FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[7]/div/select")
            WebElement model;

    @FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[8]/div[1]/select")
            WebElement priceFrom;

    @FindBy(xpath = "//*[@id=\"js-known-search-advanced\"]/div[1]/div[8]/div[2]/select")
    WebElement priceTo;

    @FindBy(css = "button.buying-page__submit-button:nth-child(11)")
    WebElement submit;


    public AutoTraderElements(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

    }


    public WebElement getPostcode() {
        return postcode;
    }

    public WebElement getDistance() {
        return distance;
    }

    public WebElement getMake() {
        return make;
    }

    public WebElement getModel() {
        return model;
    }

    public WebElement getPriceFrom() {
        return priceFrom;
    }

    public WebElement getPriceTo() {
        return priceTo;
    }

    public WebElement getSubmit() {
        return submit;
    }
}
