package xyz.bringoff.yalantistask1.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;

import xyz.bringoff.yalantistask1.R;

public class DateUtils {

    public static final int MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

    public static String unixToMediumDateString(Context context, long unixDate) {
        return DateFormat.getMediumDateFormat(context).format(new Date(unixDate));
    }

    public static String unixToDateDiffString(Context context, long unixDate) {
        long diff = System.currentTimeMillis() - unixDate;
        int daysDiff = (int) (diff / MILLISECONDS_IN_DAY);
        return context.getString(
                R.string.days_gone_after_creation_format,
                daysDiff,
                context.getResources().getQuantityString(R.plurals.days_plural, daysDiff)
        );
    }
}
