package actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

import utilities.Functions;

import java.net.MalformedURLException;

//************************************************************************************************************************
//************************************************************************************************************************
//                  Voicemail related actions
//************************************************************************************************************************
//************************************************************************************************************************

public class VoicemailAction extends BaseClassiOSAndAndroid {
    static WebDriverWait wait;
    static int count = 0;

    public VoicemailAction() throws MalformedURLException, InterruptedException {
        super(androidDriver, iosDriver);
    }

    static SoftAssert sa= new SoftAssert();

    public static void firstVoicemailCellExpand() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailMenu()));
        VoicemailPage.voicemailMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailCell("1")));
        VoicemailPage.voicemailCell("1").click();
        Thread.sleep(2000);
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.callbutton()));
        VoicemailPage.voicemailCell("1").click();

    }

    public static void firstVoicemailPlayPause() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailMenu()));
        VoicemailPage.voicemailMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.playButton("1")));
        VoicemailPage.playButton("1").click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.pauseButton()));
        VoicemailPage.pauseButton().click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.playButton("1")));


    }

    public static void firstVoicemailExpandPlayPause() throws Exception {

        //sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailMenu()));
        VoicemailPage.voicemailMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailCell("1")));
        VoicemailPage.voicemailCell("1").click();
        Thread.sleep(2000);
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailSlider()));
        VoicemailPage.playButton("1").click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.pauseButton()));
        VoicemailPage.pauseButton().click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.playButton("1")));

    }

    public static void secondVoicemailCellExpand() throws Exception {

        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailMenu()));
        VoicemailPage.voicemailMenu().click();
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailCell("2")));
        VoicemailPage.voicemailCell("2").click();
        Thread.sleep(2000);
        sa.assertTrue(Functions.verifyElementPresence(VoicemailPage.voicemailSlider()));
        VoicemailPage.voicemailCell("2").click();

    }




}























