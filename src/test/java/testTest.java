import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.text.StringEscapeUtils;
import org.asynchttpclient.util.UriEncoder;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;

public class testTest {

    WebDriver driver;


    @BeforeAll
    static void setupAll(){
         WebDriverManager.chromedriver();

     }

     @BeforeEach
    void setup(){
         driver = new ChromeDriver();
         WebDriverRunner.setWebDriver(driver);
     }


//     @AfterEach
//    void teardown(){
//         driver.quit();
//     }





     // DO COOKIES FIRST
    @Test
    public void getToLoginPage() throws InterruptedException {

        HomePage homePage = new HomePage(driver);

        LoginPage loginPage = homePage.clickLoginOption();


    }


    public void closeSaleAdvert() throws  InterruptedException{


        HomePage homePage = new HomePage(driver);

        homePage.closeSaleAdvert();
    }


    @Test
    void acceptCookies() throws InterruptedException {
        $ (By.cssSelector("#submit_cookies")).click();

    }


    @Test
    void fillOutLoginInfo() throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        acceptCookies();
        loginPage.inputTextIntoNameEmailField("testingseleniumcvut@protonmail.com");
        loginPage.inputTextIntoPWField("protondeeznuts123");
        loginPage.submitLoginInfo();



        Assertions.assertEquals("https://www.metalshop.cz/",driver.getCurrentUrl());


    }


    @Test
    void fillOutWrongLoginInfo() throws  InterruptedException{
        LoginPage loginPage = new LoginPage(driver);


        acceptCookies();
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
        acceptCookies();
        search("4=čárkaěěxxdd");
        driver.close();
        driver.quit();
    }
}
