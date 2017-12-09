package kadoo.myecotrip.kadoo.network;

import android.util.Log;


import org.json.JSONException;

import java.io.IOException;

import kadoo.myecotrip.kadoo.base.CommonModel;
import retrofit2.Callback;
import retrofit2.Response;


public class NetWorkCallBack<T extends CommonModel> implements Callback<T> {


    private static final String TAG = NetWorkCallBack.class.getName();
    private MyEcoTripCallBack<T> mUiCallBack;

    NetWorkCallBack(MyEcoTripCallBack<T> mUiCallBack) {
        this.mUiCallBack = mUiCallBack;
    }



    @Override
    public void onResponse(Response<T> response) {

        if (response.isSuccess()) {
            T commonModel = response.body();
            mUiCallBack.onSuccess(commonModel);
            return;
        }
        //TODO handle response
        switch (response.code()) {
            case 400:
                mUiCallBack.onFailure(ErrorCodes.BAD_INPUT);
                return;
            case 500:
                mUiCallBack.onFailure(ErrorCodes.INTERNAL_SERVER_ERROR);
                return;
            default:
                mUiCallBack.onFailure(ErrorCodes.UNKONN_ERROR);
                return;

        }
    }

    @Override
    public void onFailure(Throwable t) {

        if (t instanceof IOException) {
            mUiCallBack.onFailure(ErrorCodes.NO_NETWORK);
            return;
        } else if (t instanceof JSONException) {
            mUiCallBack.onFailure(ErrorCodes.JSON_SYNTAX_MISMATCH);
            return;
        }
        Log.d(TAG, t.getMessage());

    }
}
