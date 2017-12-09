package kadoo.myecotrip.kadoo.common;

import android.app.Application;
import android.content.Context;


public class KadooAplication extends Application {
    private static Context context;
    private static KadooAplication mtEcoTrippp;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static Context getAppContext() {
        return context;
    }

    public static KadooAplication getMyEcoTripApp() {
        return mtEcoTrippp;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
