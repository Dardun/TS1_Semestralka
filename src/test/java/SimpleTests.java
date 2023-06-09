import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.conditions.Interactable;
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









//NOT A TEST
    void fillOutWrongLoginInfo(WebDriver driver) throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("wrongPassword123");
        loginPage.submitLoginInfo();




        Assertions.assertEquals("https://www.metalshop.cz/login/",driver.getCurrentUrl());



    }

    void searchTest(String searchString, WebDriver driver) throws URISyntaxException {

        utilTestClass.search(searchString, driver);

        String currentUrl = driver.getCurrentUrl();

        String decodedURL = URLDecoder.decode(currentUrl);

        Assertions.assertEquals("https://www.metalshop.cz/search/" + searchString + "/", decodedURL);


    }
    UriEncoder encoder;



    //UNUSED
//    void searchTestFromHomePage(WebDriver driver) throws InterruptedException, URISyntaxException {
//
//        HomePage homePage = new HomePage(driver);
//        utilTestClass.acceptCookies();
//        searchTest("4=čárkaěěxxdd",driver);
//    }





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
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo(); // firstly, logging into the account

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf($("#alert_success > strong"))); // waiting for elements to load

        WebElement accountButton = driver.findElement(By.cssSelector("#head_nav > ul > li:nth-child(3) > a > div.name"));
        accountButton.click(); // clicking on the user account

        WebElement logOutButton = driver.findElement(By.cssSelector("#page-account > section > div > section > div.panel-heading > ul > li.logout > a"));
        logOutButton.click(); // logging out

        Assertions.assertEquals("Potvrzeno", driver.findElement(By.cssSelector("#alert_success > strong")).getText());
    }


    @Test
    public void emailSubscriptionTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();
        utilTestClass.subscribeToNewsletter(driver, "test@asdf.com");
        Assertions.assertEquals("https://www.metalshop.cz/newsletter/",driver.getCurrentUrl());
    }

    @Test
    public void newUserDataTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();

        Set<Cookie> cookies1 = driver.manage().getCookies();
        System.out.println(cookies1);
        driver.manage().deleteAllCookies();


        // Delete existing cookies

        Assertions.assertTrue(driver.manage().getCookies().isEmpty());
        // Refresh the page
        driver.navigate().refresh();


        Assertions.assertTrue(driver.manage().getCookies().isEmpty());
        // Verify that cookies have been deleted
        Set<Cookie> cookies2 = driver.manage().getCookies();
        System.out.println(cookies2);
        Assertions.assertNotEquals(cookies1, cookies2);
        // This still doesn't work but probably should

        Set<Cookie> cookies = driver.manage().getCookies();


        System.out.println(cookies);
        Assertions.assertTrue(cookies.isEmpty());

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
    public void checkoutTest() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        utilTestClass.acceptCookies();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo();


        SearchResultPage resultPage = utilTestClass.search("Shadow",driver);

        ProductPage productPage = resultPage.findAndClickProduct(4,driver);

        productPage.selectVariationViaIndex(0);
        productPage.selectVariationViaIndex(1);
        productPage.selectVariationViaIndex(0);
        productPage.addProductToCart();

        productPage.clickCloseAddedToCartPopup();

        ShoppingCartPage shoppingCartPage = utilTestClass.clickShoppingCartOption(driver);

        DeliveryPage deliveryPage = shoppingCartPage.clickContinueButton();


        deliveryPage.clickDeliveryOption(9);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        deliveryPage.clickContinueButton();
        Assertions.assertEquals("https://www.metalshop.cz/ncart/data/",driver.getCurrentUrl());

        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.deleteAllFromShoppingCart();
    }

    @Test
    public void inputTestSearch() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();



        utilTestClass.search("",driver);
        utilTestClass.search("a",driver);
        utilTestClass.search("ab",driver);
        utilTestClass.search("metallica",driver);
        utilTestClass.search("@#$&",driver);
        utilTestClass.search(" ",driver);
        utilTestClass.search("české znaky",driver);
        utilTestClass.search("english symbols",driver);
        utilTestClass.search("日本語",driver);

        //partial
        utilTestClass.search("infant annihil",driver);

        //full match
        utilTestClass.search("infant annihilator",driver);


        //bad request showcase

        utilTestClass.search("%",driver);


    }
    @Test
    public void inputTestPriceRange() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        utilTestClass.acceptCookies();
        SearchResultPage searchResultPage = utilTestClass.search("Test",driver);

        searchResultPage.clickPriceOption();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        boolean tempBool = false;
        while(tempBool==false){

            try{

                //normal values
                searchResultPage.setPriceRangeMax(200);

                Thread.sleep(2000);

                searchResultPage.setPriceRangeMin(100);
                Thread.sleep(2000);

                tempBool=true;

            }
            catch (ElementNotInteractableException e){
                utilTestClass.closeSaleAdvert(driver);
                js.executeScript("window.scrollBy(0, -250)");

            }

        }

        tempBool=false;

        while(tempBool==false){

            try{

                //MinLarger
                searchResultPage.setPriceRangeMax(200);

                Thread.sleep(2000);

                searchResultPage.setPriceRangeMin(1000);
                Thread.sleep(2000);

                tempBool=true;

            }
            catch (ElementNotInteractableException e){
                utilTestClass.closeSaleAdvert(driver);
                js.executeScript("window.scrollBy(0, -250)");

            }

        }
        tempBool=false;


        while(tempBool==false){

            try{

                //normal values
                searchResultPage.setPriceRangeMin(999999998);

                Thread.sleep(2000);

                searchResultPage.setPriceRangeMax(999999999);
                Thread.sleep(2000);

                tempBool=true;

            }
            catch (ElementNotInteractableException e){
                utilTestClass.closeSaleAdvert(driver);
                js.executeScript("window.scrollBy(0, -250)");

            }

        }
        tempBool=false;



        //large numbers

        while(tempBool==false){

            try{

                //normal values
                searchResultPage.setPriceRangeMin(-999999998);

                Thread.sleep(2000);

                searchResultPage.setPriceRangeMax(+999999999);
                Thread.sleep(2000);

                tempBool=true;

            }
            catch (ElementNotInteractableException e){
                utilTestClass.closeSaleAdvert(driver);
                js.executeScript("window.scrollBy(0, -250)");

            }

        }
        tempBool=false;

        while(tempBool==false){

            try{

                //normal values
                searchResultPage.setPriceRangeMin(-9999999.98);

                Thread.sleep(2000);

                searchResultPage.setPriceRangeMax(+9999999.99);
                Thread.sleep(2000);

                tempBool=true;

            }
            catch (ElementNotInteractableException e){
                utilTestClass.closeSaleAdvert(driver);
                js.executeScript("window.scrollBy(0, -250)");

            }

        }
        tempBool=false;
        while(tempBool==false){

            try{

                //normal values
                //Strings
                searchResultPage.setPriceRangeMinAndMaxAsString("abc","abc");

                tempBool=true;

            }
            catch (ElementNotInteractableException e){
                utilTestClass.closeSaleAdvert(driver);
                js.executeScript("window.scrollBy(0, -250)");

            }

        }
        tempBool=false;



    }






    @Test
    public void searchFilterOptions() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;



        SearchResultPage searchResultPage= utilTestClass.search("boty",driver);
        searchResultPage.clickPriceOption();
        searchResultPage.setPriceRangeMax(200);

        utilTestClass.closeSaleAdvert(driver);
        utilTestClass.closeSaleAdvert(driver);
        searchResultPage.setPriceRangeMin(10);

        js.executeScript("window.scrollBy(0, -250)");


        searchResultPage.clickSexOption();


        //selenium needs to scroll up else it thinks the element is hidden...
        //scroll up 250 pixels
        js.executeScript("window.scrollBy(0, -250)");
        searchResultPage.clickSexOption();
        js.executeScript("window.scrollBy(0, -250)");
        searchResultPage.selectFilterElementViaIndex(0);
        searchResultPage.selectFilterElementViaIndex(2);
        searchResultPage.selectFilterElementViaIndex(1);

    }






    void fillOutLoginInfo(WebDriver driver) throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo();



        Assertions.assertEquals("https://www.metalshop.cz/",driver.getCurrentUrl());


    }




