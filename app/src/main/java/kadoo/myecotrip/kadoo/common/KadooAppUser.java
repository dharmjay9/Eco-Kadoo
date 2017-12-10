package kadoo.myecotrip.kadoo.common;

import android.net.LinkAddress;
import android.text.TextUtils;

import com.google.gson.Gson;

import kadoo.myecotrip.kadoo.login.KadooUser;
import kadoo.myecotrip.kadoo.login.LoginResponse;

/**
 * Created by Gopal kumar on 10-12-2017.
 */

public class KadooAppUser {

    private static KadooAppUser kadooAppUser;


    private static KadooUser kadooUser;

    private SelectedBeatData selectedBeatData;

    public static KadooAppUser getInstnace() {
        if (kadooAppUser == null) {
            kadooAppUser = new KadooAppUser();

        }
        return kadooAppUser;
    }

    public KadooUser getKadooAppUser() {
        if (kadooUser == null) {
            KadooLocalUser kadooLocalUser = KadooLocalUser.getInstance();
            String kadooAppUser = kadooLocalUser.getUser();
            Gson gson = new Gson();
            if (!TextUtils.isEmpty(kadooLocalUser.getUser())) {
                kadooUser = gson.fromJson(kadooAppUser, KadooUser.class);
            }

        }
        return kadooUser;
    }

    public SelectedBeatData getSelectedBeatData() {

        if (selectedBeatData == null) {
            KadooLocalUser kadooLocalUser = KadooLocalUser.getInstance();
            String selectdData = kadooLocalUser.getSelectedCategory();
            Gson gson = new Gson();
            if (!TextUtils.isEmpty(kadooLocalUser.getSelectedCategory())) {
                selectedBeatData = gson.fromJson(selectdData, SelectedBeatData.class);

            }
        }
        return selectedBeatData;
    }
}
