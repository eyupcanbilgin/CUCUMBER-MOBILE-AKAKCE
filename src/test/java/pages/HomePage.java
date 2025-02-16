package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

/**
 * HomePage encapsulates the interactions on the home screen and filter/sort screens.
 * It includes methods to:
 * - Continue as a guest.
 * - Interact with the search boxes.
 * - Apply filters and sort options.
 * - Select a specific product by counting unique product names.
 * - Navigate to the product details ("Go to Product") and verify the "Go to Seller" button.
 */
public class HomePage {

    private final AppiumDriver<MobileElement> driver;
    private final WebDriverWait wait;

    // Locators
    private final By guestModeButton = By.id("com.akakce.akakce:id/continueWithoutRegister");
    private final By searchTextView = By.id("com.akakce.akakce:id/searchTextView");
    private final By secondSearchTextView = By.xpath("(//android.widget.EditText[@resource-id='com.akakce.akakce:id/searchTextView'])[2]");
    private final By filterButton = By.id("com.akakce.akakce:id/filterText");
    private final By bilgisayarDonanim = By.xpath("//android.widget.TextView[@text='Bilgisayar, Donanım']");
    private final By urunleriGorButton = By.id("com.akakce.akakce:id/applyFilterBtn");
    private final By sortAreaButton = By.id("com.akakce.akakce:id/sortArea");
    private final By enYuksekFiyat = By.xpath("(//android.widget.ImageView[@resource-id='com.akakce.akakce:id/sort_icon'])[3]");
    private final By uruneGitButton = By.id("com.akakce.akakce:id/qv_bottom_layout");
    private final By saticiyaGit = By.xpath("//android.widget.TextView[@text=\"Satıcıya Git\"]");
    private final By productNameLocator = By.id("com.akakce.akakce:id/name");

    public HomePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Clicks the "Continue as Guest" button.
     */
    public void proceedAsGuest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestModeButton));
        driver.findElement(guestModeButton).click();
    }

    /**
     * Clicks the first search box.
     */
    public void tapSearchBar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextView));
        driver.findElement(searchTextView).click();
    }

    /**
     * Clicks the second search box.
     */
    public void tapSecondSearchBox() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondSearchTextView));
        driver.findElement(secondSearchTextView).click();
    }

    /**
     * Enters text into the second search box.
     *
     * @param text the text to enter.
     */
    public void typeSearchTextInSecondBox(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondSearchTextView));
        MobileElement element = driver.findElement(secondSearchTextView);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Presses the ENTER key on the Android keyboard.
     */
    public void pressEnterKey() {
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    /**
     * Clicks the filter button.
     */
    public void clickFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
    }

    /**
     * Selects the "Bilgisayar, Donanım" category.
     */
    public void selectBilgisayarDonanim() {
        wait.until(ExpectedConditions.elementToBeClickable(bilgisayarDonanim)).click();
    }

    /**
     * Clicks the "View Products" button.
     */
    public void clickUrunleriGor() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(urunleriGorButton)).click();
    }

    /**
     * Clicks the sort button.
     */
    public void clickSortButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortAreaButton)).click();
    }

    /**
     * Selects the "Highest Price" option.
     */
    public void selectEnYuksekFiyat() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enYuksekFiyat)).click();
    }

    /**
     * Selects the nth unique product based on its name.
     * The method collects visible product names until it has at least n unique names,
     * swiping up between iterations. Then it clicks the product corresponding to the nth unique name.
     *
     * @param n the position (1-indexed) of the unique product to select.
     * @throws NoSuchElementException if the nth unique product cannot be found.
     */
    public void selectNthProduct(int n) {
        Set<String> uniqueProductNames = new LinkedHashSet<>();
        int iterations = 0;
        while (uniqueProductNames.size() < n && iterations < 10) {
            List<MobileElement> visibleNameElements = driver.findElements(productNameLocator);
            for (MobileElement nameElement : visibleNameElements) {
                String name = nameElement.getText().trim();
                if (!name.isEmpty()) {
                    uniqueProductNames.add(name);
                }
            }
            System.out.println("Iteration " + (iterations + 1) + " - Unique product count: " + uniqueProductNames.size());
            if (uniqueProductNames.size() < n) {
                swipeUp();
            }
            iterations++;
        }
        System.out.println("Final unique product count: " + uniqueProductNames.size());
        if (uniqueProductNames.size() >= n) {
            List<String> uniqueNamesList = new ArrayList<>(uniqueProductNames);
            String targetName = uniqueNamesList.get(n - 1);
            MobileElement targetElement = driver.findElement(By.xpath("//*[contains(@text, '" + targetName + "')]"));
            targetElement.click();
        } else {
            throw new NoSuchElementException(
                    "Unique product count is " + uniqueProductNames.size() +
                            " but the " + n + "th product could not be found!"
            );
        }
    }

    /**
     * Clicks the "Go to Product" button.
     * This method attempts to find and click the button, swiping up if necessary.
     *
     * @throws NoSuchElementException if the button is not found after multiple swipes.
     */
    public void clickUruneGit() {
        int maxSwipes = 5;
        int swipeCount = 0;
        while (swipeCount < maxSwipes) {
            try {
                MobileElement element = driver.findElement(uruneGitButton);
                if (element.isDisplayed()) {
                    element.click();
                    return;
                }
            } catch (NoSuchElementException e) {
                // Continue swiping if not found.
            }
            swipeUp();
            swipeCount++;
        }
        throw new NoSuchElementException("The 'Go to Product' button could not be found!");
    }

    /**
     * Checks whether the "Go to Seller" button is visible.
     *
     * @return true if the button is visible, false otherwise.
     */
    public boolean isSaticiyaGitVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(saticiyaGit));
            return driver.findElement(saticiyaGit).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Scrolls the screen upward using the mobile: scrollGesture command.
     * The scroll is performed from the central region of the screen.
     */
    private void swipeUp() {
        Dimension size = driver.manage().window().getSize();
        int left = (int) (size.width * 0.1);
        int top = (int) (size.height * 0.4);
        int width = (int) (size.width * 0.8);
        int height = (int) (size.height * 0.2);

        HashMap<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("percent", 0.85);
        params.put("left", left);
        params.put("top", top);
        params.put("width", width);
        params.put("height", height);

        driver.executeScript("mobile: scrollGesture", params);
    }
}
