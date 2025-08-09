package WebDriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;

public class WebDriverFactory {
    public static WebDriver getDriver(String browserName) {
        if (browserName == null) {
            browserName = "chrome";
        }

        switch (browserName.toLowerCase()) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Olya\\Diplom\\Diplom_3\\src\\test\\java\\resources\\yandexdriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Users\\Olya\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox",  "--disable-dev-shm-usage");
                return new ChromeDriver(chromeOptions);
            default:
                return new ChromeDriver();
        }
    }
}
