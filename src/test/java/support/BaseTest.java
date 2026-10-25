
package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("yandex")) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary(
                    "/Applications/Yandex.app/Contents/MacOS/Yandex"
            );

            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(
                            new File("/Users/valeramikhnenko/Downloads/yandexdriver")
                    )
                    .build();

            driver = new ChromeDriver(service, options);

        } else {
            throw new IllegalArgumentException(
                    "Неизвестный браузер: " + browser
            );
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

