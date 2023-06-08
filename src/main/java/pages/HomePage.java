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


    //TODO Presunout do util Classu? Vanes? nevim ktere tyto presne jsou
    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.bands > a")
    private WebElement bandsButtonAnchor;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.first > a")
    private WebElement menDropdown;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.sec_2 > a")
    private WebElement womenDropdown;

    @FindBy(how = How.CSS, using = "#mySidenav > div > ul > li.dropdown.last > a")
    private WebElement accessoriesDropdown;

    @FindBy(how = How.CSS, using = "#head_nav > ul > li:nth-child(3) > a > div.icon > span")
    private WebElement logInButton;




    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://metalshop.cz");
    }

    public HomePage openShop() {
        driver.get("https://metalshop.cz/");
        return this;
    }

    public ProductPage selectProduct(WebElement element) {
        element.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#page-product > section > div.container.detail-mobile-padding-none > div > div.col-sm-6.detail_fr"))));
        return new ProductPage(driver);
    }

    public LoginPage clickLoginButton() {
        logInButton.click();
        return new LoginPage(driver);
    }


    public BandsPage clickBandsOption() {
        bandsButtonAnchor.click();
        return new BandsPage(driver);
    }

    public HomePage chooseCategory(WebElement element) {
        element.click();
        return this;
    }

    public SearchResultPage chooseCategorySearchPage(WebElement element) {
        element.click();
        return new SearchResultPage(driver);
    }

    public HomePage selectProductsSize(WebElement element) {
        element.click();
        driver.findElement(By.cssSelector("#variation_select_135033_1_2")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#buy"))));
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
