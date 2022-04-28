package test;

import org.junit.Test;
import sms.SMSChecker;
import sms.SMS;

import static org.junit.Assert.*;

public class TestSMSCheckerHW {

    public void printBreak(){
        System.out.println("\nPASSED");
        System.out.println("===========================================================================");
    }

    @Test
    public void validateRegisterShortCode() {
        SMS referenceSMS = new SMS();
        referenceSMS.setShortCode("register");

        // assert true
        System.out.println("assertTrue tests\n");

        SMS testSMS1 = new SMS();
        testSMS1.setShortCode("register");
        SMS testSMS2 = new SMS();
        testSMS2.setShortCode("reGisTeR");
        SMS testSMS3 = new SMS();
        testSMS3.setShortCode("REGISTER");
        SMS testSMS4 = new SMS();
        testSMS4.setShortCode("Register");

        assertTrue(SMSChecker.isShortCodeMatch(testSMS1, referenceSMS));
        assertTrue(SMSChecker.isShortCodeMatch(testSMS2, referenceSMS));
        assertTrue(SMSChecker.isShortCodeMatch(testSMS3, referenceSMS));
        assertTrue(SMSChecker.isShortCodeMatch(testSMS4, referenceSMS));

        printBreak();

        // assert false
        System.out.println("assertFalse tests\n");

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
        SMS testSMS12 = new SMS();


        assertFalse(SMSChecker.isShortCodeMatch(testSMS5, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS6, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS7, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS8, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS9, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS10, referenceSMS));
        assertFalse(SMSChecker.isShortCodeMatch(testSMS11, referenceSMS));

        printBreak();

        System.out.println("assertEquals tests");
        //REFACTOR??
        assertEquals("register", testSMS1.getShortCode().toLowerCase());
        System.out.println(testSMS1.getShortCode() + " changed to lower case is considered equal to short code register.");
        assertEquals("register", testSMS2.getShortCode().toLowerCase());
        System.out.println(testSMS2.getShortCode() + " changed to lower case is considered equal to short code register.");
        assertEquals("register", testSMS3.getShortCode().toLowerCase());
        System.out.println(testSMS3.getShortCode() + " changed to lower case is considered equal to short code register.");
        assertEquals("register", testSMS4.getShortCode().toLowerCase());
        System.out.println(testSMS4.getShortCode() + " changed to lower case is considered equal to short code register.");

        printBreak();

        System.out.println("assertNotNull tests");

        assertNotNull(testSMS1);
        System.out.println("SMS NOT NULL");
        assertNotNull(testSMS2);
        System.out.println("SMS NOT NULL");
        assertNotNull(testSMS3);
        System.out.println("SMS NOT NULL");
        assertNotNull(testSMS4);
        System.out.println("SMS NOT NULL");

        printBreak();

        System.out.println("assertNull tests");

        assertNull(testSMS12.getShortCode());
        System.out.println("SMS 12 has no ShortCode");

        printBreak();

    }

    @Test
    public void validateUserInformation() {

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
        SMS testSMS17 = new SMS();

        // correct usage
        testSMS1.setMessage("Marco Valmores, 1973-09-10, Marikina City");
        testSMS17.setMessage("Marco A. Valmores, 1973-09-10, Marikina City");

        System.out.println("[ CORRECT USAGE VARIANT 1 ]");
        assertTrue(SMSChecker.hasValidPersonalDetailsFormat(testSMS1));
        assertNotNull(SMSChecker.personalDetailsSeparator(testSMS1.getMessage()));
        System.out.println("Personal details NOT NULL");

        printBreak();

        System.out.println("[ CORRECT USAGE VARIANT 2 ]");
        assertTrue(SMSChecker.hasValidPersonalDetailsFormat(testSMS17));
        assertNotNull(SMSChecker.personalDetailsSeparator(testSMS17.getMessage()));
        System.out.println("Personal details NOT NULL");

        System.out.println("\n Correct usage tests passed");
        printBreak();

        // with unnecessary white spaces
        System.out.println("[ INCORRECT USAGE DUE TO WHITESPACES ]");
        testSMS2.setMessage("  Marco Valmores  , 1973-09-10,   Marikina City  ");
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS2));

        assertNotNull(SMSChecker.personalDetailsSeparator(testSMS2.getMessage()));
        System.out.println("Personal details NOT NULL");

        System.out.println("\n Whitespace test passed");
        printBreak();

        //missing spaces
        System.out.println("[ INCORRECT USAGE DUE TO MISSING SPACES ]");
        testSMS3.setMessage("MarcoValmores, 1973-09-10, Marikina City");
        testSMS10.setMessage("Marco Valmores,1973-09-10,Marikina");
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS3));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS10));
        printBreak();

        // wrong name format
        System.out.println("[ INCORRECT USAGE DUE TO WRONG NAME FORMAT ]");
        testSMS4.setMessage("Valmores, Marco, 1973-09-10, Marikina");
        testSMS8.setMessage("Marco, 1973-09-10, Marikina City");
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS4));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS8));
        printBreak();

        // wrong address format
        System.out.println("[ INCORRECT USAGE DUE TO WRONG ADDRESS FORMAT ]");
        testSMS5.setMessage("Marco Valmores, 1973-09-10, Marikina C");
        testSMS6.setMessage("Marco Valmores, 1973-09-10, Marikina");
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS5));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS6));
        printBreak();

        // wrong order of details
        System.out.println("[ INCORRECT USAGE DUE TO WRONG ORDER OF DETAILS ]");
        testSMS7.setMessage("Marco Valmores, Marikina, 1973-09-10");
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS7));
        printBreak();

        // invalid separator/s
        System.out.println("[ INCORRECT USAGE DUE TO WRONG SEPARATORS ]");
        testSMS15.setMessage("Marco Valmores 1973-09-10 Marikina City");
        testSMS16.setMessage("<Marco Valmores> <1973-09-10> <Marikina City>");

        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS15));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS16));

        printBreak();

        // wrong date format
        System.out.println("[ INCORRECT USAGE DUE TO WRONG DATE FORMAT ]");
        testSMS9.setMessage("Marco Valmores, 1973-19-10, Marikina City");
        testSMS11.setMessage("Marco Valmores, September 10 1973, Marikina City");
        testSMS12.setMessage("Marco Valmores, 1973/09/19, Marikina City");
        testSMS13.setMessage("Marco Valmores, 09-10-1973, Marikina City");
        testSMS14.setMessage("Marco Valmores, 09/10/1973, Marikina City");

        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS9));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS11));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS12));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS13));
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS14));


    }
}
