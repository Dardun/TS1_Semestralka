import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.time.Duration;

import java.net.URISyntaxException;
import java.net.URLDecoder;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;

public class SimpleTests {

    WebDriver driver;

    UtilTestClass utilTestClass = new UtilTestClass();

    @BeforeAll
    static void setupAll(){
         WebDriverManager.chromedriver();

     }

     @BeforeEach
    void setup(){
         driver = new ChromeDriver();
         WebDriverRunner.setWebDriver(driver);
     }









     // DO COOKIES FIRST
    @Test
    public void getToLoginPage() throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();
        LoginPage loginPage = utilTestClass.clickLoginOption(driver);


    }







    @Test
    void fillOutLoginInfo() throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        utilTestClass.acceptCookies();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("protondeeznuts123");
        loginPage.submitLoginInfo();



        Assertions.assertEquals("https://www.metalshop.cz/",driver.getCurrentUrl());


    }


    @Test
    void fillOutWrongLoginInfo() throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        utilTestClass.acceptCookies();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("wrongPassword123");
        loginPage.submitLoginInfo();




        Assertions.assertEquals("https://www.metalshop.cz/login/",driver.getCurrentUrl());



    }
    @Test
    void search(String searchString) throws URISyntaxException {

        $ (By.cssSelector("#fulltextvalue")).sendKeys(searchString);

        $ (By.cssSelector("#search_send")).click();

        String currentUrl = driver.getCurrentUrl();

        String decodedURL = URLDecoder.decode(currentUrl);




        Assertions.assertEquals("https://www.metalshop.cz/search/" + searchString + "/", decodedURL);


    }
    UriEncoder encoder;

    @Test
    void searchTestFromHomePage() throws InterruptedException, URISyntaxException {

        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();
        search("4=čárkaěěxxdd");
        driver.close();
        driver.quit();
    }





//    CREATING FAKE ACCOUNTS ILLEGAL >:C also i broke it slighlty by introducing utilClass
//    @Test
//    public void registrationTest() {
//        RegistrationPage registrationPage = new HomePage(driver)
//                .openShop()
//                .clickLoginOption()
//                .registrationButtonClick()
//                .fillRegistrationForm("Lucifer", "Morningstar",
//                "lmorningstar@gmail.com", "+420601203369",
//                "Satanove Hole, 666/66", "Brno", "GodIsWatching666",
//                "GodIsWatching666");
//
//        Assertions.assertEquals("Potvrzeno", registrationPage.getPageTitle().getText());
//    }


    @Test
    public void logOutTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        utilTestClass.acceptCookies();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("protondeeznuts123");
        loginPage.submitLoginInfo(); // firstly, logging into the account

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#alert_success > strong")))); // waiting for elements to load

        WebElement accountButton = driver.findElement(By.cssSelector("#head_nav > ul > li:nth-child(3) > a > div.name"));
        accountButton.click(); // clicking on the user account

        WebElement logOutButton = driver.findElement(By.cssSelector("#page-account > section > div > section > div.panel-heading > ul > li.logout > a"));
        logOutButton.click(); // logging out

        Assertions.assertEquals("Potvrzeno", driver.findElement(By.cssSelector("#alert_success > strong")).getText());
    }

    @Test
    //TODO port to util class, rename methods it uses
    public void addProductToCartTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();
        HomePage homePage1 = homePage.chooseCategory(homePage.getWomenDropdown());
        utilTestClass.closeSaleAdvert(driver);
        HomePage homePage2 = homePage1.selectProductsSize(driver.findElement(By.cssSelector("#page-section > section > div > section > div.products_header.col-sm-12 > div > div.mp_category.vypis-filtre > div.row.row-products > div:nth-child(3) > div > a")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until((WebDriver driver) -> {
            String border = driver.findElement(By.cssSelector("#variation_select_135033_1_2 > div")).getCssValue("border");
            return border.equals("1px solid rgb(236, 127, 0)");
        });
        driver.findElement(By.cssSelector("#buy")).click();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bought-too-modal > div > div")));
        Assertions.assertEquals("1", driver.findElement(By.cssSelector("#small_cart_amount")).getText());

    }

}
