import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.Driver;

public class HomePage {
    private WebDriver driver;
//
//    @FindBy(css = "img[alt='Search Options']")
//    private WebElement searchOptionsButton;
//
//    @FindBy(css = "div[class='cross-nav cross-nav--wide'] a[class='register-link flyout-caption']")
//    private WebElement registerButton;
//
//    @FindBy(css = "#advanced-search-link")
//    private  WebElement advancedSearchLink;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://metalshop.cz");
    }

//    public HomePage clickSearchOptions() throws InterruptedException {
//        searchOptionsButton.click();
//        Thread.sleep(2000); // Wait for the page to load (adjust as needed)
//        return this;
//    }
//    public AdvancedSearchPage clickAdvancedSearch() throws InterruptedException {
//
//
//        advancedSearchLink.click();
//        Thread.sleep(2000); // Wait for the page to load (adjust as needed)
//        return new AdvancedSearchPage(driver);
//    }
//
//
//
//    public LoginPage clickRegister() throws InterruptedException {
//        registerButton.click();
//        Thread.sleep(2000); // Wait for the page to load (adjust as needed)
//        return new LoginPage(driver);
//    }
}
