package kadoo.myecotrip.kadoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;


import kadoo.myecotrip.kadoo.common.KadooAppUser;
import kadoo.myecotrip.kadoo.home.HomeActivity;
import kadoo.myecotrip.kadoo.home.HomeFragment;
import kadoo.myecotrip.kadoo.login.LoginActivity;

/**
 * Created by Gopal kumar on 18-04-2017.
 */

public class SplashActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (KadooAppUser.getInstnace().getKadooAppUser() == null)
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));

                finish();

            }
        }, 2000);
    }
}
