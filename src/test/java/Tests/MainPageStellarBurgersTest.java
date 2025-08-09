package Tests;


import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObjects.*;



public class MainPageStellarBurgersChromeTest {
    private WebDriver driver;
    private SignUpPageStellarBurgers signUpPage;
    private SignInPageStellarBurgers signInPage;
    private MainPageStellarBurgers mainPage;
    private AccountPageStellarBurgers accountPage;
    private String name = "Name";
    private String email = "test121email@gmail.com";
    private String password = "password123";


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        signInPage = new SignInPageStellarBurgers(driver);
        signUpPage = new SignUpPageStellarBurgers(driver);
        mainPage = new MainPageStellarBurgers(driver);
        accountPage = new AccountPageStellarBurgers(driver);


    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void shouldOpenPersonalAccountPageWhenClickCorrespondingButton(){
        signUpPage.signUp(name, email, password);
        signInPage.openSignInPage();
        signInPage.logIn(email, password);
        mainPage.waitForMainPageLoading();
        mainPage.clickPersonalAccountButton();
        accountPage.waitForAccountPageLoad();
        Assertions.assertTrue(accountPage.isProfilePageLoaded(), "Страница личного кабинета не открылась");
    }



    @Test
    @DisplayName("Переход в раздел Булки")
    public void shouldOpenBunsSectionWhenClickingOnCorrespondingTab() {
        mainPage.openMainPage();
        mainPage.waitForMainPageLoading();
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();
        Assertions.assertEquals("Булки", mainPage.getActiveSectionName(), "Раздел Булки не открылся");
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    public void shouldOpenSaucesSectionWhenClickingOnCorrespondingTab() {
        mainPage.openMainPage();
        mainPage.waitForMainPageLoading();
        mainPage.clickSaucesSection();
        Assertions.assertEquals("Соусы", mainPage.getActiveSectionName(), "Раздел Соусы не открылся");
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    public void shouldOpenFillingsSectionWhenClickingOnCorrespondingTab() {
        mainPage.openMainPage();
        mainPage.waitForMainPageLoading();
        mainPage.clickFillingsSection();
        Assertions.assertEquals("Начинки", mainPage.getActiveSectionName(), "Раздел Начинки не открылся");
    }

}
