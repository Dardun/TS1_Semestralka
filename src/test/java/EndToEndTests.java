import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.jupiter.api.*;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Assertions;
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


    //Adam test 3
    @Test
    public void loggedinE2ETest() throws Exception {
        SimpleTests simpleTests = new SimpleTests();

        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();

        FavoriteProductsPage favoriteProductsPage= utilTestClass.clickFavoritesOption(driver);

        favoriteProductsPage.removeAllFavorites();

        utilTestClass.clickLoginOption(driver);

        LoginPage loginPage = new LoginPage(driver);


        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo();




        simpleTests.searchTest("boty", driver);

        SearchResultPage searchResultPage = new SearchResultPage(driver);

        ProductPage productPage = searchResultPage.findAndClickProduct(0,driver);


        productPage.addProductToFavorites();


        favoriteProductsPage = utilTestClass.clickFavoritesOption(driver);


        if (!favoriteProductsPage.areFavoritesEmpty()) {
            favoriteProductsPage.clickProductInFavorites(0);
        }

        ProfilePage profilePage = utilTestClass.clickProfile(driver);


        profilePage.logOut();



        utilTestClass.clickBottomBarYouTube();
//        simpleTests.login
    }


//Adam test 2
    @Test
    public void loggedOutE2ETest() throws Exception {

        HomePage homePage = new HomePage(driver);

        SimpleTests simpleTests = new SimpleTests();


        utilTestClass.acceptCookies();

        utilTestClass.clickLoginOption(driver);

        //testing wrong login information
        simpleTests.fillOutWrongLoginInfo(driver);

        simpleTests.searchTest("boty", driver);

        SearchResultPage searchResultPage = new SearchResultPage(driver);

        ProductPage productPage = searchResultPage.findAndClickProduct(0,driver);



        productPage.addProductToFavorites();

        FavoriteProductsPage favoriteProductsPage = utilTestClass.clickFavoritesOption(driver);

        if (!favoriteProductsPage.areFavoritesEmpty()) {
            favoriteProductsPage.clickProductInFavorites(0);
        }

        driver.manage().deleteAllCookies();

        favoriteProductsPage = new FavoriteProductsPage(driver);

        if (!favoriteProductsPage.areFavoritesEmpty()) {
            throw new Exception("Favorites shouldn't have anything when cookies are cleared!");
        }


        homePage = utilTestClass.goToHomePage(driver);

        utilTestClass.acceptCookies();

        BandsPage bandsPage = homePage.clickBandsOption();


        searchResultPage = bandsPage.findAndClickBand(driver,"Alter Bridge");

        productPage = searchResultPage.findAndClickProduct(0,driver);

        productPage.openReviewOption();
        productPage.addNewReview();

        productPage.selectNumberOfStars(4);
        productPage.fillInReviewForm("Adam Strobl", "strobad1@fel.cvut.cz","fajnove");

        utilTestClass.closeSaleAdvert(driver);


        utilTestClass.clickBottomBarInstagram();

    }


    //Adam test 1
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



        utilTestClass.clickBottomBarTikTok();

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




    //Adam test 4 done
    @Test(dataProvider = "dataProviderSearchTest")
    public void dataProviderSearchTest(String keyword) throws Exception {

        SimpleTests simpleTests = new SimpleTests();
        HomePage homePage = new HomePage(driver);



        utilTestClass.acceptCookies();
        SearchResultPage searchResultPage = utilTestClass.search(keyword,driver);

        ProductPage productPage = searchResultPage.findAndClickProduct(0,driver);

        productPage.openPhotosOption();
        productPage.openDescriptionOption();
        productPage.addProductToFavorites();

        productPage.addProductToCart();
//        productPage.clickCloseAddedToCartPopup();

        LoginPage loginPage =utilTestClass.clickLoginOption(driver);


        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");

        loginPage.submitLoginInfo();

        homePage.getWomenDropdown().click();

        utilTestClass.goToHomePage(driver);

        ShoppingCartPage shoppingCartPage = utilTestClass.clickShoppingCartOption(driver);

        DeliveryPage deliveryPage = shoppingCartPage.clickContinueButton();

        deliveryPage.clickContinueButton();


        shoppingCartPage = utilTestClass.clickShoppingCartOption(driver);


        shoppingCartPage.deleteAllFromShoppingCart();

        utilTestClass.goToHomePage(driver);

        //should fail
        utilTestClass.subscribeToNewsletter(driver,"falesnyemail123");

        //should go through
        utilTestClass.subscribeToNewsletter(driver,"cvutseleniumtesting@protonmail.com");



        utilTestClass.clickBottomBarCustomerPhotos();

        utilTestClass.clickBottomBarTikTok();

    }



