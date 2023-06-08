package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductPage {


    private WebDriver driver;

//    @FindBy(how = How.CSS,using = "svg")
    WebElement saveProductTofavoritesButton;

    WebElement passwordVerificationField;
    // Find all web elements whose selectors start with "#variation_select"
    //for example size of a tshirt
    ElementsCollection productVariationElements;

    WebElement addToCartButton;


    //appears if sold out
//    @FindBy(how = How.CSS,using =".sold_out_detail")
    WebElement soldOutContainer;


//    @FindBy(how = How.CSS,using = "#li-tab8 > a")
WebElement reviewButtonAnchor;
    WebElement photosButtonAnchor;
    WebElement  descriptionButtonAnchor;
    WebElement discussionButtonAnchor;

//    @FindBy(how = How.CSS,using ="#product_rating_button")
    WebElement addReviewButton;

    List<WebElement> stars;
//            = driver.findElements(By.cssSelector("#product_rating_form > div.stars"));


    public ProductPage(WebDriver driver) {
        this.driver = driver;
//        stars = driver.findElements(By.cssSelector("#product_rating_form > div.stars"));
        productVariationElements = $$("[id^='variation_select']");


        addToCartButton = $("#buy");
        soldOutContainer = $(".sold_out_detail");
        reviewButtonAnchor = $("#li-tab8 > a");
        addReviewButton = $("#product_rating_button");
        saveProductTofavoritesButton = $("#product-heart");

        descriptionButtonAnchor=$("#li-tab1 > a");
        photosButtonAnchor=$("#li-tab2 > a");;
        discussionButtonAnchor=$("#li-tab3 > a");;

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


    }
    public void addProductToCart() {

        addToCartButton.click();

    }

    public ProductPage openDescriptionOption() {
        descriptionButtonAnchor.click();
        return this;
    }
    public ProductPage openPhotosOption() {
        photosButtonAnchor.click();
        return this;
    }
    public ProductPage openDiscussionOption() {
        discussionButtonAnchor.click();
        return this;
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
        $("#rating_star_" + String.valueOf(number)).click();

        return this;
    }

    public void clickCloseAddedToCartPopup(){
        $("#bought-too-modal > div > div > div > button").click();
    }

    public ProductPage fillInReviewForm(String name, String email, String text) {
        $("#rating_name").sendKeys(name);
        $("#rating_email").sendKeys(email);
        $("#rating_text").sendKeys(text);
        return this;
    }

    public ShoppingCartPage openCartFromPopup() {
        $("#bought-too-modal > div > div > div > div.modal-buttons > a.btn-submit").click();
        return new ShoppingCartPage(driver);
    }


}
