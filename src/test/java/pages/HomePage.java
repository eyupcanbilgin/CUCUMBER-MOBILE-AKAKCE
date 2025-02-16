package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

// KeyEvent importları:
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

public class HomePage {

    private final AppiumDriver<MobileElement> driver;
    private final WebDriverWait wait;

    // === Locators ===
    private final By guestModeButton = By.id("com.akakce.akakce:id/continueWithoutRegister");
    // İlk arama kutusu
    private final By searchTextView = By.id("com.akakce.akakce:id/searchTextView");
    // İkinci arama kutusu
    private final By secondSearchTextView = By.xpath("(//android.widget.EditText[@resource-id='com.akakce.akakce:id/searchTextView'])[2]");

    private final By filterButton = By.id("com.akakce.akakce:id/filterText");
    private final By bilgisayarDonanim = By.xpath("//android.widget.TextView[@text='Bilgisayar, Donanım']");
    private final By urunleriGorButton = By.id("com.akakce.akakce:id/applyFilterBtn");
    private final By sortAreaButton = By.id("com.akakce.akakce:id/sortArea");
    private final By enYuksekFiyat = By.xpath("(//android.widget.ImageView[@resource-id='com.akakce.akakce:id/sort_icon'])[3]");
    private final By uruneGitButton = By.id("com.akakce.akakce:id/qv_bottom_layout");
    private final By saticiyaGit = By.xpath("//android.widget.TextView[@text=\"Satıcıya Git\"]");

    // Ürün isimlerini içeren locator (benzersiz isimler için)
    private final By productNameLocator = By.id("com.akakce.akakce:id/name");

    public HomePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        // Örnek 10 saniyelik bekleme süresi
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * "Üye olmadan devam et" butonuna tıklar.
     */
    public void proceedAsGuest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(guestModeButton));
        driver.findElement(guestModeButton).click();
    }

    /**
     * İlk arama kutusuna tıklar.
     */
    public void tapSearchBar() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextView));
        driver.findElement(searchTextView).click();
    }

    /**
     * Açılan ekrandaki ikinci arama kutusuna tıklar.
     */
    public void tapSecondSearchBox() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondSearchTextView));
        driver.findElement(secondSearchTextView).click();
    }

    /**
     * İkinci arama kutusuna metin yazar.
     */
    public void typeSearchTextInSecondBox(String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(secondSearchTextView));
        MobileElement element = driver.findElement(secondSearchTextView);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Android klavyesinde ENTER tuşuna basar.
     */
    public void pressEnterKey() {
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void clickFilterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(filterButton)).click();
    }

    public void selectBilgisayarDonanim() {
        wait.until(ExpectedConditions.elementToBeClickable(bilgisayarDonanim)).click();
    }

    public void clickUrunleriGor() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(urunleriGorButton)).click();
    }

    public void clickSortButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortAreaButton)).click();
    }

    public void selectEnYuksekFiyat() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(enYuksekFiyat)).click();
    }

    /**
     * Listedeki n. benzersiz ürünü (isimlerine göre) bulup tıklar.
     * Bu metot, görünürdeki ürün isimlerini toplayıp, benzersiz olanları sayar.
     */
    public void selectNthProduct(int n) {
        // LinkedHashSet, eklenme sırasını korur.
        Set<String> uniqueProductNames = new LinkedHashSet<>();
        int iterations = 0;
        // Maksimum 10 iterasyon deneniyor, aksi halde sonsuz döngüden kaçınmak için.
        while (uniqueProductNames.size() < n && iterations < 10) {
            List<MobileElement> visibleNameElements = driver.findElements(productNameLocator);
            for (MobileElement nameElement : visibleNameElements) {
                String name = nameElement.getText().trim();
                if (!name.isEmpty()) {
                    uniqueProductNames.add(name);
                }
            }
            System.out.println("Iteration " + (iterations + 1) + " - Toplam benzersiz ürün sayısı: " + uniqueProductNames.size());
            if (uniqueProductNames.size() < n) {
                swipeUp();
            }
            iterations++;
        }
        System.out.println("Final toplam benzersiz ürün sayısı: " + uniqueProductNames.size());
        if (uniqueProductNames.size() >= n) {
            // LinkedHashSet'teki n'inci benzersiz ismi almak için:
            List<String> uniqueNamesList = new ArrayList<>(uniqueProductNames);
            String targetName = uniqueNamesList.get(n - 1);
            // Hedef ürünün bulunduğu elementi bulmak için text içeren bir xpath kullanıyoruz:
            MobileElement targetElement = driver.findElement(By.xpath("//*[contains(@text, '" + targetName + "')]"));
            targetElement.click();
        } else {
            throw new NoSuchElementException(
                    "Toplam benzersiz ürün sayısı " + uniqueProductNames.size() +
                            " ama " + n + ". ürünü bulamadık!"
            );
        }
    }

    /**
     * "Ürüne Git" butonuna tıklar.
     */
    public void clickUruneGit() {
        int maxSwipes = 5; // maksimum 5 defa kaydırmayı dene
        int swipeCount = 0;
        while (swipeCount < maxSwipes) {
            try {
                // Eğer buton görünürse tıkla ve metottan çık
                MobileElement element = driver.findElement(uruneGitButton);
                if (element.isDisplayed()) {
                    element.click();
                    return;
                }
            } catch (NoSuchElementException e) {
                // Buton bulunamadı, o zaman kaydırmaya devam et
            }
            swipeUp();
            swipeCount++;
        }
        // Buton bulunamazsa hata fırlat
        throw new NoSuchElementException("Ürüne git butonu bulunamadı!");
    }


    /**
     * "Satıcıya Git" butonunun görünür olduğunu doğrular.
     */
    public boolean isSaticiyaGitVisible() {
        try {
            // Eğer buton zaten görünüyorsa, swipeUp()'a gerek olmayabilir.
            // Direkt görünürlüğünü kontrol edelim:
            wait.until(ExpectedConditions.visibilityOfElementLocated(saticiyaGit));
            return driver.findElement(saticiyaGit).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Ekranı yukarı doğru kaydırır.
     * Bu metot, Appium'un "mobile: scrollGesture" komutunu kullanır.
     */
    private void swipeUp() {
        Dimension size = driver.manage().window().getSize();
        // Ekranın ortasındaki kaydırma bölgesini belirleyelim:
        int left = (int) (size.width * 0.1);             // Ekranın sol kenarından %10 içeride
        int top = (int) (size.height * 0.4);              // Ekranın üstünden %40
        int width = (int) (size.width * 0.8);             // Bölgenin genişliği: ekran genişliğinin %80'i
        int height = (int) (size.height * 0.2);           // Bölgenin yüksekliği: ekran yüksekliğinin %20'si

        // swipeGesture komutu için parametreler:
        HashMap<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("percent", 0.85);
        // Swipe yapılacak alanı belirtelim:
        params.put("left", left);
        params.put("top", top);
        params.put("width", width);
        params.put("height", height);

        driver.executeScript("mobile: scrollGesture", params);
    }
}
