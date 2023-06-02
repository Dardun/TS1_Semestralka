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
    WebElement saveProductTofavoritesButton;

    WebElement passwordVerificationField;
    // Find all web elements whose selectors start with "#variation_select"
    //for example size of a tshirt
    List<WebElement> productVariationElements = driver.findElements(By.cssSelector("[id^='variation_select']"));


    @FindBy(how = How.CSS,using ="#buy")
    WebElement addToCartButton;


    //appears if sold out
    @FindBy(how = How.CSS,using =".sold_out_detail")
    WebElement soldOutContainer;


    @FindBy(how = How.CSS,using = "#li-tab8 > a")
    WebElement reviewButtonAnchor;

    @FindBy(how = How.CSS,using ="#product_rating_button")
    WebElement addReviewButton;

    List<WebElement> stars = driver.findElements(By.cssSelector("#product_rating_form > div.stars"));


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addProductToFavorites(){

        saveProductTofavoritesButton.click();
    }


    //if index too large, picks the largest
    public void selectVariationViaIndex(int index) throws Exception {

        if (index<0){
            throw new Exception("faulty request!");
        }


        int productVariationNumber = productVariationElements.size();

        if(index+1>productVariationNumber){
            index = productVariationNumber+1;
        }


        WebElement variationIndexElement = productVariationElements.get(index);

        variationIndexElement.click();



        addToCartButton.click();

    }
    public void addProductToCart() {

        addToCartButton.click();

    }

    public ProductPage openReviewOption() {
        reviewButtonAnchor.click();
        return this;
    }

    public ProductPage addNewReview() {
        addReviewButton.click();
        return this;
    }

    public  ProductPage selectNumberOfStars(int number) {
        if (number > stars.size() -1) {
            stars.get(stars.size() - 2).click();
        } else if (number + 1 < stars.size() - 1) {
            stars.get(0).click();
        } else {
            stars.get(number).click();
        }
        return this;
    }

}
