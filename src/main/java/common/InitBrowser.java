package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class InitBrowser extends BasePage{
    public static WebDriver driver;

    public synchronized static WebDriver getDriver(){
        //Run by Maven command line
        String browser = System.getProperty("BROWSER");
        System.out.println("Browser name run by command line = " + browser);

        if (browser == null) {
            browser = System.getenv("BROWSER");
            System.out.println("Get browser name from OS = " + browser);
            if (browser == null) {
                browser = "chrome";
            }
        }
        //Check driver da duoc khoi tao hay chua
        if(driver == null) {
            try {
                //Happy path case
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    case "h_chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions hOption = new ChromeOptions();
                        hOption.addArguments("--headless");
                        hOption.addArguments("--window-size=1920x1080");
                        driver = new ChromeDriver(hOption);
                        break;
                    case "h_firefox":
                        WebDriverManager.firefoxdriver().setup();
                        FirefoxOptions option = new FirefoxOptions();
                        option.addArguments("---headless");
                        option.addArguments("--window-size=1920x1080");
                        driver = new FirefoxDriver(option);
                    default:
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                }
            }finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeBrowserDriver() {
       try{
           if(driver != null){
               getDriver().quit();
           }
       }catch (UnreachableBrowserException e){
           System.out.println("Cannot close the browser");
       }
    }
    private static class BrowserCleanup implements Runnable{

        @Override
        public void run() {
            closeBrowserDriver();
        }
    }
}
