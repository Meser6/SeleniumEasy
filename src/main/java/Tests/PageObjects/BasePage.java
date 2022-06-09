package Tests.PageObjects;

import Tests.Helpers.ExercisesDifficulty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a.at-cm-no-button")
    @CacheLookup
    private WebElement closePopupElement;

    @FindBy(css = "li.tab-toggle a")
    @CacheLookup
    private List<WebElement> difficultiesElements;

    @FindBy(css = "div[class='tab-pane fade active in'] div.list-group")
    @CacheLookup
    private WebElement actualExercisesListElement;

    @FindBy(css = "div.active div.list-group>a")
    @CacheLookup
    private List<WebElement> exercisesListElements;

    private final String mainSite = "https://demo.seleniumeasy.com/";

    private BasePage goToMainSite() {
        driver.navigate().to(mainSite);
        return new BasePage(driver);
    }

    private BasePage closeAdd() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(closePopupElement)).click();
        return new BasePage(driver);
    }

    private BasePage choseCategory(ExercisesDifficulty difficulty) {
        switch (difficulty) {
            case BASIC -> difficultiesElements.get(0).click();
            case INTERMEDIATE -> difficultiesElements.get(1).click();
            case ADVANCED -> difficultiesElements.get(2).click();
        }
        return new BasePage(driver);
    }

    private void choseExercises(int exercisesIndex) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOf(actualExercisesListElement));
        exercisesListElements.get(exercisesIndex).click();
    }

    public void goToExercise(ExercisesDifficulty category, int exercisesIndex){
        goToMainSite().closeAdd().choseCategory(category).choseExercises(exercisesIndex);
    }
}
