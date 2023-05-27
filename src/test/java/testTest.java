import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testTest {

    WebDriver driver;

     @BeforeAll
    static void setupAll(){
         WebDriverManager.chromedriver();
     }


     @BeforeEach
    void setup(){
         driver = new ChromeDriver();
     }


     @AfterEach
    void teardown(){
         driver.quit();
     }

@Test
     void test(){
         //test logic
         //
         //
         HomePage homePage = new HomePage(driver);
     }
}
