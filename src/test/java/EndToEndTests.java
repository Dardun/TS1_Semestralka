import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

public class EndToEndTests {

    UtilTestClass utilTestClass = new UtilTestClass();
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver();

    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }


    @AfterEach
    void teardown() {
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


        FavoriteProductsPage favoriteProductsPage = utilTestClass.clickFavoritesOption(driver);


        if (!favoriteProductsPage.areFavoritesEmpty()) {
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

        FavoriteProductsPage favoriteProductsPage = utilTestClass.clickFavoritesOption(driver);

        if (!favoriteProductsPage.areFavoritesEmpty()) {
            favoriteProductsPage.clickProductInFavorites(0);
        }

        driver.manage().deleteAllCookies();

        favoriteProductsPage = new FavoriteProductsPage(driver);

        if (!favoriteProductsPage.areFavoritesEmpty()) {
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

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        profilePage.goToOrdersTab();

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        profilePage.goToProfilePhotosTab();

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        profilePage.goToProfileTab();

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));


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


        Assertions.assertEquals(newCity, profilePage.getCityField().getAttribute("value"));

        Assertions.assertEquals(newZIP, profilePage.getZipField().getAttribute("value"));

        Assertions.assertEquals(newEmail, profilePage.getEmailField().getAttribute("value"));

        Assertions.assertEquals(newStreetandHouseNum, profilePage.getStreetField().getAttribute("value"));


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
    public void somethingTest() {

    }


    @DataProvider(name = "searchData")
    public Object[][] readCSV() throws IOException {
        // Read the CSV file and store the values in a 2D array
        BufferedReader br = new BufferedReader(new FileReader("testingFiles/data.csv"));
        String line;
        int rowCount = 0;
        int colCount = 0;

        // Get the number of rows and columns
        while ((line = br.readLine()) != null) {
            rowCount++;
            String[] cells = line.split(",");
            colCount = cells.length;
        }

        // Create a 2D array with the same size as the CSV file
        Object[][] data = new Object[rowCount][colCount];

        // Reset the buffered reader
        br = new BufferedReader(new FileReader("path/to/data.csv"));

        // Fill the 2D array with the values from the CSV file
        int i = 0;
        while ((line = br.readLine()) != null) {
            String[] cells = line.split(",");
            for (int j = 0; j < colCount; j++) {
                data[i][j] = cells[j];
            }
            i++;
        }

        // Close the buffered reader
        br.close();

        // Return the 2D array
        return data;


    }


    @Test(dataProvider = "searchData")
    public void dataProviderTest2(String keyword, String expectedTitle) throws InterruptedException {
        // Search for the keyword using Google

        HomePage homePage= new HomePage(driver);

        utilTestClass.acceptCookies();
        utilTestClass.search();


    }

}
