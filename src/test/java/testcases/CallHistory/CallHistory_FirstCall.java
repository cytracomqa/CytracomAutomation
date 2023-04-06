package testcases.CallHistory;

import actions.CallHistoryAction;
import actions.SignInAction;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.BaseClassiOSAndAndroid;
import utilities.Functions;
import utilities.Log;
import utilities.Utils;
import utilities.reports.ExtentTestManager;

public class CallHistory_FirstCall {

    private String sTestCaseName;

    // Following TestNg Test case pattern, and divided a Test case in to three different part.
    // In Before Method, your code will always be the same for every other test case.
    // In other way before method is your prerequisites of your main Test Case
    @BeforeMethod
    public void beforeMethod() throws Exception {
        // Getting the Test Case name, as it will going to use in so many places
        // The main use is to get the TestCase row from the Test Data Excel sheet
        sTestCaseName = this.toString();

        // From above method we get long test case name including package and class name etc.
        // The below method will refine your test case name, exactly the name use have used
        sTestCaseName = Utils.getTestCaseName(this.toString());

        // Start printing the logs and printing the Test Case name
        Log.startTestCase(sTestCaseName);
        ExtentTestManager.startTest(sTestCaseName);
    }

    // This is the starting of the Main Test Case
    @Test
    public void main() throws Exception {

        // Every exception thrown from any class or method, will be catch here and will be taken care off
        try{

            //SignIn if not Signed In
            SignInAction.SignInFlow();


            CallHistoryAction.CallHistoryFirstCall();
            ExtentTestManager.getTest().log(Status.PASS, "Calling First Entry on call history page");

        }catch (Exception e){
            System.out.println(e.getMessage());

            String screenShotPath="";
            // If the exception is in between the test, bcoz of any element not found or anything, this will take a screen shot
            if(BaseClassiOSAndAndroid.deviceType.equalsIgnoreCase("android")) {
                screenShotPath = Utils.takeScreenshotAndroid(sTestCaseName);
            }
            else{
                screenShotPath = Utils.takeScreenshotiOS(sTestCaseName);
            }

            // If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
            ExtentTestManager.getTest().log(Status.FAIL, "Test Failed - " + e.getMessage() +  ExtentTestManager.getTest().addScreenCaptureFromPath(screenShotPath));

            Functions.failClose();

            // This will print the error log message
            Log.error(e.getMessage());
            // Again throwing the exception to fail the test completely in the TestNG results
            throw (e);

        }

    }

    //Its time to close the finish the test case

    @AfterMethod
    public void afterMethod() {

        // Printing beautiful logs to end the test case
        Log.endTestCase(sTestCaseName);
        // Closing the opened driver
        //driver.closeApp();
        ExtentTestManager.endTest();

    }


}
