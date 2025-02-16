package drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * DriverFactory is responsible for creating and managing the AppiumDriver instance.
 * This class defines the basic DesiredCapabilities required to run tests on any connected Android device.
 * The AppiumDriver is implemented as a singleton.
 */
public class DriverFactory {

    // Singleton instance of the AppiumDriver
    private static AppiumDriver driver;

    /**
     * Returns the singleton AppiumDriver instance.
     * If the driver has not been created, it initializes it using createDriver().
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
     * <p>
     * The following capabilities are set:
     * - platformName: "Android"
     * - automationName: "UiAutomator2"
     * - appPackage and appActivity are set to sample values; update these for your application.
     * - autoGrantPermissions: true
     * </p>
     *
     * @throws RuntimeException if driver creation fails.
     */
    private static void createDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("appPackage", "com.akakce.akakce");
            caps.setCapability("appActivity", "com.akakce.akakce.ui.splash.SplashActivity");
            caps.setCapability("autoGrantPermissions", true);

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Appium driver: " + e.getMessage());
        }
    }

    /**
     * Quits the AppiumDriver and resets the driver instance.
     * This method shuts down the current AppiumDriver and clears its reference.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
