package Common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Common {
    public static boolean CheckPassWord(String pass) {
        boolean checkLenght = false;
        boolean checkLetter = false;
        boolean checkDigit = false;
        boolean checkSpecialChar = false;
        boolean checkAll = false;
        boolean checkDigitLength = false;
        int passLength = pass.length();
        int demSo = 0;
        int demUpperCase = 0;
        int demLowerCase = 0;
        boolean checkUpperCase = false;
        boolean checkLowerCase = false;

        if (passLength >= 8) { // Sua do dai cua pass
            checkLenght = true;
        }
        for (int i = 0; i < pass.length(); i++) {
            char letter = pass.charAt(i);
            if (Character.isLetter(letter) == true) {
                checkLetter = true;
            } else if (Character.isDigit(letter) == true) {
                checkDigit = true;
            }
            else {
                checkSpecialChar = true;
            }
            if (checkLetter == true && checkDigit == true && checkSpecialChar == true) {
                break;
            }
        }
        for (int n = 0; n < pass.length(); n++) {
            char digit = pass.charAt(n);
            if (Character.isDigit(digit) == true) {
                demSo++;
            }
            if (demSo == 3) {
                checkDigitLength = true;
                break;
            }
        }
        for( int m = 0; m < pass.length(); m++){
            char upperCase = pass.charAt(m);
            if(Character.isUpperCase(upperCase)){
                demUpperCase++;
            }
            if(demUpperCase == 2){
                checkUpperCase = true;
                break;
            }
        }
        for(int q = 0; q < pass.length(); q++){
            char lowerCase = pass.charAt(q);
            if(Character.isLowerCase(lowerCase)){
                demLowerCase++;
            }
            if(demLowerCase == 4){
                checkLowerCase = true;
                break;
            }
        }
        if(checkLetter == true && checkDigit == true && checkLenght == true && checkDigitLength == true && checkSpecialChar == true && checkLowerCase == true && checkUpperCase == true){
            checkAll = true;
        }
        return  checkAll;
    }
    public static String convertToString(InputStream in) {
        // TODO Auto-generated method stub
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
