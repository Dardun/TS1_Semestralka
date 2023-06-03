package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DeliveryPage {

    private WebDriver driver;

//    @FindBy(how = How.CSS,using = "#dpform > div.new-cart-left.cart-form-container > table:nth-child(2)")
    List<WebElement> deliveryOptionsTable;

    @FindBy(how = How.CSS, using = "#top-affix > section > div.container > h1")
    private WebElement pageTitle;

    public DeliveryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://www.metalshop.cz/ncart/delivery/");

        deliveryOptionsTable = driver.findElements(By.cssSelector(".delivery"));
    }

    public DeliveryPage clickDeliveryOption(int index) {
        deliveryOptionsTable.get(index).click();
        return this;
    }

    public DeliveryPage clickContinueButton() {
        driver.findElement(By.cssSelector("#dpform > div.new-cart-form-buttons.new-cart-left > div > input")).click();
        return this;
    }



    public List<WebElement> getDeliveryOptionsTable() {
        return deliveryOptionsTable;
    }
}
