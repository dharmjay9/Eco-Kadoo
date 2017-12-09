package kadoo.myecotrip.kadoo.network;


/**
 * Call UI thread after getting Data
 */

public abstract class MyCallBack<T> implements MyUICallBack<T> {

    public abstract void onFailure(String s, ErrorCodes errorCodes);

    @Override
    public final void onFailure(ErrorCodes errorCodes) {
        onFailure("", errorCodes);
    }
}
