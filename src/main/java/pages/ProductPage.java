package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductPage {


    private WebDriver driver;

    @FindBy(how = How.CSS,using = "svg")
    WebElement saveProductTofavorites;
    @FindBy(how = How.CSS,using = "#pass")
    WebElement passwordField;
    @FindBy(how = How.CSS,using = "#pass_ver")
    WebElement passwordVerificationField;
    // Find all web elements whose selectors start with "#variation_select"
    //for example size of a tshirt
    List<WebElement> productVariationElements = driver.findElements(By.cssSelector("[id^='variation_select']"));


    @FindBy(how = How.CSS,using ="#buy")
    WebElement addToCart;



    //appears if sold out
    @FindBy(how = How.CSS,using =".sold_out_detail")
    WebElement soldOutBox;

}
