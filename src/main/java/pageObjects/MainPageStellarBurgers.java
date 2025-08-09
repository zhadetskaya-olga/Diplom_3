package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageStellarBurgers {

    private final WebDriver driver;
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    // Кнопка Войти в аккаунт
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Раздел "Булки"
    private final By bunsTab = By.xpath(".//span[text()='Булки']");
    // Раздел "Соусы"
    private final By saucesTab = By.xpath(".//span[text()='Соусы']");
    // Раздел "Начинки"
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']");
    // Кнопка Личный кабинет
    private final By personalAccountButton = By.xpath("/html/body/div/div/header/nav/a/p");
    // Заголовок Соберите бургер
    private final By burgerHeader = By.xpath(".//h1[text()='Соберите бургер']");
    // Активный раздел (чтобы проверять переключения)
    private final By activeTab = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]/span");


    public MainPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }


    //Открыть главную страницу
    public void openMainPage() {
        driver.get(URL);
    }

    //Подождать загрузки главной страницы
    public void waitForMainPageLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(burgerHeader));
    }

    //Проверить, что главная страница отобразилась
    public boolean isMainPageLoaded() {
        return driver.findElement(burgerHeader).isDisplayed();
    }

    //Клик по кнопке Войти в аккаунт
    public void clickLogInButton() {
        driver.findElement(loginButton).click();
    }

    //Клик по кнопке Личный кабинет
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }


    //Нажать на раздел Булки
    public void clickBunsSection() {
        driver.findElement(bunsTab).click();
    }

    //Нажать на раздел Соусы
    public void clickSaucesSection() {
        driver.findElement(saucesTab).click();
    }

    //Нажать на раздел Начинки
    public void clickFillingsSection() {
        driver.findElement(fillingsTab).click();
    }

    //Получить активный раздел
    public String getActiveSectionName() {
        return driver.findElement(activeTab).getText();
    }


}