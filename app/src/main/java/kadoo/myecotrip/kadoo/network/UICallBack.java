package kadoo.myecotrip.kadoo.network;


import kadoo.myecotrip.kadoo.base.CommonModel;

/**
 * Use for callback passing data from netwotk call to UI
 */

public interface UICallBack<T extends CommonModel> {

    void onSuccess(T t);

    void onFailure(ErrorCodes errorCodes);
}
