import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.net.URISyntaxException;

public class EndToEndTests {

    private WebDriver driver;
    private WebDriverWait wait;
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
         driver.quit();
    }



    @Test
    public void firstTest() throws URISyntaxException, InterruptedException {
        SimpleTests simpleTests = new SimpleTests();

        HomePage homePage = new HomePage(driver);

        utilTestClass.acceptCookies();
        simpleTests.search("abc");

        utilTestClass.clickLoginOption(driver);

//        simpleTests.login
    }
}
