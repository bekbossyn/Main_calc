import java.util.LinkedHashMap;
import java.util.Map;

public class Number {

    public int intValue;
    public String strValue;
    public int roman;

    Number(String strValue){
        this.strValue = strValue;
        this.roman = Roman(strValue);
        this.intValue = toDecimal(strValue, this.roman);
    }

    Number (int intValue){
        this.intValue = intValue;
        this.strValue = ToRoman(intValue);
        this.roman = 0; // already declared as decimal number
    }

    public int toDecimal(String strValue, int roman) {
        Map<Character, Integer> values = new LinkedHashMap<>();

        if (roman == 0) {

            return Integer.parseInt(strValue);
        }

        int sign = 1;
        // negative number
        if (strValue.charAt(0) == '-'){
            sign=-1;
            strValue=strValue.substring(1);
        }

        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);

        int number = 0;
        for (int i = 0; i < strValue.length(); i++) {
            if (i+1 == strValue.length() || values.get(strValue.charAt(i)) >= values.get(strValue.charAt(i + 1))) {
                number += values.get(strValue.charAt(i));
            } else {
                number -= values.get(strValue.charAt(i));
            }
        }
        return number * sign;
    }

    public String ToRoman(int intValue) {

        final int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        final String[] romanLiterals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder s = new StringBuilder();

        // negative number
        if (intValue < 0){
            intValue = -intValue;
            s.append('-');
        }
        if( intValue == 0){
            return "";
        }
        for (int i = 0; i < values.length; i++) {
            while (intValue >= values[i]) {
                intValue -= values[i];
                s.append(romanLiterals[i]);
            }
        }
        return s.toString();
    }

    public int Roman(String strValue) {
        /*
            0 = Not Roman
            1 = Roman
            2 = Error
         */

        boolean decimal;
        decimal=false;
        boolean roman;
        roman = false;
        boolean error;
        error = false;

        //negative number check
        if (strValue.charAt(0) == '-') {
            strValue = strValue.substring(1);
        }


        for (int i = 0; i < strValue.length(); i++) {
            if (strValue.charAt(i) >='0' && strValue.charAt(i) <= '9') {
                decimal = true;
            } else if (strValue.charAt(i) == 'I' || strValue.charAt(i) == 'V' || strValue.charAt(i) == 'X' ||
                    strValue.charAt(i) == 'L' || strValue.charAt(i) == 'C'){
                roman = true;
            } else {
                error = true;
            }
        }
        if (error || (roman && decimal)) {
            return 2;
        }
        if (roman && !decimal) {
            return 1;
        }
        if (decimal && !roman) {
            return 0;
        }
        return 2;
    }

}
