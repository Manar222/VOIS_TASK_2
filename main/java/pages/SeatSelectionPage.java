package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeatSelectionPage extends BasePage {

    private final WebDriver driver;

    private final By seatPageAssertion = By.className("instructionmsg");

    private final By boardingAndDroppingElements = By.cssSelector(".point-inp");

    private final By selectSeatBtn = By.xpath("//div[@class='seatlook' and not(contains(@class, 'not-allowed'))]");

    private final By boardingAndDroppingAvailableTrips = By.className("col-time");

    private final By passengerDetailsBtn = By.className("btnPassDetails");

    private final By mobileNumberTxt = By.name("mobileNo");

    private final By emailTxt = By.name("email");

    private final By completePassengerDetailsBtn = By.cssSelector(".navswitchbtn");

    private final By passengerNameTxt = By.name("paxName[0]");

    private final By genderSelect = By.name("paxGender[0]");
    private final By ageTxt = By.name("paxAge[0]");

    private final By concessionSelect = By.name("paxConcessionType[0]");


    private final By idSelect = By.name("paxIDCardType[0]");

    private final By idCardNumTxt = By.name("paxIDCardNo[0]");

    private final By checkoutBtn = By.xpath("//div[normalize-space()='Proceed to Checkout']") ;

    private final By amountToPayAssertion = By.xpath("//div[@class='final--amount--paid']") ;

    public SeatSelectionPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSeatPageAssertionTxt() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(seatPageAssertion));
        return driver.findElement(seatPageAssertion).getText();
    }

    public void selectBoardingPoint() {
        List<WebElement> points = driver.findElements(boardingAndDroppingElements);

        WebElement boardingPoint = points.getFirst();

        boardingPoint.click();

        List<WebElement> availableBoardingTrips = driver.findElements(boardingAndDroppingAvailableTrips);
        availableBoardingTrips.getFirst().click();


    }

    public void selectDroppingPoint() {
        List<WebElement> points = driver.findElements(boardingAndDroppingElements);

        WebElement boardingPoint = points.get(1);

        boardingPoint.click();

        List<WebElement> availableDroppingTrips = driver.findElements(boardingAndDroppingAvailableTrips);
        availableDroppingTrips.getFirst().click();
    }

    public void selectAvailableSeat() {

        WebElement availableSeat = driver.findElement(selectSeatBtn);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(availableSeat));

        clickButton(availableSeat);
    }

    public void navigateToPassengerDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(passengerDetailsBtn));

        clickButton(driver.findElement(passengerDetailsBtn));
    }


    public void enterCustomerDetails(String mobileNum, String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(mobileNumberTxt));


        setInputText(driver.findElement(mobileNumberTxt), mobileNum);
        wait.until(ExpectedConditions.elementToBeClickable(emailTxt));

        setInputText(driver.findElement(emailTxt), email);
        wait.until(ExpectedConditions.elementToBeClickable(completePassengerDetailsBtn));


        clickButton(driver.findElement(completePassengerDetailsBtn));

    }

    public void enterPassengerDetails(String name, String gender, String age, String concession, String idCard, String idTxt) {

        setInputText(driver.findElement(passengerNameTxt), name);

        clickButton(driver.findElement(genderSelect));

        By genderSelector = By.xpath("//div[contains(text(),'" + gender + "')]");

        clickButton(driver.findElement(genderSelector));

        setInputText(driver.findElement(ageTxt), age);

        clickButton(driver.findElement(concessionSelect));
        By concessionSelector = By.xpath("//div[contains(text(),'" + concession + "')]");

        clickButton(driver.findElement(concessionSelector));

        clickButton(driver.findElement(idSelect));

        By idCardSelector = By.xpath("//div[contains(text(),'" + idCard + "')]");

        clickButton(driver.findElement(idCardSelector));

        setInputText(driver.findElement(idCardNumTxt), idTxt);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
        clickButton(driver.findElement(checkoutBtn));


    }

    public boolean payButtonIsDisplayedAssertion()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountToPayAssertion));
         return  driver.findElement(amountToPayAssertion).isDisplayed() ;
    }

    public boolean passengerDetailsBtnIsDisplayed()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerDetailsBtn));
        return  driver.findElement(passengerDetailsBtn).isDisplayed() ;
    }

    public boolean nameTxtFieldIsDisplayed()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passengerNameTxt));
        return  driver.findElement(passengerNameTxt).isDisplayed() ;
    }

}
