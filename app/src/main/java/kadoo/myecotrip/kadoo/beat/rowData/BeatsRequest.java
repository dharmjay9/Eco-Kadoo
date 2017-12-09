package kadoo.myecotrip.kadoo.beat.rowData;

import kadoo.myecotrip.kadoo.common.CommonRequest;

/**
 * Created by Gopal kumar on 09-12-2017.
 */

public class BeatsRequest extends CommonRequest {


    /**
     * usertype : 2
     * id : 2
     */

    private int usertype;
    private int id;

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
