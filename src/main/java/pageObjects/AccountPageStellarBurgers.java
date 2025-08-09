package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public class AccountPageStellarBurgers {
    private final WebDriver driver;

    // URL личного кабинета
    public static final String URL = "https://stellarburgers.nomoreparties.site/account";

    // Кнопка Выход
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    // Ссылка на Конструктор
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']");
    // Логотип Stellar Burgers
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    // Ссылка Профиль
    private final By accountTab = By.xpath("//a[@class='Account_link__2ETsJ text text_type_main-medium text_color_inactive Account_link_active__2opc9']");

    public AccountPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }
    //Открыть страницу Личный кабинет
    public void openAccountPage() {
        driver.get(URL);
    }

    //Ожидание загрузки страницы Личный кабинет
    public void waitForAccountPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(accountTab));
    }

    //Проверить, что страница Личный кабинет подгрузилась
    public boolean isProfilePageLoaded() {
        return driver.findElement(accountTab).isDisplayed();
    }

    //Нажать на кнопку Выход
    public void clickLogoutButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }


    //Нажать на ссылку Конструктор
    public void clickConstructorLink() {
        driver.findElement(constructorLink).click();
    }

    //Нажать на логотип Stellar Burgers
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}
