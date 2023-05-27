package pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {


    private WebDriver driver;

    @FindBy(how = How.CSS,using = "#le")
    WebElement nameEmailField;
    @FindBy(how = How.CSS,using = "#login-password")
    WebElement passwordField;
    @FindBy(how = How.CSS,using = "#login > div > div:nth-child(1) > input\n")
    WebElement submitLoginButton;




    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://www.metalshop.cz/login/");

    }


    public void inputTextIntoNameEmailField(String stringToInput){

        nameEmailField.sendKeys(stringToInput);

    }
    public void inputTextIntoPWField(String stringToInput){

        passwordField.sendKeys(stringToInput);

    }
    public void submitLoginInfo(){

        submitLoginButton.click();
    }
}
