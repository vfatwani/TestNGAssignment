package commonLibs.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class CommonDriver {
    private WebDriver driver;
    private String currentWorkingDirectory;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public int getElementDetectionTimeout() {
        return elementDetectionTimeout;
    }

    public void setElementDetectionTimeout(int elementDetectionTimeout) {
        this.elementDetectionTimeout = elementDetectionTimeout;
    }

    private int pageLoadTimeout;
    private int elementDetectionTimeout;

    public CommonDriver(String browserType) throws Exception {
              pageLoadTimeout=10;
              elementDetectionTimeout=5;
        currentWorkingDirectory = System.getProperty("user.dir");
        if(browserType.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",currentWorkingDirectory+"/drivers/chromedriver");
            driver=new ChromeDriver();
        }else{
            throw new Exception("Invalid Browser type " + browserType);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

    }

    public void navigateToUrl(String url){
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(elementDetectionTimeout,TimeUnit.SECONDS);
        driver.get(url);
        
    }
    public void closeBrowser(){
        driver.quit();;
    }

}
