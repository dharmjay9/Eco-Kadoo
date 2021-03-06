package kadoo.myecotrip.kadoo.beat.rowData;

import java.util.List;

import kadoo.myecotrip.kadoo.base.CommonModel;

/**
 * Created by Gopal kumar on 09-12-2017.
 */

public class SubDivisionResponse extends CommonModel {


    /**
     * content : [{"id":2,"name":"Circle2","isActive":1}]
     * response : {"status-code":"200 OK","error":"0","sys_msg":"","message":"Success"}
     */

    private List<SubDivisionRowData> content;

    public List<SubDivisionRowData> getContent() {
        return content;
    }
}
