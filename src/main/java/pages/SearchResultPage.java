package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage {

    private WebDriver driver;

    // Create a constructor to initialize the web elements
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void findAndClickProduct(int index) throws Exception {
        List<WebElement> productItems = driver.findElements(By.className("product-item"));

        if (index<0){
            throw new Exception("faulty request!");
        }


        int productVariationNumber = productItems.size();

        if(index+1>productVariationNumber){
            index = productVariationNumber+1;
        }


        WebElement variationIndexElement = productItems.get(index);

        variationIndexElement.click();
    }


    /**
     * if pageNumberToGoTo is -1, go to the last page.
     * @param pageNumberToGoTo
     */
    public void goToPageNumber(int pageNumberToGoTo){

        boolean goToLastPage = false;

        List<WebElement> pageLinks = driver.findElements(By.cssSelector("a.page-link"));


        if(pageNumberToGoTo==-1){
            goToLastPage=true;
        }

        WebElement lastPageContainer = null;
        int lastPageNumber = -1;
        // Iterate over the found elements and retrieve the text within the link
        for (WebElement link : pageLinks) {
            int containerPageNumber = Integer.getInteger(link.getText());


            if(goToLastPage){

                if (containerPageNumber > lastPageNumber){
                    lastPageNumber = containerPageNumber;
                }

                continue;
            }


            if(pageNumberToGoTo == containerPageNumber){

                link.click();
                return;
            }

        }



        lastPageContainer.click();



    }




}
