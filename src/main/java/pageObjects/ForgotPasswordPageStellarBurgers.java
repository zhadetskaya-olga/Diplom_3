package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPageStellarBurgers {
    private final WebDriver driver;

    private final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    //ссылка Войти
    private final By loginLink = By.linkText("Войти");

    public ForgotPasswordPageStellarBurgers(WebDriver driver) {
        this.driver = driver;
    }

    //Открыть страницу восстановления пароля
    public void openForgotPasswordPage() {
        driver.get(URL);
    }

    //Нажать на ссылку Войти на странице Восстановления пароля
    public void clickLogInLink() {
        driver.findElement(loginLink).click();
    }
}
