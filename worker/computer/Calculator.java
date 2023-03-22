package worker.computer;

public class Calculator {

    private String multiplyStringByDigit(String number, int d) {
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

    private String multiplyDigitsOf(String number) {
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

    public int calculatePersistanceOf(String number) {
        int p = 0;
        System.out.print("\ninitial number : " + number + "\n");
        while(number.length() > 1) {
            number = multiplyDigitsOf(number);
            p++;
        }
        return p;
    }

    public String incrementNumber(String number) {
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

    public String replaceChar(String str, int index, char c) {
        return str.substring(0, index) + c + str.substring(index + 1);
    }

}

