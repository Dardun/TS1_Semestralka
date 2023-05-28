package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#head_nav > ul > li:nth-child(3) > a")
    private WebElement loginButtonAnchor;

    @FindBy(how = How.CSS, using = "#header > div.container.top-header-margin > div.col-xs-4.col-2xs-4.small-devices-displayed > div > a:nth-child(4)")
    private WebElement cartButtonAnchor;

    @FindBy(how = How.CSS, using = "#header > div.container.top-header-margin > div.col-xs-4.col-2xs-4.small-devices-displayed > div > a:nth-child(2)")
    private WebElement favoritesButtonAnchor;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.bands > a")
    private WebElement bandsButtonAnchor;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.first > a")
    private WebElement menDropdown;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.sec_2 > a")
    private WebElement womenDropdown;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.last > a")
    private WebElement accessoriesDropdown;




    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://metalshop.cz");
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

    public HomePage chooseCategory(WebElement element) {
        element.click();
        return this;
    }

    public HomePage addProductToCart(WebElement element) {
        element.click();
        driver.findElement(By.cssSelector("#variation_select_135033_1_2 > div")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#buy"))));
//        driver.findElement(By.cssSelector("#buy")).click();
        return this;
    }


    public WebElement getMenDropdown() {
        return menDropdown;
    }

    public WebElement getWomenDropdown() {
        return womenDropdown;
    }

    public WebElement getAccessoriesDropdown() {
        return accessoriesDropdown;
    }
}
