package steps;

import drivers.DriverFactory;
import io.cucumber.java.en.*;
import pages.HomePage;

import static org.junit.Assert.assertTrue;

public class HomeSteps {

    private final HomePage homePage;

    public HomeSteps() {
        homePage = new HomePage(DriverFactory.getDriver());
    }

    @Given("uygulamayı konuk kullanıcı olarak açtım")
    public void openAppAsGuestUser() {
        homePage.proceedAsGuest();
    }

    @When("Laptop yazar ve klavyeden enter basarım")
    public void searchLaptopAndPressEnter() {
        homePage.tapSearchBar();
        homePage.tapSecondSearchBox();
        homePage.typeSearchTextInSecondBox("Laptop");
        homePage.pressEnterKey();
    }

    @And("filtre butonuna tıklarım")
    public void clickFilterButton() {
        homePage.clickFilterButton();
    }

    @And("bilgisayar donanım alt kategorisini seçerim")
    public void selectBilgisayarDonanimCategory() {
        homePage.selectBilgisayarDonanim();
    }

    @And("ürünleri gör butonuna tıklarım")
    public void clickUrunleriGor() {
        homePage.clickUrunleriGor();
    }

    @And("sıralama butonuna tıklarım")
    public void clickSortButton() {
        homePage.clickSortButton();
    }

    @And("en yüksek fiyatı seçerim")
    public void selectHighestPrice() {
        homePage.selectEnYuksekFiyat();
    }

    @And("{int}. ürüne tıklarım")
    public void selectNthProduct(int index) {
        homePage.selectNthProduct(index);
    }

    @And("ürüne git butonuna tıklarım")
    public void clickUruneGit() {
        homePage.clickUruneGit();
    }

    @Then("satıcıya git butonunu görürüm")
    public void verifySaticiyaGitButtonIsVisible() {
        assertTrue("Satıcıya Git butonu görünür değil!", homePage.isSaticiyaGitVisible());
    }
}
