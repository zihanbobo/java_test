package test.open;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(GregorianCalendar.DAY_OF_YEAR, -182);
        long timeMillis = calendar.getTimeInMillis();
        System.out.println(timeMillis);
        
        System.out.println(URLEncoder.encode("我勒个擦擦擦", "utf-8"));
        System.out.println(URLEncoder.encode("2907,2908,2909", "utf-8"));
        System.out.println(URLEncoder.encode("15366189928", "utf-8"));
        
        
    }
}