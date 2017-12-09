package kadoo.myecotrip.kadoo.login;

/**
 * Created by Gopal kumar on 09-12-2017.
 */

public class KadooUser {


    /**
     * user_id : 1
     * beat_id : 2
     * latitude : 12.83
     * longitude : 17.23
     * displayName : Pillar1
     * id : 3
     */

    private String user_id;
    private String beat_id;
    private String latitude;
    private String longitude;
    private String displayName;
    private int id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBeat_id() {
        return beat_id;
    }

    public void setBeat_id(String beat_id) {
        this.beat_id = beat_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
