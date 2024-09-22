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

public class SeatSelectionTest extends TestBase {

    HomePage homeObject;
    SearchResultsPage searchResultsObject;
    SeatSelectionPage seatSelectionObject;

    String fromCity;
    String toCity;
    String departureDate;
    String mobileNumber;
    String email;
    String name;
    String gender;
    String age;
    String concession;
    String idCard;
    String idNumber;
    String seatPageMessage;

    @BeforeClass
    public void setupTestData() throws IOException, ParseException {
        fromCity = JsonReader.jsonData("TripData", "fromCity");
        toCity = JsonReader.jsonData("TripData", "toCity");
        departureDate = JsonReader.jsonData("TripData", "departureDate");
        mobileNumber = JsonReader.jsonData("CustomerDetails", "mobileNumber");
        email = JsonReader.jsonData("CustomerDetails", "email");
        name = JsonReader.jsonData("CustomerDetails", "name");
        gender = JsonReader.jsonData("CustomerDetails", "gender");
        age = JsonReader.jsonData("CustomerDetails", "age");
        concession = JsonReader.jsonData("CustomerDetails", "concession");
        idCard = JsonReader.jsonData("CustomerDetails", "idCard");
        idNumber = JsonReader.jsonData("CustomerDetails", "idNumber");
        seatPageMessage = JsonReader.jsonData("Assertions", "seatPageMessage");
    }

    @BeforeMethod
    public void setUp() {
        homeObject = new HomePage(driver);
        searchResultsObject = new SearchResultsPage(driver);
        seatSelectionObject = new SeatSelectionPage(driver);
    }




    @Story("Select Boarding Point")
    @Description("User selects a boarding point on the seat selection page.")
    @Test(description = "User can select boarding point")
    public void userCanSelectBoardingPoint() throws InterruptedException {
        homeObject.searchForAvailableTickets(fromCity, toCity, departureDate);
        searchResultsObject.selectTrip();
        Assert.assertTrue(seatSelectionObject.getSeatPageAssertionTxt().contains(seatPageMessage));
        seatSelectionObject.selectBoardingPoint();
    }



    @Story("Select Dropping Point")
    @Description("User selects a dropping point on the seat selection page.")
    @Test(description = "User can select dropping point")
    public void userCanSelectDroppingPoint() throws InterruptedException {
        homeObject.searchForAvailableTickets(fromCity, toCity, departureDate);
        searchResultsObject.selectTrip();
        Assert.assertTrue(seatSelectionObject.getSeatPageAssertionTxt().contains(seatPageMessage));
        seatSelectionObject.selectDroppingPoint();
    }



    @Story("Select Seat")
    @Description("User selects an available seat on the seat selection page.")
    @Test(description = "User can select seat")
    public void userCanSelectSeat() throws InterruptedException {
        homeObject.searchForAvailableTickets(fromCity, toCity, departureDate);
        searchResultsObject.selectTrip();
        Assert.assertTrue(seatSelectionObject.getSeatPageAssertionTxt().contains(seatPageMessage));
        seatSelectionObject.selectAvailableSeat();
    }


    @Story("Fill Trip Information")
    @Description("User fills trip information including boarding point, dropping point, and seat selection.")
    @Test(description = "User can fill trip information")
    public void userCanFillTripInfo() throws InterruptedException {
        homeObject.searchForAvailableTickets(fromCity, toCity, departureDate);
        searchResultsObject.selectTrip();
        Assert.assertTrue(seatSelectionObject.getSeatPageAssertionTxt().contains(seatPageMessage));
        seatSelectionObject.selectBoardingPoint();
        seatSelectionObject.selectDroppingPoint();
        seatSelectionObject.selectAvailableSeat();
        Assert.assertTrue(seatSelectionObject.passengerDetailsBtnIsDisplayed());
        seatSelectionObject.navigateToPassengerDetails();
    }



    @Story("Enter Customer Details")
    @Description("User enters personal details like mobile number and email.")
    @Test(description = "User can enter personal details")
    public void userCanEnterHisDetails() throws InterruptedException {
       userCanFillTripInfo();
        seatSelectionObject.enterCustomerDetails(mobileNumber, email);
        Assert.assertTrue(seatSelectionObject.nameTxtFieldIsDisplayed());
    }






    @Story("Fill Checkout Details")
    @Description("User fills checkout details including passenger information and verifies the pay button.")
    @Test(description = "User can fill checkout details")
    public void userCanFillPassengerDetails() throws InterruptedException {
        userCanEnterHisDetails() ;
        seatSelectionObject.enterPassengerDetails(name, gender, age, concession, idCard, idNumber);
        Assert.assertTrue(seatSelectionObject.payButtonIsDisplayedAssertion());
    }
}
