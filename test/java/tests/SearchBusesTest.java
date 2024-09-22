package tests;

import data.JsonReader;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.SeatSelectionPage;

import java.io.IOException;

public class SearchBusesTest extends TestBase {
    HomePage homeObject;
    SearchResultsPage searchResultsObject;
    SeatSelectionPage seatSelectionObject;

    String fromCity;
    String toCity;
    String departureDate;
    String searchResultMessage;
    String seatPageMessage;

    @BeforeClass
    public void setupTestData() throws IOException, ParseException {
        fromCity = JsonReader.jsonData("TripData", "fromCity");
        toCity = JsonReader.jsonData("TripData", "toCity");
        departureDate = JsonReader.jsonData("TripData", "departureDate");
        searchResultMessage = JsonReader.jsonData("Assertions", "searchResultMessage");
        seatPageMessage = JsonReader.jsonData("Assertions", "seatPageMessage");
    }

    @BeforeMethod
    public void setUp() {
        homeObject = new HomePage(driver);
        searchResultsObject = new SearchResultsPage(driver);
        seatSelectionObject = new SeatSelectionPage(driver);
    }

    @Story("Search for Tickets")
    @Description("User searches for available tickets from one city to another on a specific date.")
    @Test(description = "User can search for available tickets")
    public void userCanSearchForTicket() throws InterruptedException {
        homeObject.searchForAvailableTickets(fromCity, toCity, departureDate);
        Assert.assertTrue(searchResultsObject.getSearchDetailsTxt().contains(searchResultMessage));
    }

    @Story("Select Trip")
    @Description("User selects a trip from the search results page.")
    @Test(description = "User can select a trip")
    public void userCanSelectTrip() throws InterruptedException {
        userCanSearchForTicket() ;
        searchResultsObject.selectTrip();
        Assert.assertTrue(seatSelectionObject.getSeatPageAssertionTxt().contains(seatPageMessage));
    }
}
