package test;

import org.junit.Test;
import sms.SMSChecker;
import sms.SMS;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestSMSCheckerHW {

    @Test
    public void validateRegisterShortCode() {
        SMS referenceSMS = new SMS();
        referenceSMS.setShortCode("register");

        // assert true
        SMS testSMS1 = new SMS();
        testSMS1.setShortCode("register");
        SMS testSMS2 = new SMS();
        testSMS2.setShortCode("reGisTeR");
        SMS testSMS3 = new SMS();
        testSMS3.setShortCode("REGISTER");
        SMS testSMS4 = new SMS();
        testSMS4.setShortCode("Register");

        // assert false
        SMS testSMS5 = new SMS();
        testSMS5.setShortCode(" register");
        SMS testSMS6 = new SMS();
        testSMS6.setShortCode("register ");
        SMS testSMS7 = new SMS();
        testSMS7.setShortCode("register*");
        SMS testSMS8 = new SMS();
        testSMS8.setShortCode("Regstr");
        SMS testSMS9 = new SMS();
        testSMS9.setShortCode("Regester");
        SMS testSMS10 = new SMS();
        testSMS10.setShortCode("regist3r");
        SMS testSMS11 = new SMS();
        testSMS11.setShortCode("REG");

        assertTrue(SMSChecker.isShortCodeMatch(testSMS1, referenceSMS));
        assertTrue(SMSChecker.isShortCodeMatch(testSMS2, referenceSMS));
        assertTrue(SMSChecker.isShortCodeMatch(testSMS3, referenceSMS));
        assertTrue(SMSChecker.isShortCodeMatch(testSMS4, referenceSMS));

        assertFalse(SMSChecker.isShortCodeMatch(testSMS5, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS6, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS7, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS8, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS9, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS10, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS11, referenceSMS));


    }

    @Test
    public void validateUserInformation() {

        // assert true
        SMS testSMS1 = new SMS();
        SMS testSMS2 = new SMS();
        SMS testSMS3 = new SMS();
        SMS testSMS4 = new SMS();
        SMS testSMS5 = new SMS();
        SMS testSMS6 = new SMS();
        SMS testSMS7 = new SMS();
        SMS testSMS8 = new SMS();
        SMS testSMS9 = new SMS();
        SMS testSMS10 = new SMS();
        SMS testSMS11 = new SMS();
        SMS testSMS12 = new SMS();
        SMS testSMS13 = new SMS();
        SMS testSMS14 = new SMS();
        SMS testSMS15 = new SMS();
        SMS testSMS16 = new SMS();

        // correct usage
        testSMS1.setMessage("Marco Valmores, 1973-09-10, Marikina City");
        // with unnecessary white spaces
        testSMS2.setMessage("  Marco Valmores  , 1973-09-10,   Marikina City  ");
        //missing spaces
        testSMS3.setMessage("MarcoValmores, 1973-09-10, Marikina City");
        testSMS10.setMessage("Marco Valmores,1973-09-10,Marikina");
        // wrong name format
        testSMS4.setMessage("Valmores, Marco, 1973-09-10, Marikina");
        testSMS8.setMessage("Marco, 1973-09-10, Marikina City");
        // wrong address format
        testSMS5.setMessage("Marco Valmores, 1973-09-10, Marikina C");
        testSMS6.setMessage("Marco Valmores, 1973-09-10, Marikina");
        // wrong order of details
        testSMS7.setMessage("Marco Valmores, Marikina, 1973-09-10");
        // invalid separator/s
        testSMS15.setMessage("Marco Valmores 1973-09-10 Marikina City");
        testSMS16.setMessage("<Marco Valmores> <1973-09-10> <Marikina City>");
        // wrong date format
        testSMS9.setMessage("Marco Valmores, 1973-19-10, Marikina City");
        testSMS11.setMessage("Marco Valmores, September 10 1973, Marikina City");
        testSMS12.setMessage("Marco Valmores, 1973/09/19, Marikina City");
        testSMS13.setMessage("Marco Valmores, 09-10-1973, Marikina City");
        testSMS14.setMessage("Marco Valmores, 09/10/1973, Marikina City");




        assertTrue(SMSChecker.hasValidPersonalDetailsFormat(testSMS1));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS2));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS3));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS4));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS5));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS6));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS7));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS8));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS9));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS10));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS11));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS12));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS13));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS14));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS15));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS16));


    }
}