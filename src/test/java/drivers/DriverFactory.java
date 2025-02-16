package drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * DriverFactory is responsible for creating and managing the AppiumDriver instance.
 *
 * Bu sınıf, herhangi bir bağlı Android cihazda testlerin çalışabilmesi için gerekli
 * temel DesiredCapabilities ayarlarını sabit olarak tanımlar.
 * AppiumDriver tekil (singleton) olarak oluşturulur.
 */
public class DriverFactory {

    // Singleton instance of the AppiumDriver
    private static AppiumDriver driver;

    /**
     * Returns the singleton AppiumDriver instance.
     * If the driver is not yet created, it calls createDriver() to initialize it.
     *
     * @return the AppiumDriver instance.
     */
    public static AppiumDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    /**
     * Creates and initializes the AppiumDriver with fixed desired capabilities.
     *
     * Bu metot, belirli sabit ayarlarla AppiumDriver'ı başlatır.
     * "platformName" olarak "Android", "automationName" olarak "UiAutomator2" kullanılır.
     * Ayrıca, örnek olarak appPackage ve appActivity ayarları sabit değerlerle belirlenmiştir.
     * Gerçek uygulama için bu değerleri doğru bilgilerle güncellemeniz gerekir.
     *
     * @throws RuntimeException if the driver creation fails.
     */
    private static void createDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            // Platform ve otomasyon framework ayarları
            caps.setCapability("platformName", "Android");
            caps.setCapability("automationName", "UiAutomator2");

            // Uygulama paket ve aktivite ayarları (örnek değerler, gerçek uygulama için güncellenmeli)
            caps.setCapability("appPackage", "com.akakce.akakce");
            caps.setCapability("appActivity", "com.akakce.akakce.ui.splash.SplashActivity");

            // Ekstra capabilities eklenebilir
            caps.setCapability("autoGrantPermissions", true);

            // Appium server'a bağlantı sağlanır (yerel sunucu: 127.0.0.1:4723)
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Appium driver: " + e.getMessage());
        }
    }

    /**
     * Quits the AppiumDriver and resets the driver instance.
     *
     * Bu metot, mevcut AppiumDriver'ı kapatır ve bellekteki referansı sıfırlar.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
