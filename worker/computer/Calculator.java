/*
 * This is a utilitary class that has raw methods to manipulate 
 * arithmetic operations between integers and strings
 */
package worker.computer;

public class Calculator {

    private static String multiplyStringByDigit(String number, int d) {
        String res = "";
        int tmp = 0;
        for(int i = number.length()-1; i >= 0; i--) {
            int op = (number.charAt(i) - '0') * d + tmp;
            res = (int) Math.floor(op % 10) + res;
            tmp = (int) Math.floor(op/10);
        }
        if(tmp != 0) res = tmp + res;
        return res;
    }

    private static String multiplyDigitsOf(String number) {
        String res = "1";
        if(number.contains("0")){
            res = "0";
        } else {
            for(int i = 0; i < number.length(); i++){
                res = multiplyStringByDigit(res, number.charAt(i) - '0');
            }
        }
        return res;
    }

    public static int calculatePersistanceOf(String number) {
        int p = 0;
        while(number.length() > 1) {
            number = multiplyDigitsOf(number);
            p++;
        }
        return p;
    }

    public static String incrementNumber(String number) {
        int index = number.length() - 1;
        while(index >= 0 && (number.charAt(index) == '9')) {
            number = replaceChar(number, index, '0');
            index--;
        }
        if(index >= 0) {
            number = replaceChar(number, index, (char) (number.charAt(index) + 1));
        } else {
            number = "1" + number;
        }
        return number;
    }

    private static String replaceChar(String str, int index, char c) {
        return str.substring(0, index) + c + str.substring(index + 1);
    }

    public static String addIntervalToString(String number, String range) {        // "987398263" + 10000 = "987408263"
        int zerosInRange = range.length() - 1;
        String result = incrementNumber(number.substring(0, number.length()-zerosInRange));
        result += number.substring(number.length() - zerosInRange);
        return result;
    }
}

