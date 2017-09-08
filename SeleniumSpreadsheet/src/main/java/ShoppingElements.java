import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ShoppingElements {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"email_create\"]")
    WebElement webEmail;

    @FindBy(xpath = "//*[@id=\"SubmitCreate\"]")
    WebElement createAcc;

    @FindBy(xpath = "//*[@id=\"create_account_error\"]")
    WebElement emailTaken;

    @FindBy(xpath = "//*[@id=\"id_gender1\"]") //M
            WebElement mr;

    @FindBy(xpath = "//*[@id=\"id_gender2\"]") //F
            WebElement mrs;

    @FindBy(xpath = "//*[@id=\"customer_firstname\"]")
    WebElement firstname;

    @FindBy(xpath = "//*[@id=\"customer_lastname\"]")
    WebElement lastname;

    @FindBy(xpath = "//*[@id=\"passwd\"]")
    WebElement password;

    @FindBy(xpath = "//*[@id=\"address1\"]")
    WebElement address;

    @FindBy(xpath = "//*[@id=\"city\"]")
    WebElement city;

    @FindBy(xpath = "//*[@id=\"id_state\"]")
    WebElement state;

    @FindBy(xpath = "//*[@id=\"postcode\"]")
    WebElement postcode;

    @FindBy(xpath = "//*[@id=\"phone_mobile\"]")
    WebElement phone;

    @FindBy(xpath = "//*[@id=\"submitAccount\"]")
    WebElement submit;

    @FindBy(xpath = "//*[@id=\"days\"]")
    WebElement day;

    @FindBy(xpath = "//*[@id=\"months\"]")
    WebElement month;

    @FindBy(xpath = "//*[@id=\"years\"]")
    WebElement year;


    public ShoppingElements(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

    }

    public WebElement getWebEmail() {
        return webEmail;
    }

    public WebElement getCreateAcc() {
        return createAcc;
    }

    public WebElement getEmailTaken() {
        return emailTaken;
    }

    public WebElement getMr() {
        return mr;
    }

    public WebElement getMrs() {
        return mrs;
    }

    public WebElement getFirstname() {
        return firstname;
    }

    public WebElement getLastname() {
        return lastname;
    }

    public WebElement getPassword() {
        return password;
    }


    public WebElement getAddress() {
        return address;
    }

    public WebElement getCity() {
        return city;
    }

    public WebElement getState() {
        return state;
    }

    public WebElement getPostcode() {
        return postcode;
    }

    public WebElement getPhone() {
        return phone;
    }

    public WebElement getSubmit() {
        return submit;
    }


    public WebElement getDay() {
        return day;
    }

    public WebElement getMonth() {
        return month;
    }

    public WebElement getYear() {
        return year;
    }
}
