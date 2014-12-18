/*
 * StringFormatter.java
 * 
 * Utility class to format Strings
 *
 * @author
 * @version 1.0
 */
import java.util.*;
//import java.math.BigDecimal;
import java.text.DecimalFormat;

public class StringFormatter {
 
    private static DecimalFormat moneyFormat = new DecimalFormat("$0.00"); 
    //private static DecimalFormat percentFormat = new DecimalFormat("0.00"); 
    private static DecimalFormat twoDecimalFormat = new DecimalFormat("0.00"); 
    
    /*
     * Returns a money-formatted String
     * from a double
     * 
     * @param money double
     * @returns formatted String
     */
    public static String formatMoney(double money) {
        
        return moneyFormat.format(money);           
    }
    
    /*
     * Returns a date formatted String
     * from a Calendar
     * 
     * @param date Calendar 
     * @returns formatted String
     */
    public static String formatDate(Calendar date) {
        
        return (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH) + "/" + date.get(Calendar.YEAR);           
    }
    
    
    /*
     * Returns a decimal formatted String
     * from a double
     * 
     * @param weight double 
     * @returns formatted String
     */
    public static String formatTwoDecimals(double weight) {
        
        return twoDecimalFormat.format(weight);    
    }
}

