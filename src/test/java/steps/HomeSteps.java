package steps;

import drivers.DriverFactory;
import io.cucumber.java.en.*;
import pages.HomePage;
import static org.junit.Assert.assertTrue;

/**
 * HomeSteps class defines the step definitions for the Mobile App Laptop Search and Filter feature.
 * Each step corresponds to an action performed in the mobile application.
 */
public class HomeSteps {

    private final HomePage homePage;

    public HomeSteps() {
        homePage = new HomePage(DriverFactory.getDriver());
    }

    @Given("I launch the application as a guest user")
    public void launchAppAsGuest() {
        homePage.proceedAsGuest();
    }

    @When("I enter \"Laptop\" into the search field and press Enter")
    public void enterSearchTextAndPressEnter() {
        homePage.tapSearchBar();
        homePage.tapSecondSearchBox();
        homePage.typeSearchTextInSecondBox("Laptop");
        homePage.pressEnterKey();
    }

    @And("I tap the filter button")
    public void tapFilterButton() {
        homePage.clickFilterButton();
    }

    @And("I select the \"Bilgisayar ve Donanım\" category")
    public void selectCategory() {
        homePage.selectBilgisayarDonanim();
    }

    @And("I tap the \"View Products\" button")
    public void tapViewProductsButton() {
        homePage.clickUrunleriGor();
    }

    @And("I tap the sort button")
    public void tapSortButton() {
        homePage.clickSortButton();
    }

    @And("I select the \"Highest Price\" option")
    public void selectHighestPriceOption() {
        homePage.selectEnYuksekFiyat();
    }

    @And("I tap on the {int}th unique product in the list")
    public void tapNthUniqueProduct(int index) {
        homePage.selectNthProduct(index);
    }

    @And("I tap the \"Go to Product\" button")
    public void tapGoToProductButton() {
        homePage.clickUruneGit();
    }

    @Then("I should see the \"Go to Seller\" button")
    public void verifyGoToSellerButton() {
        assertTrue("Go to Seller button is not visible", homePage.isSaticiyaGitVisible());
    }
}
