package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    private WebDriver driver;

    @FindBy(how = How.CSS,using = "#cart_buy_buttons > div > input")
    WebElement continueButton;

    @FindBy(how = How.CSS, using = "#alert_success > strong")
    private WebElement pageTitle;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://www.metalshop.cz/ncart/");
    }

    public DeliveryPage clickContinueButton() {
        continueButton.click();
        return new DeliveryPage(driver);
    }


}
