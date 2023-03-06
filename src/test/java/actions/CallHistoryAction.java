package actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

import utilities.Functions;

import java.net.MalformedURLException;

//************************************************************************************************************************
//************************************************************************************************************************
//                  Call History related actions
//************************************************************************************************************************
//************************************************************************************************************************

public class CallHistoryAction extends BaseClassiOSAndAndroid {
    static WebDriverWait wait;
    static int count = 0;

    public CallHistoryAction() throws MalformedURLException, InterruptedException {
        super(androidDriver, iosDriver);
    }

    static SoftAssert sa= new SoftAssert();
    public static void CallHistoryFirstCall() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.firstCallItem()));
        CallHistoryPage.firstCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.endCallButton()));
        Thread.sleep(4000);
        CallActivePage.endCallButton().click();
    }

    public static void CallHistorySecondCall() throws Exception {

        //sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        //CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.secondCallItem()));
        CallHistoryPage.secondCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.endCallButton()));
        Thread.sleep(4000);
        CallActivePage.endCallButton().click();
    }

    public static void CallHistoryThirdCall() throws Exception {

        //sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        //CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.thirdCallItem()));
        CallHistoryPage.thirdCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.endCallButton()));
        Thread.sleep(4000);
        CallActivePage.endCallButton().click();
    }

    public static void CallDetailsCall() throws Exception {

        //sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        //CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.infoButton()));
        CallHistoryPage.infoButton().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callButton()));
        CallHistoryPage.callButton().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.endCallButton()));
        Thread.sleep(4000);
        CallActivePage.endCallButton().click();
    }

    public static void CallDetailsMessage() throws Exception {

        //sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.infoButton()));
        CallHistoryPage.infoButton().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.messageButton()));
        CallHistoryPage.messageButton().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.backArrowButton()));
        CallHistoryPage.backArrowButton().click();

    }




}























