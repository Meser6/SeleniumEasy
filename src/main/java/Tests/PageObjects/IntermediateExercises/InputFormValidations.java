package Tests.PageObjects.IntermediateExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class InputFormValidations extends BasePage {

    private static int formNumber;

    private Select select;

    public InputFormValidations(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".form-control")
    @CacheLookup
    private List<WebElement> formsElements;

    @FindBy(css = "div.has-error .help-block[style=\"\"]")
    @CacheLookup
    private List<WebElement> basicErrorMessageElement;

    @FindBy(css = "div.has-error .help-block[style=\"display: block;\"]")
    @CacheLookup
    private List<WebElement> alternativeErrorMessageElement;

    @FindBy(css = ".form-group>.control-label")
    @CacheLookup
    private List<WebElement> formsNamesElements;

    @FindBy(css = "select[name='state']")
    @CacheLookup
    private WebElement stateListElement;

    @FindBy(css = "button[type='submit'].btn")
    @CacheLookup
    private WebElement sendButtonElement;

    private String choseHostingSelector = "input[value='<yesOrNo>']";

    public InputFormValidations chooseForm(int formNumber) {
        this.formNumber = formNumber;
        return new InputFormValidations(driver);
    }

    public InputFormValidations sendTextToValue(String text) {
        formsElements.get(formNumber).sendKeys(text);
        return new InputFormValidations(driver);
    }

    public InputFormValidations sendEmptyText() {
        formsElements.get(formNumber).sendKeys("N", Keys.BACK_SPACE);
        return new InputFormValidations(driver);
    }

    public InputFormValidations selectState(String state) {
        select = new Select(stateListElement);
        select.selectByVisibleText(state);
        return new InputFormValidations(driver);
    }

    public InputFormValidations selectHosting(String yesOrNo) {
        driver.findElement(By.cssSelector(choseHostingSelector.replace("<yesOrNo>", yesOrNo)));
        return new InputFormValidations(driver);
    }

    public InputFormValidations sendForm() {
        sendButtonElement.click();
        return new InputFormValidations(driver);
    }

    public boolean sendButtonIsDisabled() {
        return sendButtonElement.isEnabled();
    }

    public String getErrorMessage() {
        return errorMessageElement().getText();
    }

    public String getFormNameColor() {
        if (formNumber == 9) formNumber += 1;
        return formsNamesElements.get(formNumber).getCssValue("color");
    }

    public String getFormNameColor(int formNumber) {
        return formsNamesElements.get(formNumber).getCssValue("color");
    }

    public String getErrorMessageColor() {
        return errorMessageElement().getCssValue("color");
    }

    private WebElement errorMessageElement() {
        if (basicErrorMessageElement.size() > 0) {
            return basicErrorMessageElement.get(0);
        } else {
            return alternativeErrorMessageElement.get(0);
        }
    }
}
