public class Calculator {

    final String INVALID_EXPRESSION = "Invalid expression";

    public boolean isValidExpression(String stringValue){
        // the operation is Valid

//        int sign = 1;
        // negative number
        if (stringValue.charAt(0) == '-') {
//            sign = -1;
            stringValue = stringValue.substring(1);
        }

        boolean operation_found = false;
        String str1 = null;
        String str2 = null;

        if (stringValue.contains("-")) {
            operation_found = true;
            str1=stringValue.substring(0,stringValue.indexOf("-"));
            str2=stringValue.substring(stringValue.indexOf("-")+1);
        } else if (stringValue.contains("*")) {
            operation_found = true;
            str1=stringValue.substring(0,stringValue.indexOf("*"));
            str2=stringValue.substring(stringValue.indexOf("*")+1);
        } else if (stringValue.contains("+")) {
            operation_found = true;
            str1 = stringValue.substring(0, stringValue.indexOf("+"));
            str2 = stringValue.substring(stringValue.indexOf("+") + 1);
        }  else if (stringValue.contains("/")) {
            operation_found = true;
            str1 = stringValue.substring(0, stringValue.indexOf("/"));
            str2 = stringValue.substring(stringValue.indexOf("/") + 1);
        }
        if (!operation_found) {
            return false;
        }

        //Number num = new Number(str1);
        if (!(new Number(str1).isValid)) {
            return false;
        }

        //num = new Number(str2);
        return new Number(str2).isValid;
    }

    public String calculate(String text) {

        // remove spaces
        int length = 0;
        while (length < text.length()) {
            if (text.charAt(length) == ' ') {
                text = text.substring(0, length) + text.substring(length + 1);
            } else {
                length += 1;
            }
        }

        if (!isValidExpression(text)) {
            return INVALID_EXPRESSION;
        }
        int sign = 1;
        // first number negative
        if (text.charAt(0)=='-'){
            sign = -1;
            text = text.substring(1);
        }
        String str1 = "";
        String str2 = "";
        String operation = "";
        if (text.contains("-")) {
            str1=text.substring(0,text.indexOf("-"));
            str2=text.substring(text.indexOf("-")+1);
            operation="-";
        } else if (text.contains("*")) {
            str1=text.substring(0,text.indexOf("*"));
            str2=text.substring(text.indexOf("*")+1);
            operation="*";
        } else if (text.contains("+")) {
            str1 = text.substring(0, text.indexOf("+"));
            str2 = text.substring(text.indexOf("+") + 1);
            operation = "+";
        }  else if (text.contains("/")) {
            str1 = text.substring(0, text.indexOf("/"));
            str2 = text.substring(text.indexOf("/") + 1);
            operation = "/";
        }

        // first number negative
        if (sign == -1) {
            str1 =  "-" + str1;
        }

        Number number1=new Number(str1);

        Number number2=new Number(str2);

        Number result = new Number(0);
//        Number result = new Number(0);

        switch (operation) {
            case "-":  // subtraction
                result = subtraction(number1, number2);
//                result = number1.decimalValue - number2.decimalValue;
                break;
            case "+":  // addition
                result = addition(number1, number2);
//                result = number1.decimalValue + number2.decimalValue;
                break;
            case "*":  // multiplication
                result = multiplication(number1, number2);
//                result = number1.decimalValue * number2.decimalValue;
                break;
            case "/":  // division
                result = division(number1, number2);
//                result = number1.decimalValue / number2.decimalValue;
                break;
        }

        //Number number = new Number(String.valueOf(result));

        // printing result
        if (result.valueType.equals(number1.valueType) && result.valueType.equals(number2.valueType)) {
            return result.stringValue;
        }
        return INVALID_EXPRESSION;
    }

    public Number subtraction(Number number1, Number number2) {
        Number num;
        int result = number1.decimalValue - number2.decimalValue;
        if (number1.valueType.equals(Number.ROMAN)) {
            num = new Number(Number.ToRoman(String.valueOf(result)));
        } else {
            num = new Number(result);
        }
        return num;
    }

    public Number addition(Number number1, Number number2) {
        Number num;
        int result = number1.decimalValue + number2.decimalValue;
        if (number1.valueType.equals(Number.ROMAN)) {
            num = new Number(Number.ToRoman(String.valueOf(result)));
        } else {
            num = new Number(result);
        }
        return num;
    }

    public Number multiplication(Number number1, Number number2) {
        Number num;
        int result = number1.decimalValue * number2.decimalValue;
        if (number1.valueType.equals(Number.ROMAN)) {
            num = new Number(Number.ToRoman(String.valueOf(result)));
        } else {
            num = new Number(result);
        }
        return num;
    }

    public Number division(Number number1, Number number2) {
        Number num = new Number(0);
        // division to ZERO
        if (number2.decimalValue == 0) {
            num.valueType = INVALID_EXPRESSION;
            return num;
        }

        // divisibility LCD
        /*
        if (!(lcd(Math.abs(number1.decimalValue), Math.abs(number2.decimalValue)) == Math.abs(number2.decimalValue))) {
            num.valueType = INVALID_EXPRESSION;
            return num;
        }
         */

        int result = number1.decimalValue / number2.decimalValue;
        if (number1.valueType.equals(Number.ROMAN)) {
            num = new Number(Number.ToRoman(String.valueOf(result)));
        } else {
            num = new Number(result);
        }
        return num;
    }

    // division Lowest Common denominator (LCD)
    public int lcd(int a, int b) {
        int c;
        while (b!=0) {
            c = b;
            b = a % b;
            a = c;
        }
        return a;
    }

}
