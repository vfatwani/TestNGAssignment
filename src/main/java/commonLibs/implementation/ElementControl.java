package commonLibs.implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementControl {
    WebDriver driver;
    public ElementControl(WebDriver driver){
        this.driver=driver;
    }
    public void clickElement
            (WebElement element){
        element.click();
    }
    public void setText(WebElement element,String text){
        element.sendKeys(text);
    }
    public String getText(WebElement element){
       return element.getText();
    }
    public void clearText(WebElement element){
        element.clear();
    }
    public boolean isVisible(WebElement element){
      return  element.isDisplayed();
    }
    public boolean isEnabled(WebElement element){
        return  element.isEnabled();
    }
    public void refreshPage(){
        driver.navigate().refresh();
    }
    public boolean isDisplayed(WebElement element){
        return  element.isDisplayed();
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();
    }
    public String getTextFromAlert(){
       return driver.switchTo().alert().getText();
    }
    public  void selectViaVisibleText(WebElement element,String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
    public  void selectViaValue(WebElement element,String value){
        Select select = new Select(element);
        select.selectByValue(value);
    }
    public  void selectViaIndex(WebElement element,int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }
    protected void clickElementByJavascriptExecutor(String xpath){
        WebElement element=driver.findElement(By.xpath(xpath));
        JavascriptExecutor ex=(JavascriptExecutor)driver;
        ex.executeScript("arguments[0].click()", element);
    }
    public Long scrollYPositionByJavascriptExecutor(){
        JavascriptExecutor j = (JavascriptExecutor) driver;
        Long value = (Long) j.executeScript("return window.pageYOffset;");
        return value;
    }
    public void scrollElementIntoViewByJavascriptExecutor(WebElement element){
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].scrollIntoView(true);",element);
    }
    public void moveToElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }
    public void freeze(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
