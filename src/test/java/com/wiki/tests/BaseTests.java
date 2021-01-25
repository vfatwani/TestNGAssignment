package com.wiki.tests;

import com.aventstack.extentreports.Status;
import com.wiki.pages.WikiPage;
import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ReportUtils;
import commonLibs.utils.ScreenShotUtils;
import commonLibs.utils.configUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.internal.TestResult;

import java.util.Properties;

public class BaseTests {
    CommonDriver cmnDriver;
    WebDriver driver;
    WikiPage wikipediaPage;
    String configFileName;
    String currentWorkingDirectory;
    Properties configProperty;
    ReportUtils reportUtils;
    String reportFileName;
    ScreenShotUtils screenShot;

    @BeforeSuite
    public void preSetUp() throws Exception {
        currentWorkingDirectory=System.getProperty("user.dir");
        configFileName = currentWorkingDirectory+ "/config/config.properties";
        configProperty = configUtils.readProperty(configFileName);
        reportFileName= currentWorkingDirectory+"/Reports/wikiTestReport.html";
        reportUtils=new ReportUtils(reportFileName);

    }


    @BeforeClass
    public void setUp() throws Exception {

        cmnDriver=new CommonDriver(configProperty.getProperty("browserType"));
        driver= cmnDriver.getDriver();
        wikipediaPage = new WikiPage(driver);
        screenShot=new ScreenShotUtils(driver);
        cmnDriver.navigateToUrl(configProperty.getProperty("baseURl"));
    }
    @AfterMethod
    public void postTestAction(ITestResult result) throws Exception {
    String testCaseName = result.getTestName();
    long executionTime = System.currentTimeMillis();

    String screenShotFileName = currentWorkingDirectory+"/Screenshots/"+testCaseName+executionTime+".jpeg";
        if(result.getStatus()==ITestResult.FAILURE){
             reportUtils.addTestLogs(Status.FAIL,"One or more step failed");
            screenShot.captureAndSaveScreenShot(screenShotFileName);
    }
    }
    @AfterClass
    public void tearDown(){
        cmnDriver.closeBrowser();
    }

    @AfterSuite
    public void postTearDown(){
        reportUtils.flushReport();
    }
}
