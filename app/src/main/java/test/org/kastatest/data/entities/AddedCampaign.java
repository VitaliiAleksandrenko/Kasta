package test.org.kastatest.data.entities;

import test.org.kastatest.data.interfaces.ICampaignsType;

public class AddedCampaign implements ICampaignsType {


    private String mImageUrl = "https://kasta.ua/imgw/loc/0x0/uploads/banners/9ccec5aa71de4b919e6a2d310a2a0908.jpg";

    private int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    @Override
    public int getType() {
        return ICampaignsType.TYPE_ADDED_CAMPAIGN;
    }
}
