package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckBoxDemo extends BasePage {

    public CheckBoxDemo(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input#isAgeSelected")
    @CacheLookup
    private WebElement checkboxInputElement;

    @FindBy(css = "div#txtAge")
    @CacheLookup
    private WebElement receivedMessageElement;

    @FindBy(css = "input.cb1-element")
    @CacheLookup
    private List<WebElement> allCheckboxListElement;

    @FindBy(css = "#check1")
    @CacheLookup
    private WebElement checkAllButtonElement;


    public CheckBoxDemo checkSingleCheckbox() {
        checkboxInputElement.click();
        return new CheckBoxDemo(driver);
    }

    public String getMessage() {
        return receivedMessageElement.getText();
    }

    public CheckBoxDemo checkAllCheckbox() {
        checkAllButtonElement.click();
        return new CheckBoxDemo(driver);
    }

    public String getButtonText() {
        return checkAllButtonElement.getAttribute("value");
    }

    public CheckBoxDemo clickOption(int optionNumber) {
        allCheckboxListElement.get(optionNumber).click();
        return new CheckBoxDemo(driver);
    }

    public int getAmountOfCheckedOption() {
        int checkedCheckbox = 0;
        for (WebElement e : allCheckboxListElement) {
            if (e.isSelected()) {
                checkedCheckbox++;
            }
        }
        return checkedCheckbox;
    }
}
