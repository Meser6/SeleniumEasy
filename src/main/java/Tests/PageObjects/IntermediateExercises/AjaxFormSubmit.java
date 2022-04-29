package Tests.PageObjects.IntermediateExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AjaxFormSubmit extends BasePage {

    WebDriverWait wait;

    public AjaxFormSubmit(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "title")
    @CacheLookup
    private WebElement nameFormElement;

    @FindBy(id = "description")
    @CacheLookup
    private WebElement descriptionFormElement;

    @FindBy(id = "btn-submit")
    @CacheLookup
    private WebElement submitButtonElement;

    @FindBy(css = "img[scr='LoaderIcon.gif']")
    private WebElement loaderAnimationElement;

    @FindBy(css = "div#submit-control")
    @CacheLookup
    private WebElement successMessageElement;

    public AjaxFormSubmit sendTextToNameForm(String text) {
        nameFormElement.sendKeys(text);
        return new AjaxFormSubmit(driver);
    }

    public AjaxFormSubmit sendTextToDescriptionForm(String text) {
        descriptionFormElement.sendKeys(text);
        return new AjaxFormSubmit(driver);
    }

    public AjaxFormSubmit submitForm() {
        submitButtonElement.click();
        return new AjaxFormSubmit(driver);
    }

    public WebElement getSubmitButtonElement() {
        return submitButtonElement;
    }

    public String getMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(2), Duration.ofMillis(100));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(successMessageElement, "Ajax Request is Processing!")));
        return successMessageElement.getText();
    }


    public String getNameFormBorderColor() { // rgb(255, 0, 0) / 1px solid rgb(255, 0, 0)
        return nameFormElement.getAttribute("style");
    }

}
