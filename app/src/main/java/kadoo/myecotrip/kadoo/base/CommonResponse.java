package kadoo.myecotrip.kadoo.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gopal kumar on 09-12-2017.
 */

public class CommonResponse {


    /**
     * status-code : 200 OK
     * error : 0
     * sys_msg :
     * message : Success
     */

    @SerializedName("status-code")
    private String statuscode;
    private String error;
    private String sys_msg;
    private String message;

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getSys_msg() {
        return sys_msg;
    }

    public void setSys_msg(String sys_msg) {
        this.sys_msg = sys_msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
