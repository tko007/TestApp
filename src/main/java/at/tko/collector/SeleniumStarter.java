package at.tko.collector;

import at.tko.collector.util.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SeleniumStarter {
    private static final String BET_AT_HOME =
            "https://www.bet-at-home.com/de/sport";

    private WebDriver driver = null;

    private static DesiredCapabilities createCapabilities(){
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(
                ChromeOptions.CAPABILITY, setOptions());
        desiredCapabilities.setCapability(
                CapabilityType.LOGGING_PREFS, setLogLevels(Level.OFF));

        return desiredCapabilities;
    }

    private static ChromeOptions setOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions
                .addArguments("--lang=de");

        return chromeOptions;
    }

    private static LoggingPreferences setLogLevels(Level aLevel){
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, aLevel);
        logs.enable(LogType.CLIENT, aLevel);
        logs.enable(LogType.PERFORMANCE, aLevel);
        logs.enable(LogType.PROFILER, aLevel);
        logs.enable(LogType.DRIVER, aLevel);
        logs.enable(LogType.SERVER, aLevel);

        return logs;
    }

    public SeleniumStarter(Locale locale) throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://192.168.0.78:4444/wd/hub"),
                createCapabilities());

        // set driver options
        driver.manage()
                .timeouts()
                .pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.get(BET_AT_HOME);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
