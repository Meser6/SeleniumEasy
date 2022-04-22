package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdownList extends BasePage {

    private Select select;
    private Actions actions;

    public SelectDropdownList(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "select.form-control")
    @CacheLookup
    private WebElement singleListElement;

    @FindBy(css = "p.selected-value")
    @CacheLookup
    private WebElement singleMessageElement;

    @FindBy(css = "button[value='Print First']")
    @CacheLookup
    private WebElement firstSelectedButtonElement;

    @FindBy(css = "button[value='Print All']")
    @CacheLookup
    private WebElement allSelectedButtonElement;

    @FindBy(css = "p.getall-selected")
    @CacheLookup
    private WebElement multiMessageElement;

    String multiSelectOptionSelector = "option[value='<selectedOption>']";

    By firstSelectedButtonSelector = By.cssSelector("button[value='Print First']");
    By allSelectedButtonSelector = By.cssSelector("button[value='Print All']");
    By receivedMessageSelector = By.cssSelector("p.getall-selected");

    public SelectDropdownList singleSelectDay(String day) {
        select = new Select(singleListElement);
        select.selectByVisibleText(day);
        return new SelectDropdownList(driver);
    }

    public String getSingleMessage() {
        return singleMessageElement.getText();
    }

    public SelectDropdownList selectTwoOptions(String firstOption, String secondOption) {
        WebElement firstSelectedOptionElement = driver.findElement(By.cssSelector(multiSelectOptionSelector.replaceAll("<selectedOption>", firstOption)));
        WebElement secondSelectedOptionElement = driver.findElement(By.cssSelector(multiSelectOptionSelector.replaceAll("<selectedOption>", secondOption)));
        actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).click(firstSelectedOptionElement).click(secondSelectedOptionElement).build().perform();
        return new SelectDropdownList(driver);
    }

    public String getFirstSelectedMessage() {
        firstSelectedButtonElement.click();
        return multiMessageElement.getText();
    }

    public String getAllSelectedMessage() {
        allSelectedButtonElement.click();
        return multiMessageElement.getText();
    }
}