//    ///upravit, prodlouzit
//    @Test(dataProvider = "searchData2")
//    public void dataProviderSearchTest2(String keyword) throws InterruptedException {
//
//        SimpleTests simpleTests = new SimpleTests();
//        HomePage homePage = new HomePage(driver);
//
//        utilTestClass.acceptCookies();
//        utilTestClass.search(keyword,driver);
//
//    }


    //dataprovider
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



    //adam test 5
    @Test(dataProvider = "firmData")
    public void firmDataProviderTest(String name, String street, String ico, String city, String dic, String zip) throws Exception {

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

        ProductPage productPage = searchResultPage.findAndClickProduct(2,driver);

        productPage.selectVariationViaIndex(1);



        profilePage = utilTestClass.clickProfile(driver);

        profilePage.goToOrdersTab();
        profilePage.goToProfilePhotosTab();
        profilePage.goToCreditsTab();
        profilePage.goToProfileTab();
        profilePage.logOut();

        homePage.getAccessoriesDropdown().click();
        utilTestClass.clickShippingInfoButton();
        utilTestClass.goToHomePage(driver);

        utilTestClass.clickFavoritesOption(driver);

        utilTestClass.search("jsemOdhlasen123",driver);
        searchResultPage = utilTestClass.search("Gutalax",driver);
        searchResultPage = utilTestClass.search("avenged sevenfold",driver);

        searchResultPage.goToPageNumber(3);

        productPage = searchResultPage.findAndClickProduct(1,driver);

        productPage.openDiscussionOption();
        productPage.openReviewOption();
        productPage.addNewReview();

        productPage.fillInReviewForm("","","");


        productPage.fillInReviewForm("aaa235@","22","nelibii sem itpo@!#!%@");

        productPage.selectNumberOfStars(1);
        productPage.fillInReviewForm("adamek stroblicek","adamstrobk@gmail.com","asi ok");





    }

    //vaness TODO
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

    //vaness
    @Test
    public void addProductToCartTest() throws Exception {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();
        HomePage homePage1 = homePage.chooseCategory(homePage.getWomenDropdown()); // clicked on woman category
        utilTestClass.closeSaleAdvert(driver);

        // choosing product and its size
        driver.findElement(By.cssSelector("#page-section > section > div > section > div.products_header.col-sm-12 > div > div.mp_category.vypis-filtre > div.row.row-products > div:nth-child(4) > div > a > picture:nth-child(5) > img")).click();
        ProductPage productPage = new ProductPage(driver);
        productPage.selectVariationViaIndex(0);

        // waiting till the color of the size box turns orange, therefore is selected
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((WebDriver driver) -> {
            String border = driver.findElement(By.cssSelector("#variation_select_135033_1_2 > div")).getCssValue("border");
            return border.equals("1px solid rgb(236, 127, 0)");
        });
        //add product to favorites
        productPage.addProductToFavorites();

        // clicking on the buy button
        productPage.addProductToCart();

        //waiting for the website to process the addition
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bought-too-modal > div > div")));

        // checking for the number above cart icon
        Assertions.assertEquals("1", driver.findElement(By.cssSelector("#small_cart_amount")).getText());

        // opening shoping cart
        ShoppingCartPage shoppingCartPage = productPage.openCartFromPopup();

        // deleting the products
        shoppingCartPage.deleteAllFromShoppingCart();

    }

    //vaness
    @Test
    public void loggedOutCheckoutEnd2End() throws Exception {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();

        //seaching for a product
        SearchResultPage searchResultPage = utilTestClass.search("I love Satan",driver);

        //selecting product and its size
        ProductPage productPage = searchResultPage.findAndClickProduct(2, driver);
        productPage.selectVariationViaIndex(0);

        //adding it to favorites
        productPage.addProductToFavorites();


        //adding product to the cart
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        productPage.addProductToCart();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        utilTestClass.clickShoppingCartOption(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        DeliveryPage deliveryPage = shoppingCartPage.clickContinueButton();
        deliveryPage.clickDeliveryOption(9);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("#dpform > div.new-cart-form-buttons.new-cart-left > div > input")).click();


        Assertions.assertEquals("Váš nákupní košík\n" +
                "Pokud není stanoveno jinak, jsou uvedené ceny včetně DPH", driver.findElement(By.cssSelector("#top-affix > section > div.container > h1")).getText());

    }

    //vaness
    @Test
    public void loggedInCheckoutEnd2End() throws Exception {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();

        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo();

        //seaching for a product
        SearchResultPage searchResultPage = utilTestClass.search("I love Satan",driver);

        //selecting product and its size
        ProductPage productPage = searchResultPage.findAndClickProduct(2, driver);
        productPage.selectVariationViaIndex(0);

        //adding it to favorites
        productPage.addProductToFavorites();


        //adding product to the cart
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        productPage.addProductToCart();


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        utilTestClass.clickShoppingCartOption(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        DeliveryPage deliveryPage = shoppingCartPage.clickContinueButton();
        deliveryPage.clickDeliveryOption(9);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("#dpform > div.new-cart-form-buttons.new-cart-left > div > input")).click();


        Assertions.assertEquals("Váš nákupní košík\n" +
                "Pokud není stanoveno jinak, jsou uvedené ceny včetně DPH", driver.findElement(By.cssSelector("#top-affix > section > div.container > h1")).getText());

    }


    //vaness
    @Test
    public void reviewScenarioEnd2End() throws Exception {
        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();
        SearchResultPage searchPage = homePage.chooseCategorySearchPage(homePage.getAccessoriesDropdown()); // clicked on woman category

        ProductPage productPage = searchPage.findAndClickProduct(2, driver);


        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("Mamradtesting12345!");
        loginPage.submitLoginInfo();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        utilTestClass.closeSaleAdvert(driver);


        productPage.openReviewOption().addNewReview();
        productPage.fillInReviewForm("Isména Temná", "ismenatemna@gmail.com", "Skvelý produkt, rozhodne sa nechcem zabiť.");
        productPage.selectNumberOfStars(4);

        Assertions.assertEquals("Isména Temná", driver.findElement(By.cssSelector("#rating_name")).getAttribute("value"));


        SearchResultPage searchResultPage = homePage.chooseCategorySearchPage(homePage.getMenDropdown());

        //selecting product and its size
        productPage = searchResultPage.findAndClickProduct(3, driver);

        productPage.addProductToFavorites();

        productPage.openReviewOption();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        productPage.addNewReview();
        productPage.fillInReviewForm("Isména Temná", "ismenatemna@gmail.com", "Manžel v ňom miluje zakopávať mŕtvoly.");
        productPage.selectNumberOfStars(5);


        Assertions.assertEquals("Isména Temná", driver.findElement(By.cssSelector("#rating_name")).getAttribute("value"));

    }

    //vaness
    @Test
    public void browsingFavoritesEnd2End() throws Exception {
        HomePage homePage = new HomePage(driver);
        utilTestClass.acceptCookies();

        SearchResultPage searchPage = homePage.chooseCategorySearchPage(homePage.getAccessoriesDropdown());
        ProductPage productPage = searchPage.findAndClickProduct(4, driver);

        productPage.openPhotosOption();
        productPage.openDescriptionOption();
        productPage.addProductToFavorites();

        searchPage = homePage.chooseCategorySearchPage(homePage.getWomenDropdown());
        productPage = searchPage.findAndClickProduct(2, driver);
        productPage.addProductToFavorites();

        searchPage = homePage.chooseCategorySearchPage(homePage.getMenDropdown());
        productPage = searchPage.findAndClickProduct(5, driver);
        productPage.addProductToFavorites();

        productPage.openDiscussionOption();


        FavoriteProductsPage favoriteProductsPage = utilTestClass.clickFavoritesOption(driver);

//        favoriteProductsPage.removeAllFavorites();

        Assertions.assertTrue(!favoriteProductsPage.areFavoritesEmpty());

    }



}