//    DOESNT WORK
// Backupxd
//
//
//    @Test
//    public void inputTestPriceRange1() throws InterruptedException {
//        HomePage homePage = new HomePage(driver);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        utilTestClass.acceptCookies();
//        SearchResultPage searchResultPage = utilTestClass.search("Test",driver);
//
//        searchResultPage.clickPriceOption();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        utilTestClass.closeSaleAdvert(driver);
//        js.executeScript("window.scrollBy(0, -250)");
//
//        //normal values
//        searchResultPage.setPriceRangeMax(200);
//
//        searchResultPage.setPriceRangeMin(10);
//
//    }
//    @Test
//    public void inputTestPriceRange2() throws InterruptedException {
//        HomePage homePage = new HomePage(driver);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        utilTestClass.acceptCookies();
//        SearchResultPage searchResultPage = utilTestClass.search("Test",driver);
//
//        searchResultPage.clickPriceOption();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        utilTestClass.closeSaleAdvert(driver);
//        js.executeScript("window.scrollBy(0, -250)");
//
//        //max smaller than min values
//        searchResultPage.setPriceRangeMax(200);
//        searchResultPage.setPriceRangeMin(1000);
//
//
//    }
//
//
//
//    @Test
//    public void inputTestPriceRange3() throws InterruptedException {
//        HomePage homePage = new HomePage(driver);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        utilTestClass.acceptCookies();
//        SearchResultPage searchResultPage = utilTestClass.search("Test",driver);
//
//        searchResultPage.clickPriceOption();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        utilTestClass.closeSaleAdvert(driver);
//        js.executeScript("window.scrollBy(0, -250)");
//
//
//        //large numbers
//
//        searchResultPage.setPriceRangeMin(999999998);
//        searchResultPage.setPriceRangeMax(999999999);
//
//
//
//    }
//
//    @Test
//    public void inputTestPriceRange4() throws InterruptedException {
//        HomePage homePage = new HomePage(driver);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        utilTestClass.acceptCookies();
//        SearchResultPage searchResultPage = utilTestClass.search("Test",driver);
//
//        searchResultPage.clickPriceOption();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        utilTestClass.closeSaleAdvert(driver);
//        js.executeScript("window.scrollBy(0, -250)");
//
//
//        //+ and -
//        searchResultPage.setPriceRangeMin(-999999999);
//        searchResultPage.setPriceRangeMax(+999999999);
//
//
//
//    }
//
//    @Test
//    public void inputTestPriceRange5() throws InterruptedException {
//        HomePage homePage = new HomePage(driver);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        utilTestClass.acceptCookies();
//        SearchResultPage searchResultPage = utilTestClass.search("Test",driver);
//
//        searchResultPage.clickPriceOption();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        utilTestClass.closeSaleAdvert(driver);
//        js.executeScript("window.scrollBy(0, -250)");
//
//
//        //Strings
//        searchResultPage.setPriceRangeMinAndMaxAsString("abc","abc");
//
//
//
//    }

}
