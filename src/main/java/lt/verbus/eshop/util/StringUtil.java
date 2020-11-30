package lt.verbus.eshop.util;

public class StringUtil {

    /**
     * Checks if all the characters are digits
     */

    public static boolean areCharsDigits(String value){
        return value.matches("\\d+");
    }

}
