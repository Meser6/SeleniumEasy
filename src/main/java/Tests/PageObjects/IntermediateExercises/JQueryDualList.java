package Tests.PageObjects.IntermediateExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class JQueryDualList extends BasePage {

    public JQueryDualList(WebDriver driver) {
        super(driver);
    }

    private Select select;

    @FindBy(className = "pickData")
    private WebElement leftListElement;

    @FindBy(className = "pickListResult")
    private WebElement rightListElement;

    @FindBy(className = "pAdd")
    @CacheLookup
    private WebElement addButtonElement;

    @FindBy(className = "pAddAll")
    @CacheLookup
    private WebElement addAllButtonElement;

    @FindBy(className = "pRemove")
    @CacheLookup
    private WebElement removeButtonElement;

    @FindBy(className = "pRemoveAll")
    @CacheLookup
    private WebElement removeAllButtonElement;


    public JQueryDualList clickAddButton() {
        addButtonElement.click();
        return new JQueryDualList(driver);
    }

    public JQueryDualList clickAddAllButton() {
        addAllButtonElement.click();
        return new JQueryDualList(driver);
    }

    public JQueryDualList clickRemoveButton() {
        removeButtonElement.click();
        return new JQueryDualList(driver);
    }

    public JQueryDualList clickRemoveAllButton() {
        removeAllButtonElement.click();
        return new JQueryDualList(driver);
    }

    public JQueryDualList selectOptionOnLeftList(String option) {
        switchToLeftList();
        select.selectByVisibleText(option);
        return new JQueryDualList(driver);
    }

    public JQueryDualList selectOptionOnRightList(String option) {
        switchToRightList();
        select.selectByVisibleText(option);
        return new JQueryDualList(driver);
    }

    public boolean bothListAreMultiple() {
        int multiple = 0;
        switchToLeftList();
        if (select.isMultiple()) {
            multiple++;
        }
        switchToRightList();
        if (select.isMultiple()) {
            multiple++;
        }
        return multiple == 2;
    }

    public int getAmountOfOptionOnLeftList() {
        switchToLeftList();
        List<WebElement> amount = select.getOptions();
        return amount.size();
    }

    public int getAmountOfOptionOnRightList() {
        switchToRightList();
        List<WebElement> amount = select.getOptions();
        return amount.size();
    }

    private void switchToLeftList() {
        select = new Select(leftListElement);
    }

    private void switchToRightList() {
        select = new Select(rightListElement);
    }


}
