package utils;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Business_Book on 10.04.2016.
 */
public class DateUtils {

    private static final Logger logger = Logger.getLogger(DateUtils.class);

    static final SimpleDateFormat DATE_FORMAT_RFC3339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String convertToRFC3339(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_RFC3339.toPattern());
        return dateFormat.format(date);
    }
}
