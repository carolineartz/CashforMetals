/*
 * UniqueIDFactory.java
 * 
 * Utility class to generate unique ids
 *
 * @author
 * @version 1.0
 */
import java.util.Calendar;

public class UniqueIDFactory {

     /*
     * Returns a unique long number
     * 
     * @returns unique id long
     */
    public static long generateUniqueID(){
        
        return Calendar.getInstance().getTimeInMillis()/1000;
        
    }
    
}
