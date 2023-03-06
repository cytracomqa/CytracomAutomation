package actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pageObjects.BaseClassiOSAndAndroid;
import pageObjects.CallActivePage;
import pageObjects.CallHistoryPage;
import utilities.Functions;

import java.net.MalformedURLException;

//************************************************************************************************************************
//************************************************************************************************************************
//                  Call Active Screen related actions
//************************************************************************************************************************
//************************************************************************************************************************

public class CallActiveAction extends BaseClassiOSAndAndroid {
    static WebDriverWait wait;
    static int count = 0;

    public CallActiveAction() throws MalformedURLException, InterruptedException {
        super(androidDriver, iosDriver);
    }

    static SoftAssert sa= new SoftAssert();


    public static void CallActivePauseButton() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.firstCallItem()));
        CallHistoryPage.firstCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.pauseHoldButton()));
        CallActivePage.endCallButton().click();
    }

    public static void CallActiveAddCallButton() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.firstCallItem()));
        CallHistoryPage.firstCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.addCallButton()));
        CallActivePage.endCallButton().click();
    }

    public static void CallActiveTransferCallButton() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.firstCallItem()));
        CallHistoryPage.firstCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.transferCallButton()));
        CallActivePage.endCallButton().click();
    }

    public static void CallActiveSpeakerButton() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.firstCallItem()));
        CallHistoryPage.firstCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.speakerButton()));
        CallActivePage.speakerButton().click();
        Thread.sleep(2000);
        CallActivePage.speakerButton().click();
        CallActivePage.endCallButton().click();
    }
    public static void CallActiveMuteUnmuteButton() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.callHistoryMenu()));
        CallHistoryPage.callHistoryMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(CallHistoryPage.firstCallItem()));
        CallHistoryPage.firstCallItem().click();
        sa.assertTrue(Functions.verifyElementPresence(CallActivePage.speakerButton()));
        CallActivePage.muteButton().click();
        Thread.sleep(2000);
        CallActivePage.unmuteButton().click();
        CallActivePage.endCallButton().click();
    }






}























