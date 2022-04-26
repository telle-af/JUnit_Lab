package sms;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.HashMap;

public class SMSChecker {

    public static boolean isShortCodeMatch(SMS test_sms, SMS reference_sms) {

        //set initial value to false
        boolean result = false;

        String reference_short_code = reference_sms.getShortCode();
        // make test_sms short code lower case and check if valid
        String test_short_code = test_sms.getShortCode().toLowerCase();

        //check if both strings are equal
        if (reference_short_code.equals(test_short_code)) {
            result = true;
        }
        //return result
        return result;
    }

    public static boolean isValidDateFormat(final String date) {

        boolean valid = false;

        try {

            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-MM-dd")  //"uuuu-M-d"
                            .withResolverStyle(ResolverStyle.STRICT)
            );

            valid = true;

        } catch (DateTimeParseException e) {
            e.printStackTrace();
            valid = false;

        } finally {
            return valid;
        }


    }

    public static boolean hasValidPersonalDetailsFormat(final SMS sms) {

        String text = sms.getMessage();
        boolean valid = true;
        PersonalDetails person = personalDetailsSeparator(text);

        // if null, return false (may be caused by bad formatting)
        if (person == null) {
            return false;
        }


        // if it has trailing or leading whitespace, return false
        if (hasLeadingOrTrailingWhitespace(person.getFullName()) ||
                hasLeadingOrTrailingWhitespace(person.getBirthDate()) ||
                hasLeadingOrTrailingWhitespace(person.getCity())) {
            return false;
        }

        // if date format is invalid, return false
        if (!isValidDateFormat(person.getBirthDate())) {
            return false;
        }

        // if there's only one word in the full name
        if (stringArray(person.getFullName()).length <= 1) {
            return false;
        }

        // if there's only one word in city and if city doesn't contain the word "city"
        if(stringArray(person.getCity()).length <= 1 || !person.getCity().toLowerCase().matches("(.*)city")){
            return false;
        }

        return valid;
    }

    public static boolean hasLeadingOrTrailingWhitespace(final String text) {
        if (Character.isWhitespace(text.charAt(0)) || Character.isWhitespace(text.charAt(text.length() - 1))) {
            return true;
        }
        return false;
    }

    public static String[] stringArray(final String text) {

        String[] arrOfStr = text.split(" ", 0);
        return arrOfStr;

    }

    public static PersonalDetails personalDetailsSeparator(final String details) {

        String[] arrOfStr = details.split(", ", 3);

        /*
        for(int index = 0; index < arrOfStr.length; index++){
            arrOfStr[index] = arrOfStr[index].stripLeading().stripTrailing();
            System.out.println(arrOfStr[index]);
        }
        */

        if (arrOfStr.length == 3) {
            return new PersonalDetails(arrOfStr[0], arrOfStr[1], arrOfStr[2]);
        } else {
            return null;
        }


    }

    public static void main(String[] args) {
        PersonalDetails personA = personalDetailsSeparator("   Marco Valmores   ,    1973-09-10    ,     Marikina City   ");
        //System.out.println(stringArray("asdf jkl; 2qewq zxcvouio asdfasdf, asdfasdfasdf"));
        System.out.println(hasLeadingOrTrailingWhitespace(" aaa "));


    }
}