package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

public class WindowPopupModal extends BasePage {

    public WindowPopupModal(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div:not([class])>h5+a")
    @CacheLookup
    private List<WebElement> buttonsToSingleSocialMediaElements;

    @FindBy(css = "div[class$='windows']>h5+a")
    @CacheLookup
    private List<WebElement> buttonsToMultiSocialMediaElements;

    public WindowPopupModal clickSingleWindowPopupButton(String button) {
        if (button.equals("twitter")) {
            buttonsToSingleSocialMediaElements.get(0).click();
        } else buttonsToSingleSocialMediaElements.get(1).click();
        return new WindowPopupModal(driver);
    }

    public WindowPopupModal clickMultiWindowPopupButton(int pageAmount) {
        if (pageAmount == 3) {
            buttonsToMultiSocialMediaElements.get(0).click();
        } else buttonsToMultiSocialMediaElements.get(1).click();
        return new WindowPopupModal(driver);
    }

    public int getPageAmount() {
        Set<String> cards = driver.getWindowHandles();
        return cards.size();
    }

    public String getSecondCardURL() {
        switchToSecondCard();
        return driver.getCurrentUrl();
    }

    private void switchToSecondCard() {
        Set<String> cards = driver.getWindowHandles();
        String actualCard = driver.getWindowHandle();
        cards.remove(actualCard);
        String newCard = cards.iterator().next();
        driver.switchTo().window(newCard);
    }

}
