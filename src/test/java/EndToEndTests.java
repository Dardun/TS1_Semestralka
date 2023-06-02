import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

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

        //error
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
    public void accountOperationsTest(){

    }

    @Test
    public void somethingTest(){

    }


    @Test
    public void unloggedSomethingTest(){

    }
}
