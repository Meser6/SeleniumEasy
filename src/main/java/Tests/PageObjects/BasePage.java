package Tests.PageObjects;

import Tests.Helpers.ExercisesDifficulty;
import Tests.PageObjects.BasicExercises.SimpleFormDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    private By closePopupLocator = By.cssSelector("a.at-cm-no-button");
    private By difficultiesLocator = By.cssSelector("li.tab-toggle a");
    private By actualExercisesListLocator = By.cssSelector("div[class=\"tab-pane fade active in\"] div.list-group");
    private By exercisesLocator = By.cssSelector("div.active div.list-group>a");


    private String mainSite = "https://demo.seleniumeasy.com/";

    public BasePage goToMainSite() {
        driver.navigate().to(mainSite);
        return new BasePage(driver);
    }

    public BasePage closeAdd() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(closePopupLocator)).click();
        return new BasePage(driver);
    }

    public BasePage choseCategory(ExercisesDifficulty difficulty) {
        List<WebElement> difficultyCategoriesList = driver.findElements(difficultiesLocator);

        switch (difficulty) {
            case BASIC -> difficultyCategoriesList.get(0).click();
            case INTERMEDIATE -> difficultyCategoriesList.get(1).click();
            case ADVANCED -> difficultyCategoriesList.get(2).click();
        }
        return new BasePage(driver);
    }

    public void choseExercises(int exercisesIndex) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.presenceOfElementLocated(actualExercisesListLocator));

        List<WebElement> exercisesList = driver.findElements(exercisesLocator);
        exercisesList.get(exercisesIndex).click();
    }
}
