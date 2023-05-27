package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#head_nav > ul > li:nth-child(3) > a")
    private WebElement loginButtonAnchor;

    @FindBy(how = How.CSS,using = "#newsletter-modal-in > div > div > div > button")
    private WebElement closeSaleAdvertButton;

    @FindBy(how = How.CSS, using = "#header > div.container.top-header-margin > div.col-xs-4.col-2xs-4.small-devices-displayed > div > a:nth-child(4)")
    private WebElement cartButtonAnchor;

    @FindBy(how = How.CSS, using = "#header > div.container.top-header-margin > div.col-xs-4.col-2xs-4.small-devices-displayed > div > a:nth-child(2)")
    private WebElement favoritesButtonAnchor;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.bands > a")
    private WebElement bandsButtonAnchor;
    @FindBy(how = How.CSS,using = "#submit_cookies")
    private WebElement cookieAnchor;




    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://metalshop.cz");
    }

    public HomePage openShop() {
        driver.get("https://metalshop.cz/");
        return this;
    }
    public HomePage closeSaleAdvert() {
        closeSaleAdvertButton.click();
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

    public HomePage acceptCookiesOnHomePage() {
        cookieAnchor.click();
        return this;
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
