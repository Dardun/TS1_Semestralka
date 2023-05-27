package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ShoppingCartPage {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#alert_success > strong")
    private WebElement pageTitle;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }
}
