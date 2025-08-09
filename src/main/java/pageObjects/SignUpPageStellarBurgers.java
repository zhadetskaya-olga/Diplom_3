package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPageStellarBurgers {
    private final WebDriver driver;

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    // Поле Имя
    private final By nameField = By.xpath(".//label[text()='Имя']/../input");
    // Поле Email
    private final By emailField = By.xpath(".//label[text()='Email']/../input");
    // Поле Пароль
    private final By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    // Кнопка Зарегистрироваться
    private final By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Сообщение об ошибке при вводе некорректного пароля
    private final By passwordError = By.xpath("//p[@class='input__error text_type_main-default']");
    //Ссылка Войти
    private final By signInLink = By.xpath("//a[@class='Auth_link__1fOlj']");


    public SignUpPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    //Открыть страницу регистрации
    public void openSignUpPage() {
        driver.get(URL);
    }

    //Ожидать загрузки страницы входа
    public void waitForSignUpPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(signUpButton));
    }

    //Ввести имя
    public void setName(String name) {
        driver.findElement(nameField).click();
        driver.findElement(nameField).sendKeys(name);
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

    //Нажать кнопку Зарегистрироваться
    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    //Завершить регистрацию
    public void signUp(String name, String email, String password) {
        openSignUpPage();
        waitForSignUpPageLoad();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickSignUpButton();
    }

    //Проверить видимость поп-апа с ошибкой "Некорректный пароль"
    public boolean isPasswordErrorVisible() {
        return driver.findElement(passwordError).isDisplayed();
    }

    public void clickSignInLink() {
        driver.findElement(signInLink).click();
    }



}
