package test.org.kastatest.data.entities;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

import test.org.kastatest.data.interfaces.ICampaignsType;

@Parcel
public class Campaign implements ICampaignsType {

    public int id = 0;
    public String name;
    public String description;

    @SerializedName("now_image")
    public String mImageUrl = "";

    @SerializedName("starts_at")
    public Date startsAt = null;

    @Override
    public int getType() {
        return ICampaignsType.TYPE_CAMPAIGN;
    }
}
