import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.HomePage;

import static com.codeborne.selenide.Selenide.$;

public class UtilTestClass {


    @FindBy(how = How.CSS,using = ".fa.fa-facebook-square")
    WebElement bottomBarFaceBook;
    @FindBy(how = How.CSS,using = ".fa.fa-instagram")
    WebElement bottomBarInstagram;
    @FindBy(how = How.CSS,using = ".fa.fa-youtube-play")
    WebElement bottomBarYouTube;
    @FindBy(how = How.CSS,using = "img[alt='tiktok']")
    WebElement bottomBarTikTok;
    @FindBy(how = How.CSS,using = ".fa.fa-user")
    WebElement bottomBarCustomerPhotos;




    @FindBy(how = How.CSS,using = "div[class='container'] input[placeholder='Zadejte Váš e-mail']")
    WebElement subscribeEmailField;
    @FindBy(how = How.CSS,using = "div[class='container'] input[value='Odebírat']")
    WebElement subscribeToNewsletterButton;

    @FindBy(how = How.CSS,using = "a[href='https://www.metalshop.cz/s/doprava-a-platba/']")
    WebElement shippingInfoButton;



    @FindBy(how = How.CSS,using = "#newsletter-modal-in > div > div > div > button")
    private WebElement closeSaleAdvertButton;


    @Test
    void acceptCookies() throws InterruptedException {
        $ (By.cssSelector("#submit_cookies")).click();

    }
    public void closeSaleAdvert(WebDriver driver) throws  InterruptedException{


        closeSaleAdvertButton.click();


    }



}
