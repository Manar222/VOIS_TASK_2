package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.Helper;

import java.io.IOException;

public class TestBase {

    public static WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void startDriver(@Optional("chrome") String browserName) throws InterruptedException {

        if (browserName.equalsIgnoreCase("chrome")) {

             driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
         Dimension windowSize = new Dimension(1024, 768);

        driver.manage().window().setSize(windowSize);

        driver.get("https://ksrtc.in/oprs-web/guest/home.do?h=1");



    }


    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("test failed: " + result.getName());
            System.out.println("taking screenshot");
            Helper.takeScreenShot(driver, result.getName());
        }
        driver.close();
    }




}