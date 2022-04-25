package Tests.PageObjects.BasicExercises;

import Tests.PageObjects.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BootstrapModals extends BasePage {

    public BootstrapModals(WebDriver driver) {
        super(driver);
    }

    private WebDriverWait wait;

    @FindBy(css = "a[href='#myModal0']")
    @CacheLookup
    private WebElement lunchSingleModalElement;

    @FindBy(css = "a[onclick='history.go(0)']")
    private List<WebElement> saveChangeButtonsElements;

    @FindBy(css = "a[href='#myModal']")
    private WebElement lunchMultiModalElement;

    @FindBy(css = "a[href='#myModal2']")
    private WebElement lunchSecondModalElement;

    public BootstrapModals lunchSingleModal() {
        lunchSingleModalElement.click();
        return new BootstrapModals(driver);
    }

    public BootstrapModals lunchMultiModal() {
        lunchMultiModalElement.click();
        return new BootstrapModals(driver);
    }

    public BootstrapModals lunchSecondModal() {
        wait = new WebDriverWait(driver, Duration.ofMillis(1500));
        wait.until(ExpectedConditions.elementToBeClickable(lunchSecondModalElement));
        lunchSecondModalElement.click();
        return new BootstrapModals(driver);
    }

    public BootstrapModals saveChanges(int buttonIndex) {
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(saveChangeButtonsElements.get(buttonIndex)));
        if (saveChangeButtonsElements.size() == 0) {
            throw new NoSuchElementException("Any modal did no lunch");
        }
        saveChangeButtonsElements.get(buttonIndex).click();
        return new BootstrapModals(driver);
    }

    public void checkRefreshing() {
        final long startTime = System.currentTimeMillis();
        final long maxLoadTime = TimeUnit.MILLISECONDS.toMillis(50);
        boolean startedReloading;
        do {
            try {
                startedReloading = !lunchSingleModalElement.isDisplayed();
            } catch (NoSuchElementException ex) {
                startedReloading = true;
            }
        } while (!startedReloading && (System.currentTimeMillis() - startTime < maxLoadTime));

        if (!startedReloading) {
            throw new IllegalStateException("Page did not start reloading in " + maxLoadTime + "ms");
        }
    }
}
