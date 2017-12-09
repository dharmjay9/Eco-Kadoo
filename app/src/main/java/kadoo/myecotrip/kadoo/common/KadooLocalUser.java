package kadoo.myecotrip.kadoo.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by divum on 4/3/17.
 */

public class KadooLocalUser {

    private static SharedPreferences sharedPreferences;
    private static KadooLocalUser kadooLocalUser;
    private static final String EMAIL_ID = "email_id";
    private static final String USER_ID = "user_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String COUNTRY = "country";
    private static final String MOBILE_NO = "mobile";
    private static final String ORDER_ID = "order_id";
    private static final String SELECTED_CATEGORY = "selected_category";


    public static KadooLocalUser getInstance(Context context) {
        if (kadooLocalUser == null) {
            kadooLocalUser = new KadooLocalUser();
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return kadooLocalUser;

    }

    public void setEmail(String email) {
        putString(EMAIL_ID, email);
    }

    public String getEmailId() {
        return sharedPreferences.getString(EMAIL_ID, "");
    }

    public void setFirstName(String email) {
        putString(FIRST_NAME, email);
    }

    public String getFirstName() {
        return sharedPreferences.getString(FIRST_NAME, "Guest");
    }

    public void setLastName(String email) {
        putString(LAST_NAME, email);
    }

    public String getLastName() {
        return sharedPreferences.getString(LAST_NAME, "User");
    }

    public void setCountry(String email) {
        putString(COUNTRY, email);
    }

    public String getCountry() {
        return sharedPreferences.getString(COUNTRY, "");
    }

    public void setOrderId(String email) {
        putString(ORDER_ID, email);
    }

    public String gerOrderId() {
        return sharedPreferences.getString(ORDER_ID, "");
    }

    public void setMobileNo(String email) {
        putString(MOBILE_NO, email);
    }

    public String getMobileNo() {
        return sharedPreferences.getString(MOBILE_NO, "");
    }


    public void setUserId(String email) {
        putString(USER_ID, email);
    }

    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    public void setSelectedCategory(String email) {
        putString(SELECTED_CATEGORY, email);
    }

    public String getSelectedCategory() {
        return sharedPreferences.getString(SELECTED_CATEGORY, "");
    }


    private void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

}
