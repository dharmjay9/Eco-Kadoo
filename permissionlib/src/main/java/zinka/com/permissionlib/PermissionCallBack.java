package zinka.com.permissionlib;


public interface PermissionCallBack {

    void permissionApproved(String... permissions);

    void permissionDenied(String... permissionName);

    void permissionNotInManifest(String... permissionName);

    void onError(ErrorCode errorCode);


}
