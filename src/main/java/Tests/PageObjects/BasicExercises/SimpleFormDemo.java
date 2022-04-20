package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SimpleFormDemo extends BasePage {

    public SimpleFormDemo(WebDriver driver) {
        super(driver);
    }

    private By formToSendMessageLocator = By.cssSelector("form#get-input input");
    private By showMessageButtonLocator = By.cssSelector("form#get-input>button");
    private By receivedMessageLocator = By.cssSelector("div#user-message");
    private By formToSendALocator = By.cssSelector("#sum1");
    private By formToSendBLocator = By.cssSelector("#sum2");
    private By getTotalButtonLocator = By.cssSelector("#gettotal button");
    private By receivedSumLocator = By.cssSelector("#gettotal+div:not([class])");

    public SimpleFormDemo sendMessage(String message) {
        driver.findElement(formToSendMessageLocator).sendKeys(message);
        return new SimpleFormDemo(driver);
    }

    public SimpleFormDemo clickOnShowMessageButton() {
        driver.findElement(showMessageButtonLocator).click();
        return new SimpleFormDemo(driver);
    }

    public String showReceivedMessage() {
        return driver.findElement(receivedMessageLocator).getText();
    }

    public SimpleFormDemo sendValues(String a, String b) {
        driver.findElement(formToSendALocator).sendKeys(a);
        driver.findElement(formToSendBLocator).sendKeys(b);
        return new SimpleFormDemo(driver);
    }

    public SimpleFormDemo clickOnGetTotalButton() {
        driver.findElement(getTotalButtonLocator).click();
        return new SimpleFormDemo(driver);
    }

    public String showTotalSum() {
        return driver.findElement(receivedSumLocator).getText();
    }
}
