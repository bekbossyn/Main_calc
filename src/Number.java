import java.nio.file.FileAlreadyExistsException;
import java.security.cert.TrustAnchor;
import java.util.LinkedHashMap;
import java.util.Map;

public class Number {

    public static String INVALID_NUMBER = "Invalid number";

    public static String DECIMAL = "Decimal";
    public static String ROMAN = "Roman";

    public static String[] romanLiterals = { "M", "D", "C", "L", "X", "V", "I" };

    public boolean isValid;
    public String stringValue;
    public int decimalValue;
    public String romanValue;
    public String valueType;

    Number(String stringValue) {
        this.isValid = isValidNumber(stringValue);
        this.valueType = ValueType(stringValue);
        this.stringValue = stringValue;
        this.decimalValue = toDecimal(stringValue);
        this.romanValue = ToRoman(stringValue);
    }

    Number (int decimalValue) {
        this.stringValue = String.valueOf(decimalValue);
        this.isValid = isValidNumber(this.stringValue);
        this.valueType = ValueType(this.stringValue);
        this.decimalValue = decimalValue;
        this.romanValue = ToRoman(this.stringValue);
    }

    public String ValueType(String stringValue) {

        boolean roman = false;
        boolean decimalValue = false;

        if (isValidNumber(stringValue)) {

            // negative number
            if (stringValue.charAt(0) == '-') {
                stringValue = stringValue.substring(1);
            }

            if (isDecimalDigit(stringValue.charAt(0))) {
                return DECIMAL;
            } else if (isRomanDigit(stringValue.charAt(0))) {
                return ROMAN;
            }
        }
        return INVALID_NUMBER;
    }

    public static boolean isValidNumber(String stringValue){

        // the number is Valid

        // empty text
        if (stringValue.length() == 0) {
            return false;
        }

//        int sign = 1;
        // negative number
        if (stringValue.charAt(0) == '-') {
//            sign = -1;
            stringValue = stringValue.substring(1);
        }
        boolean decimalDigit = false;
        boolean romanDigit = false;
        boolean error = false;
        for (int i = 0; i < stringValue.length(); i++) {
            if (isDecimalDigit(stringValue.charAt(i))) {
                decimalDigit = true;
            } else if (isRomanDigit(stringValue.charAt(i))) {
                romanDigit = true;
            } else {
                error = true;
            }
        }
        return !error && ((decimalDigit && !romanDigit) || (!decimalDigit && romanDigit));
    }

    private static boolean isRomanDigit(char Char) {
        boolean Roman = false;
        for (String romanLiteral : romanLiterals) {
            if (romanLiteral.charAt(0) == Char) {
                Roman = true;
                break;
            }
        }
        return Roman;
    }

    private static boolean isDecimalDigit(char Char) {
        return Char >= '0' && Char <= '9';
    }

    public int toDecimal(String stringValue) {

        if (!isValidNumber(stringValue)) {
            return 0;
        }

        Map<Character, Integer> values = new LinkedHashMap<>();

        // decimal number
        if (ValueType(stringValue).equals(DECIMAL)) {
            return Integer.parseInt(stringValue);
        }

        int sign = 1;

        // negative number
        if (stringValue.charAt(0) == '-'){
            sign = -1;
            stringValue = stringValue.substring(1);
        }

        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int number = 0;
        for (int i = 0; i < stringValue.length(); i++) {
            if (i+1 == stringValue.length() || values.get(stringValue.charAt(i)) >= values.get(stringValue.charAt(i + 1))) {
                number += values.get(stringValue.charAt(i));
            } else {
                number -= values.get(stringValue.charAt(i));
            }
        }
        return number * sign;
    }

    public static String ToRoman(String stringValue) {

        // invalid number
        if (!isValidNumber(stringValue)) {
            return INVALID_NUMBER;
        }

        final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        final String[] romanLiterals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder s = new StringBuilder();
        String result = stringValue;

//        int sign = 1;
        // negative number
        if (stringValue.charAt(0) == '-'){
            stringValue = stringValue.substring(1);
//            sign = -1;
            s.append('-');
        }

        int intValue = 0;
        if (isDecimalDigit(stringValue.charAt(0)) ) {
            intValue = Integer.parseInt(stringValue);
        }

        if (isRomanDigit(stringValue.charAt(0))) {
            return result;
        }

        for (int i = 0; i < values.length; i++) {
            while (intValue >= values[i]) {
                intValue -= values[i];
                s.append(romanLiterals[i]);
            }
        }
        return s.toString();
    }

}
