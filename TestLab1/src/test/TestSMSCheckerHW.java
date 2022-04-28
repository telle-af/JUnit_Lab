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
        System.out.println("[ assertTrue tests ]\n");

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
        System.out.println("[ assertFalse tests ]\n");

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

        System.out.println("[ assertEquals tests ]");
        //REFACTOR??

        assertEquals("register", testSMS1.getShortCode().toLowerCase());
        System.out.println(testSMS1.getShortCode() + " in lower case is considered equal to short code register.");
        assertEquals("register", testSMS2.getShortCode().toLowerCase());
        System.out.println(testSMS2.getShortCode() + " in lower case is considered equal to short code register.");
        assertEquals("register", testSMS3.getShortCode().toLowerCase());
        System.out.println(testSMS3.getShortCode() + " in lower case is considered equal to short code register.");
        assertEquals("register", testSMS4.getShortCode().toLowerCase());
        System.out.println(testSMS4.getShortCode() + " in lower case is considered equal to short code register.");

        printBreak();

        System.out.println("[ assertNotNull tests ]");

        assertNotNull(testSMS1);
        System.out.println("SMS 1 NOT NULL");
        assertNotNull(testSMS2);
        System.out.println("SMS 2 NOT NULL");
        assertNotNull(testSMS3);
        System.out.println("SMS 3 NOT NULL");
        assertNotNull(testSMS4);
        System.out.println("SMS 4 NOT NULL");

        printBreak();

        System.out.println("[ assertNull tests ]");

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

        // correct usage #1
        System.out.println("[ CORRECT USAGE - VARIANT 1 OF 2]");
        testSMS1.setMessage("Marco Valmores, 1973-09-10, Marikina City");

        // assertions
        assertTrue(SMSChecker.hasValidPersonalDetailsFormat(testSMS1));
        assertNotNull(SMSChecker.personalDetailsSeparator(testSMS1.getMessage()));
        System.out.println("Personal details NOT NULL");

        printBreak();

        // correct usage #2
        System.out.println("[ CORRECT USAGE - VARIANT 2 OF 2]");
        testSMS17.setMessage("Marco A. Valmores, 1973-09-10, Marikina City");

        //assertions
        assertTrue(SMSChecker.hasValidPersonalDetailsFormat(testSMS17));
        assertNotNull(SMSChecker.personalDetailsSeparator(testSMS17.getMessage()));
        System.out.println("Personal details NOT NULL");

        System.out.println("\n Correct usage tests passed");
        printBreak();


        // with unnecessary white spaces
        System.out.println("[ INCORRECT USAGE DUE TO WHITESPACES - CASE 1 of 1 ]");
        testSMS2.setMessage("  Marco Valmores  , 1973-09-10,   Marikina City  ");

        // assertions
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS2));
        assertNotNull(SMSChecker.personalDetailsSeparator(testSMS2.getMessage()));
        System.out.println("Personal details NOT NULL");

        System.out.println("\n Whitespace test passed");
        printBreak();

        // missing space/s #1
        System.out.println("[ INCORRECT USAGE DUE TO MISSING SPACES - CASE 1 OF 2 ]");
        testSMS3.setMessage("MarcoValmores, 1973-09-10, Marikina City");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS3));

        // missing space/s #2
        System.out.println("[ INCORRECT USAGE DUE TO MISSING SPACES - CASE 2 OF 2 ]");
        testSMS10.setMessage("Marco Valmores,1973-09-10,Marikina");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS10));
        assertNull(SMSChecker.personalDetailsSeparator(testSMS10.getMessage()));

        System.out.println("\n Missing-spaces tests passed");
        printBreak();


        // wrong name format #1
        System.out.println("[ INCORRECT USAGE DUE TO WRONG NAME FORMAT - CASE 1 of 2 ]");
        testSMS4.setMessage("Valmores, Marco, 1973-09-10, Marikina");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS4));
        assertNull(SMSChecker.personalDetailsSeparator(testSMS4.getMessage()));

        // wrong name format #2
        System.out.println("[ INCORRECT USAGE DUE TO WRONG NAME FORMAT - CASE 2 of 2 ]");
        testSMS8.setMessage("Marco, 1973-09-10, Marikina City");

        // assertions
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS8));

        System.out.println("\n Name format tests passed");
        printBreak();


        // wrong address format #1
        System.out.println("[ INCORRECT USAGE DUE TO WRONG ADDRESS FORMAT - CASE 1 of 2 ]");
        testSMS5.setMessage("Marco Valmores, 1973-09-10, Marikina C");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS5));

        // wrong address format #2
        System.out.println("[ INCORRECT USAGE DUE TO WRONG ADDRESS FORMAT - CASE 2 of 2 ]");
        testSMS6.setMessage("Marco Valmores, 1973-09-10, Marikina");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS6));

        System.out.println("\n Address format tests passed");
        printBreak();


        // wrong order of details
        System.out.println("[ INCORRECT USAGE DUE TO WRONG ORDER OF DETAILS - CASE 1 of 1 ]");
        testSMS7.setMessage("Marco Valmores, Marikina, 1973-09-10");
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS7));

        System.out.println("\n Detail order test passed");
        printBreak();


        // invalid separator #1
        System.out.println("[ INCORRECT USAGE DUE TO WRONG SEPARATORS - CASE 1 of 2 ]");
        testSMS15.setMessage("Marco Valmores 1973-09-10 Marikina City");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS15));

        // invalid separator #2
        System.out.println("[ INCORRECT USAGE DUE TO WRONG SEPARATORS - CASE 2 of 2 ]");
        testSMS16.setMessage("<Marco Valmores> <1973-09-10> <Marikina City>");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS16));

        System.out.println("\n Separators tests passed");
        printBreak();


        // wrong date format # 1
        System.out.println("[ INCORRECT USAGE DUE TO WRONG DATE FORMAT - CASE 1 OF 5 ]");
        testSMS9.setMessage("Marco Valmores, 1973-19-10, Marikina City");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS9));
        assertNotNull(SMSChecker.personalDetailsSeparator(testSMS9.getMessage()));

        // wrong date format # 2
        System.out.println("[ INCORRECT USAGE DUE TO WRONG DATE FORMAT - CASE 2 OF 5 ]");
        testSMS11.setMessage("Marco Valmores, September 10 1973, Marikina City");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS11));

        // wrong date format # 3
        System.out.println("[ INCORRECT USAGE DUE TO WRONG DATE FORMAT - CASE 3 OF 5 ]");
        testSMS12.setMessage("Marco Valmores, 1973/09/19, Marikina City");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS12));

        // wrong date format # 4
        System.out.println("[ INCORRECT USAGE DUE TO WRONG DATE FORMAT - CASE 4 OF 5 ]");
        testSMS13.setMessage("Marco Valmores, 09-10-1973, Marikina City");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS13));

        // wrong date format # 5
        System.out.println("[ INCORRECT USAGE DUE TO WRONG DATE FORMAT - CASE 5 OF 5 ]");
        testSMS14.setMessage("Marco Valmores, 09/10/1973, Marikina City");

        // assertion
        assertFalse(SMSChecker.hasValidPersonalDetailsFormat(testSMS14));

        System.out.println("\n Date format tests passed");
        printBreak();


    }
}
