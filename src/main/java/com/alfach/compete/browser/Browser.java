package com.alfach.compete.browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by User on 11/3/2016.
 */
public class Browser {

    private WebDriver webDriver;

    public Browser(String browserName) {
        initBrowser(Browsers.valueOf(browserName));
        webDriver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return this.webDriver;
    }

    private void initBrowser(Browsers browserName) {
        switch (browserName) {
            case CHROME:
                initChromeDriverParams();
                webDriver = new ChromeDriver();
                break;
            case FIREFOX:
                webDriver = new FirefoxDriver();
                break;
            case IE:
                webDriver = new InternetExplorerDriver();
                break;
            case EDGE:
                webDriver = new EdgeDriver();
                break;
            default:
                webDriver = new FirefoxDriver();
        }
    }

    private void initChromeDriverParams(){
        String chromeDriverPath = this.getClass().getClassLoader().getResource("drivers/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    public void close(){
        webDriver.quit();
    }

    public void clearCookies() {
        webDriver.manage().deleteAllCookies();
    }

}
