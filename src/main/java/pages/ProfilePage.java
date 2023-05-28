package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {

    private WebDriver driver;

    @FindBy(how = How.CSS,using = "#email")
    WebElement emailField;
    @FindBy(how = How.CSS,using = "#pass")
    WebElement passwordField;
    @FindBy(how = How.CSS,using = "#pass_ver")
    WebElement passwordVerificationField;



    
    @FindBy(how = How.CSS,using = "#name")
    WebElement nameField;

    @FindBy(how = How.CSS,using = "#surname")
    WebElement surnameField;


    @FindBy(how = How.CSS,using = "#phone")
    WebElement phoneField;




    @FindBy(how = How.CSS,using = "#street")
    WebElement streetField;
    @FindBy(how = How.CSS,using = "#city")
    WebElement cityField;
    @FindBy(how = How.CSS,using = "#zip_code")
    WebElement zipField;







    @FindBy(how = How.CSS,using = "#f_name")
    WebElement firmName;
    @FindBy(how = How.CSS,using = "#f_street")
    WebElement firmStreet;
    @FindBy(how = How.CSS,using = "#f_dic")
    WebElement firmDIC;
    @FindBy(how = How.CSS,using = "#f_zip_code")
    WebElement firmZIP;
    @FindBy(how = How.CSS,using = "input[value='Odešlete informace']")
    WebElement firmSubmit;




    @FindBy(how = How.CSS,using = "li[class='active'] a span")
    WebElement activeTabButton;



    @FindBy(how = How.CSS,using = "#page-account > section > div > section > div.panel-heading > ul > li:nth-child(1) > a")
    WebElement profileInfoButton;

    @FindBy(how = How.CSS,using = "#page-account > section > div > section > div.panel-heading > ul > li:nth-child(2) > a")
    WebElement ordersButton;


    @FindBy(how = How.CSS,using = "#page-account > section > div > section > div.panel-heading > ul > li:nth-child(3) > a")
    WebElement creditsButton;
    @FindBy(how = How.CSS,using = "#page-account > section > div > section > div.panel-heading > ul > li:nth-child(4) > a")
    WebElement profilePhotosButton;





}
