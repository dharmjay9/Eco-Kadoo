package kadoo.myecotrip.kadoo.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import kadoo.myecotrip.kadoo.KadooApp;

/**
 * Created by divum on 4/3/17.
 */

public class KadooLocalUser {

    private static SharedPreferences sharedPreferences;
    private static KadooLocalUser kadooLocalUser;
    private static final String SELECTED_CATEGORY = "selected_category";
    private static final String KADOO_USER = "kaadoo_user";


    public static KadooLocalUser getInstance() {
        if (kadooLocalUser == null) {
            kadooLocalUser = new KadooLocalUser();
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(KadooApp.getAppContext());
        }
        return kadooLocalUser;

    }

    public void setSelectedCategory(String email) {
        putString(SELECTED_CATEGORY, email);
    }

    public String getSelectedCategory() {
        return sharedPreferences.getString(SELECTED_CATEGORY, "");
    }

    public void setUser(String email) {
        putString(KADOO_USER, email);
    }

    public String getUser() {
        return sharedPreferences.getString(KADOO_USER, "");
    }


    private void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

}
