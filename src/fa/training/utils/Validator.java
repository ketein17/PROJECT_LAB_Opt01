package fa.training.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String VALID_EMAIL_REGEX="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]"+"+\\.[a-zA-Z]{2,6}$";
    /*
    * Check email address is valid
    *
    * */

    public static boolean isEmail(String emailAddress){
        Pattern pattern=Pattern.compile(VALID_EMAIL_REGEX);
        Matcher matcher=pattern.matcher(emailAddress);
        return matcher.matches();
    }

    /*
    * Check price values is valid
    *
    * */

    public static double isPrice(String price){
        double doPrice=0d;
        try{
            doPrice=Double.parseDouble(price);
        } catch(NumberFormatException exception){
            throw new NumberFormatException();
        }
        return doPrice;
    }

    /*
    * Check qtyInStock value is valid
    *
    * */

    public static int isQtyInStock(String qtyInStock){
        int intQtyInStock=0;
        try{
            intQtyInStock=Integer.parseInt(qtyInStock);
        } catch(NumberFormatException exception){
            throw new NumberFormatException();
        }
        return intQtyInStock;
    }

    /*
    * Check gender value is valid
    *
    * */

    public static boolean isGender(char gender){
        if((gender=='m')||(gender=='f')){
            return true;
        }
        else return false;
    }
}
