package kadoo.myecotrip.kadoo.base;

import com.google.gson.annotations.SerializedName;

public class CommonModel {
    @SerializedName("response")
    private CommonResponse response;

    public CommonResponse getResponse() {
        return response;
    }
}
