package kadoo.myecotrip.kadoo;


import zinka.com.permissionlib.ErrorCode;
import zinka.com.permissionlib.PermissionCallBack;

public abstract class PermissionImpl implements PermissionCallBack {
    @Override
    public void permissionApproved(String... permissions) {
        for (String approved : permissions) {
            onApproved(approved);
        }
    }

    public abstract void onApproved(String str);

    @Override
    public void permissionDenied(String... permissionName) {

    }

    @Override
    public void permissionNotInManifest(String... permissionName) {

    }

    @Override
    public void onError(ErrorCode errorCode) {

    }
}

