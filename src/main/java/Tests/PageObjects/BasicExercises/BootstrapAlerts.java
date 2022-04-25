package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BootstrapAlerts extends BasePage {

    public BootstrapAlerts(WebDriver driver) {
        super(driver);
    }

    private WebDriverWait wait;

    public String boxAlertMessage;
    public String boxFontColor;
    public String boxBackgroundColor;
    public String boxBorderColor;

    @FindBy(css = "div[style=\"display: block;\"]>button.close")
    private WebElement alarmCloseButtonElement;

    private WebElement alarmBoxElement(boolean autocloseable, String alertCategory) {
        String alarmBoxSelector;
        if (autocloseable) alarmBoxSelector = "div.alert-autocloseable-<alertCategory>";
        else alarmBoxSelector = "div.alert-normal-<alertCategory>";
        return driver.findElement(By.cssSelector(alarmBoxSelector.replace("<alertCategory>", alertCategory)));
    }

    private WebElement alarmButtonElement(boolean autocloseable, String alertCategory) {
        String alarmButtonSelector;
        if (autocloseable) alarmButtonSelector = "button#autoclosable-btn-<alertCategory>";
        else alarmButtonSelector = "button#normal-btn-<alertCategory>";
        return driver.findElement(By.cssSelector(alarmButtonSelector.replace("<alertCategory>", alertCategory)));
    }

    public boolean closeAlarmBoxIsDisplayed() {
        return alarmCloseButtonElement.isDisplayed();
    }

    public boolean boxIsInvisibility(boolean autocloseable, String alertCategory, int displayedTime) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(displayedTime + 1));
        return wait.until(ExpectedConditions.invisibilityOf(alarmBoxElement(autocloseable, alertCategory)));
    }

    public boolean buttonIsEnabled(boolean autocloseable, String alertCategory) {
        return alarmButtonElement(autocloseable, alertCategory).isEnabled();
    }

    public void clickAndGetBoxParameters(boolean autocloseable, String alertCategory) {
        clickOnAlarmBox(autocloseable, alertCategory);
        WebElement alarmBoxElement = alarmBoxElement(autocloseable, alertCategory);

        boxAlertMessage = alarmBoxElement.getText();
        boxFontColor = alarmBoxElement.getCssValue("color");
        boxBackgroundColor = alarmBoxElement.getCssValue("background-color");
        boxBorderColor = alarmBoxElement.getCssValue("border-color");
    }

    private void clickOnAlarmBox(boolean autocloseable, String alertCategory) {
        alarmButtonElement(autocloseable, alertCategory).click();
    }


}
