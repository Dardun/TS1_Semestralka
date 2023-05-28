import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EndToEndTests {

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





     @AfterEach
    void teardown(){
         driver.quit();
     }

}
