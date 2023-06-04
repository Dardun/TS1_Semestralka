import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.net.URISyntaxException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class UtilTestClass {



    @Test
    public SearchResultPage search(String searchString, WebDriver driver){
        $ (By.cssSelector("#fulltextvalue")).sendKeys(searchString);
        $ (By.cssSelector("#search_send")).click();

        return new SearchResultPage(driver);
    }


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

    public ProfilePage clickProfile(WebDriver driver) {
        WebElement profilePageAnchor = Selenide.$("#head_nav > ul > li:nth-child(3) > a > div.name");

        profilePageAnchor.click();
        return new ProfilePage(driver);
    }

    public LoginPage clickLoginOption(WebDriver driver) {


        WebElement loginButtonAnchor = Selenide.$("#head_nav > ul > li:nth-child(3) > a");

        loginButtonAnchor.click();
        return new LoginPage(driver);
    }


    public FavoriteProductsPage clickFavoritesOption(WebDriver driver) {
        WebElement favoritesButtonAnchor = Selenide.$(byText("OBLÍBENÉ"));
        favoritesButtonAnchor.click();
        return new FavoriteProductsPage(driver);
    }

    public void wait10SecondsFor(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void subscribeToNewsletter(WebDriver driver, String email) {
        WebElement element = driver.findElement(By.cssSelector("#page-index > div.social-media > div:nth-child(2) > div > form > input[type=text]:nth-child(1)"));
        element.sendKeys(email);
        driver.findElement(By.cssSelector("#page-index > div.social-media > div:nth-child(2) > div > form > input.button")).click();
    }

    public void selectProductsSize(WebDriver driver, WebElement element) {
        element.click();
        driver.findElement(By.cssSelector("#variation_select_135033_1_2")).click();
        wait10SecondsFor(driver, driver.findElement(By.cssSelector("#buy")));
    }




public HomePage goToHomePage(WebDriver driver){
        WebElement homePageImage = $("a[href='https://www.metalshop.cz']");

        homePageImage.click();
        return new HomePage(driver);
}



}
