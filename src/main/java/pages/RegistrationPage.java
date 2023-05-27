package pages;

import org.junit.jupiter.api.Assertions;
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
    @FindBy(how = How.CSS, using = "#alert_success > strong")
    private WebElement pageTitle;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstNameInput));
    }

    public RegistrationPage fillRegistrationForm(String firstName,String surname, String email, String phone,
                                                 String street, String city, String password, String passwordV) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(surname);
        emailInput.sendKeys(email);
        phoneNumberInput.sendKeys(phone);
        addressInput.sendKeys(street);
        cityInput.sendKeys(city);
        passwordInput.sendKeys(password);
        passwordAgainInput.sendKeys(passwordV);
        personalInfoCheckbox.click();

        return this;
    }

    public RegistrationPage pageTitleText(String text) { // should be "Potvrzeno"
        Assertions.assertEquals(text, pageTitle.getText());
        return this;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
}
