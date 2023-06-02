package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FavoriteProductsPage {

    private WebDriver driver;

    public FavoriteProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    public boolean areFavoritesEmpty(){
        List<WebElement> elements = driver.findElements(By.className("product-item-info"));

        if(elements.size()>0){
            return false;
        }

        return true;
    }


    public void clickProductInFavorites(int index){
        List<WebElement> elements = driver.findElements(By.className("product-item-info"));

        WebElement elementToFind = null;


        int i = 0;

        for (WebElement element : elements) {
            if(i==index){
                elementToFind = element;
            }

            i++;
        }

        elementToFind.click();

    }


    public void addProductInFavoritesToCart(int index){

        List<WebElement> elements = driver.findElements(By.className("product-item-buy"));

        WebElement elementToFind = null;
        int i = 0;

        for (WebElement element : elements) {
            if(i==index){
                elementToFind = element;
            }

            i++;
        }

        elementToFind.click();
    }



}
