package test.org.kastatest.ui.campaigns

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import test.org.kastatest.data.RestApi
import test.org.kastatest.data.entities.CampaignList
import test.org.kastatest.data.entities.AddedCampaign
import test.org.kastatest.data.interfaces.ICampaignsType
import java.util.Date
import kotlin.collections.ArrayList

class CampaignsViewModel : ViewModel() {

    val campaigns = MutableLiveData<List<ICampaignsType>>()
    val showError = MediatorLiveData<Boolean>()

    private lateinit var restApi: RestApi

    init {
        campaigns.value = ArrayList()
        showError.value = false
    }

    fun init(restApi: RestApi) {
        this.restApi = restApi
    }

    fun loadCampaigns() {
        showError.value = false
        restApi.campaign
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CampaignList> {
                    override fun onNext(result: CampaignList) {
                        onLoad(result)
                    }

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        showError.value = true
                    }
                })
    }

    private fun onLoad(campaignList: CampaignList?) {
        val currentTime = Date()
        val newCampaigns = ArrayList<ICampaignsType>()
        if (campaignList?.items != null) {
            for (campaign in campaignList.items!!) {
                if (campaign.startsAt != null && campaign.startsAt.before(currentTime)) {
                    newCampaigns.add(campaign)
                }
            }
        }

        if (newCampaigns.size >= 2){
            newCampaigns.add(2, AddedCampaign())
        }
        campaigns.value = newCampaigns
    }

}