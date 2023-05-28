import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.FavoriteProductsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ShoppingCartPage;

import static com.codeborne.selenide.Selenide.$;

public class UtilTestClass {







    @Test
    public void clickBottomBarFaceBook() {
        WebElement bottomBarFaceBook = $(".fa.fa-facebook-square");
        bottomBarFaceBook.click();
    }
    @Test
    public void clickBottomBarInstagram() {
        WebElement bottomBarInstagram = $(".fa.fa-instagram");
        bottomBarInstagram.click();
    }
    @Test
    public void clickBottomBarYouTube() {
        WebElement bottomBarYouTube = $(".fa.fa-youtube-play");
        bottomBarYouTube.click();
    }
    @Test
    public void clickBottomBarTikTok() {
        WebElement bottomBarTikTok = $("img[alt='tiktok']");
        bottomBarTikTok.click();
    }
    @Test
    public void clickBottomBarCustomerPhotos() {
        WebElement bottomBarCustomerPhotos = $(".fa.fa-user");
        bottomBarCustomerPhotos.click();
    }
    @Test
    public void setSubscribeEmail(String email) {
        WebElement subscribeEmailField = $("div.container input[placeholder='Zadejte Váš e-mail']");
        subscribeEmailField.sendKeys(email);
    }
    @Test
    public void clickSubscribeToNewsletterButton() {
        WebElement subscribeToNewsletterButton = $("div.container input[value='Odebírat']");
        subscribeToNewsletterButton.click();
    }
    @Test
    public void clickShippingInfoButton() {
        WebElement shippingInfoButton = $("a[href='https://www.metalshop.cz/s/doprava-a-platba/']");
        shippingInfoButton.click();
    }




    @Test
    void acceptCookies() throws InterruptedException {
        $ (By.cssSelector("#submit_cookies")).click();
    }
    public void closeSaleAdvert(WebDriver driver) throws  InterruptedException{
        WebElement closeSaleAdvertButton = $("#newsletter-modal-in > div > div > div > button");
        closeSaleAdvertButton.click();
    }



    public ShoppingCartPage clickShoppingCartOption(WebDriver driver) {
        WebElement cartButtonAnchor = Selenide.$("#header > div.container.top-header-margin > div.col-xs-4.col-2xs-4.small-devices-displayed > div > a:nth-child(4)");

        cartButtonAnchor.click();
        return new ShoppingCartPage(driver);
    }

    public LoginPage clickLoginOption(WebDriver driver) {


        WebElement loginButtonAnchor = Selenide.$("#head_nav > ul > li:nth-child(3) > a");

        loginButtonAnchor.click();
        return new LoginPage(driver);
    }


    public FavoriteProductsPage clickFavoritesOption(WebDriver driver) {
        WebElement favoritesButtonAnchor = Selenide.$("#header > div.container.top-header-margin > div.col-xs-4.col-2xs-4.small-devices-displayed > div > a:nth-child(2)");
        favoritesButtonAnchor.click();
        return new FavoriteProductsPage(driver);
    }




}
