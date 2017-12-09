package kadoo.myecotrip.kadoo.common;

import android.app.Application;
import android.content.Context;


public class MyEcoTripApplication extends Application {
    private static Context context;
    private static MyEcoTripApplication mtEcoTrippp;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static Context getAppContext() {
        return context;
    }

    public static MyEcoTripApplication getMyEcoTripApp() {
        return mtEcoTrippp;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
