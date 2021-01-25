package commonLibs.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;

public class ReportUtils {

    ExtentHtmlReporter htmlReport;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public ReportUtils(String htmlReportFileName){
        htmlReportFileName = htmlReportFileName.trim();
        htmlReport=new ExtentHtmlReporter(htmlReportFileName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReport);
    }

    public void createATestCase(String testCaseName){
        extentTest = extentReports.createTest(testCaseName);
    }

    public void addTestLogs(Status status, String comments) {
        extentTest.log(status,comments);
    }

    public void attachScreeShotToReport(String fileName) throws IOException {
        extentTest.addScreenCaptureFromPath(fileName);
    }
    public void flushReport(){
        extentReports.flush();
    }

}
