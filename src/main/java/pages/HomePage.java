package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.sql.Driver;

public class HomePage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "body > div:nth-child(1) > div:nth-child(3) > header:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1) > div:nth-child(2)")
    private WebElement loginButtonAnchor;

    @FindBy(how = How.CSS, using = "body > div:nth-child(1) > div:nth-child(3) > header:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1) > div:nth-child(2)")
    private WebElement cartButtonAnchor;

    @FindBy(how = How.CSS, using = "body > div:nth-child(1) > div:nth-child(3) > header:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1) > div:nth-child(2)")
    private WebElement favoritesButtonAnchor;

    @FindBy(how = How.CSS, using = "body > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > nav:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(10) > a:nth-child(1)")
    private WebElement bandsButtonAnchor;
//
//    @FindBy(css = "div[class='cross-nav cross-nav--wide'] a[class='register-link flyout-caption']")
//    private WebElement registerButton;
//
//    @FindBy(css = "#advanced-search-link")
//    private  WebElement advancedSearchLink;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
//        driver.get("https://metalshop.cz");
    }

    public HomePage openShop() {
        driver.get("https://metalshop.cz/");
        return this;
    }

    public ShoppingCartPage clickShoppingCartOption() {
        cartButtonAnchor.click();
        return new ShoppingCartPage(driver);
    }

    public LoginPage clickLoginOption() {
        loginButtonAnchor.click();
        return new LoginPage(driver);
    }

    public FavoriteProductsPage clickFavoritesOption() {
        favoritesButtonAnchor.click();
        return new FavoriteProductsPage(driver);
    }

    public BandsPage clickBandsOption() {
        bandsButtonAnchor.click();
        return new BandsPage(driver);
    }


//    public pages.HomePage clickSearchOptions() throws InterruptedException {
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
