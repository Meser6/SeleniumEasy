package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBoxDemo extends BasePage {

    public CheckBoxDemo(WebDriver driver) {
        super(driver);
    }

    By checkboxInputLocator = By.cssSelector("input#isAgeSelected");
    By receivedMessageLocator = By.cssSelector("div#txtAge");
    By allCheckboxLocators = By.cssSelector("input.cb1-element");
    By checkAllButtonLocator = By.cssSelector("#check1");

    public CheckBoxDemo checkSingleCheckbox() {
        driver.findElement(checkboxInputLocator).click();
        return new CheckBoxDemo(driver);
    }

    public String getMessage() {
        return driver.findElement(receivedMessageLocator).getText();
    }

    public CheckBoxDemo checkAllCheckbox() {
        driver.findElement(checkAllButtonLocator).click();
        return new CheckBoxDemo(driver);
    }

    public String getButtonText() {
        return driver.findElement(checkAllButtonLocator).getAttribute("value");
    }

    public CheckBoxDemo clickOption(int optionNumber) {
        driver.findElements(allCheckboxLocators).get(optionNumber).click();
        return new CheckBoxDemo(driver);
    }

    public int getAmountOfCheckedOption() {
        int checkedCheckbox = 0;
        List<WebElement> checkboxList = driver.findElements(allCheckboxLocators);
        for (WebElement e : checkboxList) {
            if (e.isSelected()) {
                checkedCheckbox++;
            }
        }
        return checkedCheckbox;
    }
}
