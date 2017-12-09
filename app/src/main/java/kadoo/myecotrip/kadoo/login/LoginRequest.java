package kadoo.myecotrip.kadoo.login;

import kadoo.myecotrip.kadoo.common.CommonRequest;

/**
 * Created by Gopal kumar on 09-12-2017.
 */

public class LoginRequest extends CommonRequest {


    /**
     * userName : user4@gmail.com
     * password : 123
     */

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
