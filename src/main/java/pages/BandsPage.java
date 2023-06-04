package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BandsPage {

    private WebDriver driver;

    public BandsPage(WebDriver driver) {
        this.driver = driver;
    }



    public SearchResultPage findAndClickBand(WebDriver driver, String searchText) {
        List<WebElement> itemList = driver.findElements(By.className("items"));

        for (WebElement item : itemList) {
            List<WebElement> subElements = item.findElements(By.xpath(".//*"));

            for (WebElement subElement : subElements) {
                if (subElement.getText().equals(searchText)) {
                    subElement.click();
                    return new SearchResultPage(driver);
                }
            }
        }

        return null;
    }
}
