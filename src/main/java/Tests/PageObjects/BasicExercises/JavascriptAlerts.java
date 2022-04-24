package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class JavascriptAlerts extends BasePage {


    public JavascriptAlerts(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[onclick=\"myAlertFunction()\"]")
    @CacheLookup
    private WebElement initialAlertBoxButtonElement;

    @FindBy(css = "button[onclick=\"myConfirmFunction()\"]")
    @CacheLookup
    private WebElement initialConfirmBoxButtonElement;

    @FindBy(css = "button[onclick=\"myPromptFunction()\"]")
    @CacheLookup
    private WebElement initialPromptBoxButtonElement;

    @FindBy(css = "p#confirm-demo")
    @CacheLookup
    private WebElement confirmReceivedMessageElement;

    @FindBy(css = "p#prompt-demo")
    @CacheLookup
    private WebElement promptReceivedMessageElement;


    public JavascriptAlerts initAlertBox() {
        initialAlertBoxButtonElement.click();
        return new JavascriptAlerts(driver);
    }

    public JavascriptAlerts initConfirmBox() {
        initialConfirmBoxButtonElement.click();
        return new JavascriptAlerts(driver);
    }

    public JavascriptAlerts initPromptBox() {
        initialPromptBoxButtonElement.click();
        return new JavascriptAlerts(driver);
    }

    public String getAlertText() {
        return switchToAlert().getText();
    }

    public JavascriptAlerts acceptOrDismiss(String alert) {
        if (alert.equals("OK")) switchToAlert().accept();
        else switchToAlert().dismiss();
        return new JavascriptAlerts(driver);
    }

    public JavascriptAlerts sendKeysToAlert(String messageToSend) {
        switchToAlert().sendKeys(messageToSend);
        return new JavascriptAlerts(driver);
    }

    public String getConfirmReceivedMessage() {
        return confirmReceivedMessageElement.getText();
    }

    public String getPromptReceivedMessage() {
        return promptReceivedMessageElement.getText();
    }

    private Alert switchToAlert() {
        Alert alert = driver.switchTo().alert();
        return alert;
    }
}
