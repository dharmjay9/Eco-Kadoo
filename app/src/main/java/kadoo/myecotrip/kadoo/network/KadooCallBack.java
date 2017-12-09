package kadoo.myecotrip.kadoo.network;


import kadoo.myecotrip.kadoo.base.CommonModel;

/**
 * Call UI thread after getting Data
 */

public abstract class KadooCallBack<T extends CommonModel> implements UICallBack<T> {

    public abstract void onFailure(String s, ErrorCodes errorCodes);

    @Override
    public final void onFailure(ErrorCodes errorCodes) {
        onFailure("", errorCodes);
    }
}
