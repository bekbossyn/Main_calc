public class Calculator {

    public String calculate(String text) {
        int sign = 1;
        // first number negative
        if (text.charAt(0)=='-'){
            sign = -1;
            text = text.substring(1);
        }
        String str1 = "";
        String str2 = "";
        String operation = "";
        if (text.substring(0).contains("-")) {
            str1=text.substring(0,text.indexOf("-"));
            str2=text.substring(text.indexOf("-")+1);
            operation="-";
        } else if (text.substring(0).contains("*")) {
            str1=text.substring(0,text.indexOf("*"));
            str2=text.substring(text.indexOf("*")+1);
            operation="*";
        } else if (text.substring(0).contains("+")) {
            str1 = text.substring(0, text.indexOf("+"));
            str2 = text.substring(text.indexOf("+") + 1);
            operation = "+";
        }  else if (text.substring(0).contains("/")) {
            str1 = text.substring(0, text.indexOf("/"));
            str2 = text.substring(text.indexOf("/") + 1);
            operation = "/";
        }

        // first number negative
        if (sign == -1) {
            str1 =  "-" + str1;
        }

        Number num1=new Number(str1);

        Number num2=new Number(str2);

        int result = 0;

        if (operation.equals("-")) { // subtraction
            result = num1.intValue - num2.intValue;
        } else if (operation.equals("+")) { // addition
            result = num1.intValue + num2.intValue;
        }  else if (operation.equals("*")) { // multiplication
            result = num1.intValue * num2.intValue;
        } else if (operation.equals("/")) { // division
            // #TODO need factor checking later on
            result = num1.intValue / num2.intValue;
        }
        Number num3 = new Number(result);

        if (num1.roman == num2.roman && num1.roman!=2) {
            num3.roman = num1.roman;
        } else {
            return "Error";
        }

        if (num1.roman == 1) { // Roman
            return num3.ToRoman(num3.intValue);
        } else {
            return String.valueOf(num3.intValue);
        }
    }
}
