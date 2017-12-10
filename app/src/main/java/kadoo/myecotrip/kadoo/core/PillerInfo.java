package kadoo.myecotrip.kadoo.core;

import kadoo.myecotrip.kadoo.IBaseModel;

/**
 * Created by divum on 10/12/17.
 */

public class PillerInfo implements IBaseModel {
    private String lat;
    private String lng;
    private String pillerName;
    private String create_piller_id;
    private String bet_id;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPillerName() {
        return pillerName;
    }

    public void setPillerName(String pillerName) {
        this.pillerName = pillerName;
    }

    public String getCreate_piller_id() {
        return create_piller_id;
    }

    public void setCreate_piller_id(String create_piller_id) {
        this.create_piller_id = create_piller_id;
    }

    public String getBet_id() {
        return bet_id;
    }

    public void setBet_id(String bet_id) {
        this.bet_id = bet_id;
    }

}
