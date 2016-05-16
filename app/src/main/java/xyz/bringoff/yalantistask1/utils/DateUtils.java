package xyz.bringoff.yalantistask1.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;

import xyz.bringoff.yalantistask1.R;

public class DateUtils {

    public static final long MILLISECONDS_IN_SECOND = 1000L;
    public static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * MILLISECONDS_IN_SECOND;

    public static String unixToMediumDateString(Context context, long unixDate) {
        long milliseconds = unixDate * MILLISECONDS_IN_SECOND;
        return DateFormat.getMediumDateFormat(context).format(new Date(milliseconds));
    }

    public static String unixToDateDiffString(Context context, long unixDate) {
        long milliseconds = unixDate * MILLISECONDS_IN_SECOND;

        long diff = System.currentTimeMillis() - milliseconds;
        int daysDiff = (int) (diff / MILLISECONDS_IN_DAY);
        return context.getString(
                R.string.days_gone_after_creation_format,
                daysDiff,
                context.getResources().getQuantityString(R.plurals.days_plural, daysDiff)
        );
    }
}
