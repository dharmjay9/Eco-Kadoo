package kadoo.myecotrip.kadoo.core;


import kadoo.myecotrip.kadoo.IBaseModel;

public class NFCWrite implements IBaseModel {
    private String tag_id;
    private String tag_description;
    private String owner_of_tag;
    private String species_of_tree;
    private String date_of_plantation;
    private double lat;
    private double lang;
    public String getOwner_of_tag() {
        return owner_of_tag;
    }

    public void setOwner_of_tag(String owner_of_tag) {
        this.owner_of_tag = owner_of_tag;
    }

    public String getSpecies_of_tree() {
        return species_of_tree;
    }

    public void setSpecies_of_tree(String species_of_tree) {
        this.species_of_tree = species_of_tree;
    }

    public String getDate_of_plantation() {
        return date_of_plantation;
    }

    public void setDate_of_plantation(String date_of_plantation) {
        this.date_of_plantation = date_of_plantation;
    }


    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_description() {
        return tag_description;
    }

    public void setTag_description(String tag_description) {
        this.tag_description = tag_description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }


}
