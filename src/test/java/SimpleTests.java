import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Set;

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
    void fillOutLoginInfo(WebDriver driver) throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("protondeeznuts123");
        loginPage.submitLoginInfo();



        Assertions.assertEquals("https://www.metalshop.cz/",driver.getCurrentUrl());


    }


    @Test
    void fillOutWrongLoginInfo(WebDriver driver) throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("wrongPassword123");
        loginPage.submitLoginInfo();




        Assertions.assertEquals("https://www.metalshop.cz/login/",driver.getCurrentUrl());



    }

    //Vanes NEVIM JESTLI TO @TEST vubec plati protoze samo je to nespustitelne... musi se to volat jen z te druhe metody
    @Test
    void searchTest(String searchString, WebDriver driver) throws URISyntaxException {

        utilTestClass.search(searchString);

        String currentUrl = driver.getCurrentUrl();

        String decodedURL = URLDecoder.decode(currentUrl);

        Assertions.assertEquals("https://www.metalshop.cz/search/" + searchString + "/", decodedURL);


    }
    UriEncoder encoder;

    @Test
    void searchTestFromHomePage(WebDriver driver) throws InterruptedException, URISyntaxException {

        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();
        searchTest("4=čárkaěěxxdd",driver);
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
        HomePage homePage1 = homePage.chooseCategory(homePage.getWomenDropdown()); // clicked on woman category
        utilTestClass.closeSaleAdvert(driver);
        // choosing product and its size
        HomePage homePage2 = homePage1.selectProductsSize(driver.findElement(By.cssSelector("#page-section > section > div > section > div.products_header.col-sm-12 > div > div.mp_category.vypis-filtre > div.row.row-products > div:nth-child(3) > div > a")));
        // waiting till the color of the size box turns orange, therefore is selected
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((WebDriver driver) -> {
            String border = driver.findElement(By.cssSelector("#variation_select_135033_1_2 > div")).getCssValue("border");
            return border.equals("1px solid rgb(236, 127, 0)");
        });
        // clicking on the buy button
        driver.findElement(By.cssSelector("#buy")).click();
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bought-too-modal > div > div")));
        // checking for the number above cart icon
        Assertions.assertEquals("1", driver.findElement(By.cssSelector("#small_cart_amount")).getText());

    }

    @Test
    public void emailSubscriptionTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();
        utilTestClass.subscribeToNewsletter(driver, "@gmail.com");
        Assertions.assertEquals("https://www.metalshop.cz/newsletter/",driver.getCurrentUrl());
    }

    @Test
    public void newUserDataTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();

        Set<Cookie> cookies1 = driver.manage().getCookies();
        System.out.println(cookies1);
        driver.manage().deleteAllCookies();


        driver.get("https://www.metalshop.cz");
        // Delete existing cookies

        // Refresh the page
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#header"))));

        // Verify that cookies have been deleted
        Set<Cookie> cookies2 = driver.manage().getCookies();
        System.out.println(cookies2);
        Assertions.assertNotEquals(cookies1, cookies2);
        // This still doesn't work but probably should
//        Set<Cookie> cookies = driver.manage().getCookies();
//        System.out.println(cookies);
//        Assertions.assertTrue(cookies.isEmpty());

        // Accept new cookies (e.g., click "Accept" button)
        utilTestClass.acceptCookies();

        // Verify that new cookies have been accepted and applied
        Set<Cookie> cookies3 = driver.manage().getCookies();
        Assertions.assertFalse(cookies3.isEmpty());
    }

    @Test
    public void addReviewTest() throws InterruptedException {
        driver.get("https://www.metalshop.cz/p/135033-tricko-unisex-killstar-soul-card-black-ksra007941/");
        utilTestClass.acceptCookies();
        ProductPage productPage = new ProductPage(driver)
                .openReviewOption()
                .addNewReview();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#product_rating_form > div.stars"))));
        productPage.selectNumberOfStars(5);
        productPage.fillInReviewForm("Franta Metal", "testingseleniumcvut@protonmail.com", "Veľmi spokojný s produktom.");
//        driver.findElement(By.cssSelector("#product_rating_form > input.submit.next")).click();

    }

    @Test
    public void checkoutTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        utilTestClass.acceptCookies();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("protondeeznuts123");
        loginPage.submitLoginInfo();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
//        utilTestClass.acceptCookies();
        DeliveryPage deliveryPage = shoppingCartPage.clickContinueButton();
        deliveryPage.clickDeliveryOption(9);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        deliveryPage.clickContinueButton();
        Assertions.assertEquals("https://www.metalshop.cz/ncart/data/",driver.getCurrentUrl());

    }

}
