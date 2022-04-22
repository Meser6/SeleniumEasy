package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class RadioButtonsDemo extends BasePage {

    public RadioButtonsDemo(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "button#buttoncheck")
    @CacheLookup
    private WebElement getCheckedValueButtonElement;

    @FindBy(css = "p.radiobutton")
    @CacheLookup
    private WebElement radioReceivedMessageElement;

    @FindBy(css = "div.panel-body>button")
    @CacheLookup
    private WebElement getValuesButtonElement;

    @FindBy(css = "p.groupradiobutton")
    @CacheLookup
    private WebElement groupRadioReceivedMessageElement;

    private String radioGenderSelector = "[name='optradio'][value='<gender>']";
    private String groupRadioGenderSelector = "[name='gender'][value='<gender>']";
    private String groupRadioAgeSelector = "[name='ageGroup'][value='<age>']";

    public RadioButtonsDemo radioSelectGender(String gender) {
        driver.findElement(By.cssSelector(radioGenderSelector.replace("<gender>", gender))).click();
        return new RadioButtonsDemo(driver);
    }

    public RadioButtonsDemo getCheckedValue() {
        getCheckedValueButtonElement.click();
        return new RadioButtonsDemo(driver);
    }

    public String getRadioMessage() {
        return radioReceivedMessageElement.getText();
    }

    public RadioButtonsDemo groupRadioSelectGender(String gender) {
        driver.findElement(By.cssSelector(groupRadioGenderSelector.replace("<gender>", gender))).click();
        return new RadioButtonsDemo(driver);
    }

    public RadioButtonsDemo groupRadioSelectAge(String age) {
        driver.findElement(By.cssSelector(groupRadioAgeSelector.replace("<age>", age))).click();
        return new RadioButtonsDemo(driver);
    }

    public RadioButtonsDemo getValues() {
        getValuesButtonElement.click();
        return new RadioButtonsDemo(driver);
    }

    public String getGroupRadioMessage() {
        return groupRadioReceivedMessageElement.getText();
    }

}
