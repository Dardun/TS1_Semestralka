import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

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

    @Test
    public void acceptCookies() throws InterruptedException {



        $ (By.cssSelector("#submit_cookies")).click();


    }

    public void closeSaleAdvert() throws  InterruptedException{


        HomePage homePage = new HomePage(driver);

        homePage.closeSaleAdvert();
    }


    @Test
    void cookiesPlusLogin() throws InterruptedException {

        HomePage homePage = new HomePage(driver);

         acceptCookies();
         getToLoginPage();

    }

}
