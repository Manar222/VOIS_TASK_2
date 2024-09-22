package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends BasePage{
    private final WebDriver driver;

    private final By searchDetailsTxt = By.xpath("//div[@class='trip--label active']/div");

    private final By selectSeatBtn = By.className("selectbutton") ;


    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }


    public void selectTrip()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(selectSeatBtn));

        clickButton(driver.findElement(selectSeatBtn));
    }

    public String getSearchDetailsTxt()
    {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchDetailsTxt));
        return  driver.findElement(searchDetailsTxt).getText() ;
    }





}
