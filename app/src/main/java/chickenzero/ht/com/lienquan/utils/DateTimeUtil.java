package chickenzero.ht.com.lienquan.utils;

import java.util.Calendar;
import java.util.Date;

import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.SCApplication;

/**
 * Created by QuyDV on 4/20/17.
 */

public class DateTimeUtil {
    public static String DATE_TIME_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static String DATE_TIME_FORMAT2 = "EEE, d MMM yyyy HH:mm:ss Z";

    public static String findTimeAgo(Date d1, Date d2) {
        if (d1.getYear() != d2.getYear())
            return String.valueOf(d1.getYear() - d2.getYear()).
                    concat(SCApplication.getContext().getString(R.string.year_ago));

        if (d1.getMonth() != d2.getMonth())
            return String.valueOf(d1.getMonth() - d2.getMonth()).
                    concat(SCApplication.getContext().getString(R.string.month_ago));

        if(d1.getDay() != d2.getDay())
            return String.valueOf(d1.getDay() - d2.getDay()).
                    concat(SCApplication.getContext().getString(R.string.day_ago));

        if(d1.getHours() != d2.getHours())
            return String.valueOf(d1.getHours() - d2.getHours()).
                    concat(SCApplication.getContext().getString(R.string.hour_ago));

        if(d1.getMinutes() != d2.getMinutes())
            return String.valueOf(d1.getMinutes() - d2.getMinutes()).
                    concat(SCApplication.getContext().getString(R.string.minute_ago));

        return "";

    }

    public static String findTimeAgo(Calendar c1, Calendar c2) {
        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR))
            return String.valueOf(c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)).
                    concat(" "+SCApplication.getContext().getString(R.string.year_ago));

        if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH))
            return String.valueOf(c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH)).
                    concat(" "+SCApplication.getContext().getString(R.string.month_ago));

        if(c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH))
            return String.valueOf(c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH)).
                    concat(" "+SCApplication.getContext().getString(R.string.day_ago));

        if(c1.get(Calendar.HOUR_OF_DAY) != c2.get(Calendar.HOUR_OF_DAY))
            return String.valueOf(c1.get(Calendar.HOUR_OF_DAY) - c2.get(Calendar.HOUR_OF_DAY)).
                    concat(" "+SCApplication.getContext().getString(R.string.hour_ago));

        if(c1.get(Calendar.MINUTE) != c2.get(Calendar.MINUTE))
            return String.valueOf(c1.get(Calendar.MINUTE) - c2.get(Calendar.MINUTE)).
                    concat(" "+SCApplication.getContext().getString(R.string.minute_ago));

        return "";

    }
}
