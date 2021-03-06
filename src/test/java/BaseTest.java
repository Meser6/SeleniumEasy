import Tests.Helpers.ScreenshotHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class BaseTest {

    WebDriver driver;

    @RegisterExtension
    ScreenshotHelper status = new ScreenshotHelper();


    @BeforeEach
    void testsSetUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @AfterEach
    void closeProcess(TestInfo info) throws IOException, URISyntaxException {
        if (status.isFailed) {
            System.out.println("Test screenshot is available at: " + takeScreenshot(info));
        }
        driver.quit();
    }

    private String takeScreenshot(TestInfo info) throws IOException, URISyntaxException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH-mm-ss");
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\ScreenshotsFromFailedTests\\"
                + info.getDisplayName() + " " + formatter.format(timeNow) + ".jpg";
        FileHandler.copy(screenshot, new File(path));
        return path;
    }
}
