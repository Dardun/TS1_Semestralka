import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.*;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndToEndTests {

    UtilTestClass utilTestClass = new UtilTestClass();
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeSuite
    static void setupAll() {
        WebDriverManager.chromedriver();

    }

    @BeforeMethod
    void setup() {
        driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }


    @AfterMethod
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




        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField(newPassword);
        loginPage.submitLoginInfo();

        utilTestClass.clickProfile(driver);


        //        profilePage

//        ASSERT THAT THE NEW STUFFS ARE THERE



        assertEquals(newCity, profilePage.getCityField().getAttribute("value"));

        assertEquals(newZIP, profilePage.getZipField().getAttribute("value"));

        assertEquals(newEmail, profilePage.getEmailField().getAttribute("value"));

        assertEquals(newStreetandHouseNum, profilePage.getStreetField().getAttribute("value"));


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


    @DataProvider(name = "dataProviderSearchTest")
    public Object[][] readCSV() throws IOException {
        // Get the class loader of this class
        ClassLoader classLoader = this.getClass().getClassLoader();

        // Get the input stream of the resource
        InputStream inputStream = classLoader.getResourceAsStream("testingFiles/searchData.csv");

        // Check if the resource exists
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: testingFiles/searchData.csv");
        }

        // Use a buffered reader to read the input stream
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // Create a list to store the data
        List<Object[]> data = new ArrayList<>();

        // Read each line of the file and add it to the list as an object array
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Split the line by comma and trim any whitespace
            String[] cells = line.split(",");
            for (int i = 0; i < cells.length; i++) {
                cells[i] = cells[i].trim();
            }
            // Add the cells as an object array to the list
            data.add(cells);
        }

        // Close the buffered reader and the input stream
        bufferedReader.close();
        inputStream.close();

        // Convert the list to a 2D array and return it
        return data.toArray(new Object[0][]);
    }



    @Test(dataProvider = "searchData")
    public void dataProviderSearchTest(String keyword) throws InterruptedException {

        SimpleTests simpleTests = new SimpleTests();
        HomePage homePage = new HomePage(driver);



        utilTestClass.acceptCookies();
        utilTestClass.search(keyword,driver);
    }


    ///upravit, prodlouzit
    @Test(dataProvider = "searchData2")
    public void dataProviderSearchTest2(String keyword) throws InterruptedException {

        SimpleTests simpleTests = new SimpleTests();
        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();
        utilTestClass.search(keyword,driver);

    }


    @DataProvider(name = "firmData")
    public Object[][] readFirmDataCSV() throws IOException {
        // Get the class loader of this class
        ClassLoader classLoader = this.getClass().getClassLoader();

        // Get the input stream of the resource
        InputStream inputStream = classLoader.getResourceAsStream("testingFiles/firmData.csv");

        // Check if the resource exists
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: testingFiles/firmData.csv");
        }

        // Use a buffered reader to read the input stream
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // Create a list to store the data
        List<Object[]> data = new ArrayList<>();

        // Read each line of the file and add it to the list as an object array
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Split the line by comma and trim any whitespace
            String[] cells = line.split(",");
            for (int i = 0; i < cells.length; i++) {
                cells[i] = cells[i].trim();
            }
            // Add the cells as an object array to the list
            data.add(cells);
        }

        // Close the buffered reader and the input stream
        bufferedReader.close();
        inputStream.close();

        // Convert the list to a 2D array and return it
        return data.toArray(new Object[0][]);
    }



    ///upravit, prodlouzit
    @Test(dataProvider = "firmData")
    public void firmDataProviderTest(String name, String street, String ico, String city, String dic, String zip) throws InterruptedException {

        SimpleTests simpleTests = new SimpleTests();
        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();

        LoginPage loginPage = utilTestClass.clickLoginOption(driver);


        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo();

        ProfilePage profilePage = utilTestClass.clickProfile(driver);

        profilePage.fillOutFirmAndSubmit(name,street,ico,city,dic,zip);


        SearchResultPage searchResultPage = utilTestClass.search("Megadeth",driver);



    }

    @Test(dataProvider = "reviewData")
    public void addReviewTest(String name, String email, String review) throws InterruptedException {
        driver.get("https://www.metalshop.cz/p/135033-tricko-unisex-killstar-soul-card-black-ksra007941/");
        utilTestClass.acceptCookies();
        ProductPage productPage = new ProductPage(driver)
                .openReviewOption()
                .addNewReview();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#product_rating_form > div.stars"))));
        productPage.selectNumberOfStars(5);
        productPage.fillInReviewForm(name, email, review);
        // ...
    }

    @DataProvider(name = "reviewData")
    public Object[][] readReviewDataCSV() throws IOException {
        // Get the class loader of this class
        ClassLoader classLoader = this.getClass().getClassLoader();

        // Get the input stream of the resource
        InputStream inputStream = classLoader.getResourceAsStream("testingFiles/reviewData.csv");

        // Check if the resource exists
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: testingFiles/reviewData.csv");
        }

        // Use a buffered reader to read the input stream
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // Create a list to store the data
        List<Object[]> data = new ArrayList<>();

        // Read each line of the file and add it to the list as an object array
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Split the line by comma and trim any whitespace
            String[] cells = line.split(",");
            for (int i = 0; i < cells.length; i++) {
                cells[i] = cells[i].trim();
            }
            // Add the cells as an object array to the list
            data.add(cells);
        }

        // Close the buffered reader and the input stream
        bufferedReader.close();
        inputStream.close();

        // Convert the list to a 2D array and return it
        return data.toArray(new Object[0][]);
    }





}
