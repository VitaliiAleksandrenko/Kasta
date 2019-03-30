package test.org.kastatest.data;

import retrofit.http.GET;
import rx.Observable;
import test.org.kastatest.data.entities.CampaignList;

public interface RestApi {

    @GET("/api/v2/campaigns")
    Observable<CampaignList> getCampaign();

}
