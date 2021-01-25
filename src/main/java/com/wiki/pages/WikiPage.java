package com.wiki.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class WikiPage extends  BasePage {

    private WebDriver driver;
    @FindBy(xpath = "//*[@id='toc']/ul/li/a/span[2]")
    List<WebElement> contentList;
    @FindBy(xpath = "//span[@class='mw-headline']")
    List<WebElement> headingList;
    @FindBy(xpath = "//*[@id='toc']/ul/li/a")
    List<WebElement> contentHyperLink;
    @FindBy(xpath = "(//A[@href='/wiki/Nike_(mythology)'][text()='Nike'][text()='Nike'])[1]")
    WebElement linkNike;
    @FindBy(xpath = "(//P)[12]")
    WebElement iframe;
    @FindBy(xpath = "//*[@id='Family_tree']")
    WebElement tableFamilyTree;
    @FindBy(id="Family_tree")
    WebElement textFamilyTree;

    public WikiPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public List<String> getAllTheHeading() {
        List<String> list=new ArrayList<>();
        for(WebElement element:contentList){
            list.add(elementControl.getText(element));
        }
        return list;
    }

    public List<String> getAllHeaderList() {
        List<String> list=new ArrayList<>();
        for(WebElement element:headingList){
            list.add(elementControl.getText(element));
        }
        return list;
    }

    public List<Integer> checkFunctioningHyperLink(){
        String url = "";
        HttpURLConnection huc = null;
        List<WebElement> contentLink = contentHyperLink;
        List<Integer> responseResult = new ArrayList<>();
        for(WebElement element:contentLink){
            url = element.getAttribute("href");
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                int respCode= huc.getResponseCode();
                if(respCode >= 400){
                    System.out.println(url+" is a broken link");
                    responseResult.add(respCode);
                }
                else{
                    System.out.println(url+" is a valid link");
                    responseResult.add(respCode);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return responseResult;
    }


    public String switchToPopUp(){
        elementControl.freeze(2);
        elementControl.scrollElementIntoViewByJavascriptExecutor(linkNike);
        elementControl.moveToElement(linkNike);
        elementControl.freeze(3);
        return (elementControl.getText(iframe));
    }

    public void clickOnNike() {
        elementControl.clickElement(linkNike);
    }

    public String dispalysFamilyTree() {
        return elementControl.getText(textFamilyTree);
    }
    public boolean displayOfFamilyTree(){
        return elementControl.isDisplayed(tableFamilyTree);
    }
}
