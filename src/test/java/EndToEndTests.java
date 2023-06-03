import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

public class EndToEndTests {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
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





     @AfterEach
    void teardown(){
//         driver.quit();
    }



    @Test
    public void loggedinE2ETest() throws Exception {
        SimpleTests simpleTests = new SimpleTests();

        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();


        utilTestClass.clickLoginOption(driver);

        simpleTests.fillOutLoginInfo(driver);




        simpleTests.searchTest("boty", driver);

        SearchResultPage searchResultPage = new SearchResultPage(driver);

        searchResultPage.findAndClickProduct(0);

        ProductPage productPage = new ProductPage(driver);

        //error
        productPage.addProductToFavorites();


        FavoriteProductsPage favoriteProductsPage= utilTestClass.clickFavoritesOption(driver);


        if(!favoriteProductsPage.areFavoritesEmpty()) {
            favoriteProductsPage.clickProductInFavorites(0);
        }

        ProfilePage profilePage = utilTestClass.clickProfile(driver);


        profilePage.logOut();

//        simpleTests.login
    }


    @Test
    public void loggedOutE2ETest() throws Exception {


        SimpleTests simpleTests = new SimpleTests();

        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();
        utilTestClass.clickLoginOption(driver);

        //testing wrong login information
        simpleTests.fillOutWrongLoginInfo(driver);

        simpleTests.searchTest("boty", driver);

        SearchResultPage searchResultPage = new SearchResultPage(driver);

        searchResultPage.findAndClickProduct(0);

        ProductPage productPage = new ProductPage(driver);

        productPage.addProductToFavorites();

        FavoriteProductsPage favoriteProductsPage= utilTestClass.clickFavoritesOption(driver);

        if(!favoriteProductsPage.areFavoritesEmpty()) {
            favoriteProductsPage.clickProductInFavorites(0);
        }

        driver.manage().deleteAllCookies();

        favoriteProductsPage = new FavoriteProductsPage(driver);

        if(!favoriteProductsPage.areFavoritesEmpty()) {
            throw new Exception("Favorites shouldn't have anythign when cookies are cleared!");
        }
        ProfilePage profilePage = utilTestClass.clickProfile(driver);

    }


    @Test
    public void accountOperationsTest() throws InterruptedException {

        SimpleTests simpleTests = new SimpleTests();

        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();

        LoginPage loginPage = utilTestClass.clickLoginOption(driver);

        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo();

        ProfilePage profilePage = utilTestClass.clickProfile(driver);

        String newName = "Adam";
        String newSurname = "Strobl";
        String newCity = "Velke Prilepy";
        String newPhone = "731881966";

        //        Email is NOT editable
        String newEmail = "testingseleniumcvut@protonmail.com";
        String newPassword = "Mamradtesting12345!";
        String newStreetandHouseNum = "Sukova 413";
        String newZIP = "25264";


        profilePage.changeName(newName);
        profilePage.changeSurname(newSurname);
        profilePage.changePhone(newPhone);
        profilePage.changeCity(newCity);
//        profilePage.changeEmail(newEmail);
        profilePage.changePassword(newPassword);
        profilePage.changeStreetAndHouseNum(newStreetandHouseNum);
        profilePage.changeZIP(newZIP);
        profilePage.clickNewsletterCB();




        profilePage.goToCreditsTab();

        webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(2));

        profilePage.goToOrdersTab();

        webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(2));

        profilePage.goToProfilePhotosTab();

        webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(2));

        profilePage.goToProfileTab();

        webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(2));



        profilePage.submitForm();




        driver.manage().deleteAllCookies();

        driver.get("https://www.metalshop.cz/");

        utilTestClass.acceptCookies();

        loginPage = utilTestClass.clickLoginOption(driver);


//        loginPage.inputTextIntoNameEmailField(newEmail);
//        loginPage.inputTextIntoPWField(newPassword);
//        loginPage.submitLoginInfo();

        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField(newPassword);
        loginPage.submitLoginInfo();

        utilTestClass.clickProfile(driver);




        //        profilePage

//        ASSERT THAT THE NEW STUFFS ARE THERE


        Assertions.assertEquals(newCity,profilePage.getCityField().getAttribute("value"));

        Assertions.assertEquals(newZIP,profilePage.getZipField().getAttribute("value"));

        Assertions.assertEquals(newEmail,profilePage.getEmailField().getAttribute("value"));

        Assertions.assertEquals(newStreetandHouseNum,profilePage.getStreetField().getAttribute("value"));




        profilePage = utilTestClass.clickProfile(driver);





        newCity = "Adamov";
//        newEmail = "strobad1@fel.cvut.cz";

      //change as needed
        newPassword = "Mamradtesting12345!";
        newStreetandHouseNum = "Vresova 222";
        newZIP = "37371";

        profilePage.changeCity(newCity);
//        profilePage.changeEmail(newEmail);
        profilePage.changePassword(newPassword);
        profilePage.changeStreetAndHouseNum(newStreetandHouseNum);
        profilePage.changeZIP(newZIP);
        profilePage.clickNewsletterCB();

        profilePage.submitForm();


    }

    @Test
    public void somethingTest(){

    }


    @Test
    public void unloggedSomethingTest(){

    }
}
