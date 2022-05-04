package Tests.PageObjects.IntermediateExercises;

import Tests.Helpers.BootstrapLists;
import Tests.PageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BootstrapListBox extends BasePage {

    public BootstrapListBox(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".list-left li:not([style])")
    private List<WebElement> leftListElements;

    @FindBy(css = ".list-right li:not([style])")
    private List<WebElement> rightListElements;

    @FindBy(css = ".move-left")
    @CacheLookup
    private WebElement switchToLeftButtonElements;

    @FindBy(css = ".move-right")
    @CacheLookup
    private WebElement switchToRightButtonElements;

    @FindBy(css = ".list-left [title='select all']")
    @CacheLookup
    private WebElement selectAllLeftButtonElement;

    @FindBy(css = ".list-right [title='select all']")
    @CacheLookup
    private WebElement selectAllRightButtonElement;

    @FindBy(css = ".list-left [name='SearchDualList']")
    @CacheLookup
    private WebElement searchFormLeftButtonElement;

    @FindBy(css = ".list-right [name='SearchDualList']")
    @CacheLookup
    private WebElement searchFormRightButtonElement;

    @FindBy(className = "active")
    private List<WebElement> selectedElements;

    public int getAmountOfElements(BootstrapLists list) {
        int amount = 0;
        switch (list) {
            case LEFT -> amount = leftListElements.size();
            case RIGHT -> amount = rightListElements.size();
        }
        return amount;
    }

    public int getAmountOfSelectedElements() {
        return selectedElements.size();
    }

    public BootstrapListBox selectElement(BootstrapLists list, int elementNumber) {
        switch (list) {
            case LEFT -> leftListElements.get(elementNumber).click();
            case RIGHT -> rightListElements.get(elementNumber).click();
        }
        return new BootstrapListBox(driver);
    }

    public BootstrapListBox switchFromList(BootstrapLists list) {
        switch (list) {
            case LEFT -> switchToRightButtonElements.click();
            case RIGHT -> switchToLeftButtonElements.click();
        }
        return new BootstrapListBox(driver);
    }

    public BootstrapListBox selectAll(BootstrapLists list) {
        switch (list) {
            case LEFT -> selectAllLeftButtonElement.click();
            case RIGHT -> selectAllRightButtonElement.click();
        }
        return new BootstrapListBox(driver);
    }

    public BootstrapListBox findElement(BootstrapLists list, String textToSearch) {
        switch (list) {
            case LEFT -> searchFormLeftButtonElement.sendKeys(textToSearch);
            case RIGHT -> searchFormRightButtonElement.sendKeys(textToSearch);
        }
        return new BootstrapListBox(driver);
    }


}
