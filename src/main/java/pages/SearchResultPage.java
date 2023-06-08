package pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultPage {

    private WebDriver driver;

    // Create a constructor to initialize the web elements
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public ProductPage findAndClickProduct(int index,WebDriver driver) throws Exception {
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

        return new ProductPage(driver);
    }


    /**
     * if pageNumberToGoTo is -1, go to the last page.
     * @param pageNumberToGoTo
     */
    public void goToPageNumber(int pageNumberToGoTo){

        boolean goToLastPage = false;


        ElementsCollection pageLinks = $$("a.page-link");


        if(pageNumberToGoTo==-1){
            goToLastPage=true;
        }

        WebElement lastPageContainer = null;
        int lastPageNumber = -1;
        // Iterate over the found elements and retrieve the text within the link
        for (WebElement link : pageLinks) {
            int containerPageNumber = Integer.valueOf(link.getText());


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

    public void clickPriceOption( ){

        WebElement priceOptionButton = $("#faset_filter_price");

        priceOptionButton.click();

    }
    public void clickSexOption( ){

        WebElement sexButton = $("#faset_filter_gender");

        sexButton.click();

    }
    public void clickColorOption( ){

        WebElement colorButton = $("#faset_filter_colors");

        colorButton.click();

    }
    public void clickBrandOption( ){

        WebElement brandButton = $("#faset_filter_brands");

        brandButton.click();

    }
    public void clickBandOption( ){

        WebElement bandButton = $("#faset_filter_bands");

        bandButton.click();

    }

    public void clickSizeOption( ){

        WebElement sizeButton = $("#faset_filter_sizes");

        sizeButton.click();

    }

    public void clickStorageOption( ){

        WebElement storageButton = $("#faset_filter_storage");

        storageButton.click();

    }

    public void clickStyleOption( ){

        WebElement styleOptionButton = $("#faset_filter_styles");

        styleOptionButton.click();

    }


    public void setPriceRangeMin(double minNumber){
        WebElement priceRangeMin = $("#price-range-start");

        priceRangeMin.clear();
        String minNumberString = String.valueOf(minNumber);
        priceRangeMin.click();
        priceRangeMin.sendKeys(minNumberString);

    }
    public void setPriceRangeMax( double maxNumber){


        WebElement priceRangeMax = $("#price-range-stop");


        priceRangeMax.clear();

        String maxNumberString = String.valueOf(maxNumber);


        priceRangeMax.click();
        priceRangeMax.sendKeys(maxNumberString);




    }




    public void setPriceRangeMinAndMaxAsString(String minNumber, String maxNumber){


        WebElement priceRangeMax = $("#price-range-stop");

        WebElement priceRangeMin = $("#price-range-start");


        priceRangeMax.clear();
        priceRangeMin.clear();

        priceRangeMax.click();
        priceRangeMax.sendKeys(maxNumber);
        priceRangeMin.sendKeys(minNumber);




    }


    public void selectFilterElementViaIndex(int filterIndexToClick){
        ElementsCollection elements = $$(".filter-container > *");

        if(elements.size()==0){
            return;
        }

        elements.get(filterIndexToClick).click();
    }





}
