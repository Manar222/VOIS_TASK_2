package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class HomePage extends BasePage {
    private final WebDriver driver;

    private final By searchBtn = By.id("submitSearch");

    private final By fromCity = By.id("fromCity");
    private final By toCity = By.id("toCity");

    private final By calenderBtn = By.id("imgDepartDate");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void searchForAvailableTickets(String departureCity, String destinationCity, String departureDate) throws InterruptedException {

        WebElement fromCityDropdown = driver.findElement(fromCity);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].style.display = 'block'", fromCityDropdown);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(fromCityDropdown));

        selectElementByTxt(fromCityDropdown, departureCity);

        WebElement toCityDropdown = driver.findElement(toCity);

        js.executeScript("arguments[0].style.display = 'block'", toCityDropdown);

        wait.until(ExpectedConditions.elementToBeClickable(toCityDropdown));

        selectElementByTxt(toCityDropdown, destinationCity);

        clickButton(driver.findElement(calenderBtn));

        new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement arrivalDateElement = wait.until(ExpectedConditions.elementToBeClickable(getDynamicArrivalDateSelector(departureDate)));

        clickButton(arrivalDateElement);

        WebElement searchElementBtn = wait.until(ExpectedConditions.elementToBeClickable(searchBtn));

        clickButton(searchElementBtn);

    }


    private By getDynamicArrivalDateSelector(String departureDate) {

        String[] dateParts = departureDate.split("-");

        String day = dateParts[2];

        String month = String.valueOf(Integer.parseInt(dateParts[1]) - 1);

        String year = dateParts[0];

        return By.xpath("//td[@data-month='" + month + "' and @data-year='" + year + "']/a[text()='" + day + "']");
    }


}
