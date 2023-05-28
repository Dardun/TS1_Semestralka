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



    @FindBy(how = How.CSS,using = "a[href='/logout']")
    WebElement logoutButton;




    @FindBy(how = How.CSS,using = "#pkms")
    WebElement wantToRecieveNewsletterCB;




    @FindBy(how = How.CSS,using = "#f_name")
    WebElement firmName;
    @FindBy(how = How.CSS,using = "#f_street")
    WebElement firmStreet;
    @FindBy(how = How.CSS,using = "#f_dic")
    WebElement firmDIC;
    @FindBy(how = How.CSS,using = "#f_zip_code")
    WebElement firmZIP;
    @FindBy(how = How.CSS,using = "#f_ico")
    WebElement firmICO;
    @FindBy(how = How.CSS,using = "#f_city")
    WebElement firmCity;
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






    public void goToProfilePhotosTab(){

        profilePhotosButton.click();
    }
    public void goToCreditsTab(){
        creditsButton.click();
    }
    public void goToProfileTab(){

        profileInfoButton.click();
    }
    public void goToOrdersTab(){

        ordersButton.click();
    }
    public void logOut(){
        logoutButton.click();

    }
    public void clickActiveTabButton(){

        //SHOULD do nothing
        activeTabButton.click();
    }
    public void fillOutFirmAndSubmit(String name, String street, String ico, String city,
                                     String dic, String zip){

        firmName.sendKeys(name);
        firmStreet.sendKeys(street);
        firmICO.sendKeys(ico);
        firmCity.sendKeys(city);
        firmDIC.sendKeys(dic);
        firmZIP.sendKeys(zip);


        firmSubmit.click();
    }
    public void clickNewsletterCB(){
        wantToRecieveNewsletterCB.click();
    }
    public void changeName(String name){

        nameField.sendKeys(name);
    }
    public void changeSurname(String surname){

        surnameField.sendKeys(surname);
    }
    public void changeEmail(String email){

        emailField.sendKeys(email);
    }
    public void changePassword(String pw){

        passwordField.sendKeys(pw);
        passwordVerificationField.sendKeys(pw);
    }
    public void changeStreetAndHouseNum(String streetAndHouseNum){

        streetField.sendKeys(streetAndHouseNum);
    }
    public void changeCity(String city){
        cityField.sendKeys(city);

    }
    public void changeZIP(String zip){

        zipField.sendKeys(zip);
    }
    public void changePhone(String phone){

        phoneField.sendKeys(phone);
    }



}
