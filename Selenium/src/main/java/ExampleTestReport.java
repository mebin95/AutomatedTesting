package utils.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExampleTestReport {

    private ExtentReports report;
    private ExtentTest test;
    private String reportFilePath = "C:/Users/Administrator/IdeaProjects/";

    @BeforeClass
    public static void init(){

    }

    @Before
    public void setUp(){
        report = new ExtentReports();
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
        extentHtmlReporter.config().setReportName("ReportName");
        extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
        report.attachReporter(extentHtmlReporter);
        test = report.createTest("TestName");
    }

    @Test
    public void myTestMethod(){
        test.log(Status.INFO,"Info level");
        test.fail("Failed");
    }

}
