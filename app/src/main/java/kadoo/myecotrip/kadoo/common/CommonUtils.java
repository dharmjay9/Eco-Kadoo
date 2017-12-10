package kadoo.myecotrip.kadoo.common;

import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gopal kumar on 04-06-2017.
 */

public class CommonUtils {

    public static String getImageUrl(String subUrl) {
        return IConstant.BASE_URL + subUrl;
    }

    public static String getEcoTrailBaseUrl(String subUrl) {
        subUrl = subUrl.trim();
        return IConstant.BASE_URL + "/public/images/ecotrails/" + subUrl;//.substring(0,subUrl.length()-4);
    }

    public static String getDateInFormate(String date) {
        try {
            DateFormat utcFormat = new SimpleDateFormat(IConstant.IP_DATE_FORMATER);
            // utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            // DateFormat indianFormat = new SimpleDateFormat(IConstant.OP_DATE_FORMATER);
            //  indianFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
            Date timestamp = utcFormat.parse(date);
            return DateUtils.getRelativeTimeSpanString(timestamp.getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getModifyString(String string) {
        if (TextUtils.isEmpty(string)) return "";
        if (string.length() <= 3) return string;
        return string.substring(0, 3);

    }
}
