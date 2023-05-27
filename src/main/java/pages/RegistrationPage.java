package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#name" )
    private WebElement firstNameInput;
    @FindBy(how = How.CSS, using = "#surname" )
    private WebElement lastNameInput;
    @FindBy(how = How.CSS, using = "#email" )
    private WebElement emailInput;
    @FindBy(how = How.CSS, using = "#phone" )
    private WebElement phoneNumberInput;
    @FindBy(how = How.CSS, using = "#street" )
    private WebElement addressInput;
    @FindBy(how = How.CSS, using = "#city" )
    private WebElement cityInput;
    @FindBy(how = How.CSS, using = "#pass" )
    private WebElement passwordInput;
    @FindBy(how = How.CSS, using = "#pass_ver" )
    private WebElement passwordAgainInput;
    @FindBy(how = How.CSS, using = "#privacy" )
    private WebElement personalInfoCheckbox;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
    }
}
