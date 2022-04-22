package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class SimpleFormDemo extends BasePage {

    public SimpleFormDemo(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "form#get-input input")
    @CacheLookup
    private WebElement formToSendMessageElement;

    @FindBy(css = "form#get-input>button")
    @CacheLookup
    private WebElement showMessageButtonElement;

    @FindBy(css = "div#user-message")
    @CacheLookup
    private WebElement receivedMessageElement;

    @FindBy(css = "#sum1")
    @CacheLookup
    private WebElement formToSendAElement;

    @FindBy(css = "#sum2")
    @CacheLookup
    private WebElement formToSendBElement;

    @FindBy(css = "#gettotal button")
    @CacheLookup
    private WebElement getTotalButtonElement;

    @FindBy(css = "#gettotal+div:not([class]")
    @CacheLookup
    private WebElement receivedSumElement;

    public SimpleFormDemo sendMessage(String message) {
        formToSendMessageElement.sendKeys(message);
        return new SimpleFormDemo(driver);
    }

    public SimpleFormDemo clickOnShowMessageButton() {
        showMessageButtonElement.click();
        return new SimpleFormDemo(driver);
    }

    public String showReceivedMessage() {
        return receivedMessageElement.getText();
    }

    public SimpleFormDemo sendValues(String a, String b) {
        formToSendAElement.sendKeys(a);
        formToSendBElement.sendKeys(b);
        return new SimpleFormDemo(driver);
    }

    public SimpleFormDemo clickOnGetTotalButton() {
        getTotalButtonElement.click();
        return new SimpleFormDemo(driver);
    }

    public String showTotalSum() {
        return receivedSumElement.getText();
    }
}
