package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPageStellarBurgers {
    private final WebDriver driver;

    public static final String URL = "https://stellarburgers.nomoreparties.site/login";
    // Заголовок формы входа
    private final By header = By.xpath(".//h2[text()='Вход']");
    // Поле Email
    private final By emailField = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    // Поле Пароль
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    // Кнопка Войти
    private final By logInButton = By.xpath("/html/body/div/div/main/div/form/button");
    //Кнопка оформить заказ
    private final By createOrderButton =  By.xpath("/html/body/div/div/main/section[2]/div/button");



    public SignInPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForSignInPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logInButton));
    }

    //переход на страницу регистрации
    public void openSignInPage() {
        driver.get(URL);
    }

    //Проверить, что страница входа загрузилась
    public boolean isLogInPageLoaded() {
        return driver.findElement(logInButton).isDisplayed();
    }

    //Ввести email
    public void setEmail(String email) {
        driver.findElement(emailField).click();
        driver.findElement(emailField).sendKeys(email);
    }

    //Ввести пароль
    public void setPassword(String password) {
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).sendKeys(password);
    }

    //Нажать кнопку Войти
    public void clickLoginButton() {
        driver.findElement(logInButton).click();
    }


    //Выполнить вход в аккаунт
    public void logIn(String email, String password) {
        waitForSignInPageLoad();
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
    public boolean isCreateOrderButtonVisible() {
        return driver.findElement(createOrderButton).isDisplayed();
    }
}
