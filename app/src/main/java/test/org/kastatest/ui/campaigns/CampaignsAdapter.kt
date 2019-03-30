package test.org.kastatest.ui.campaigns

import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.org.kastatest.data.entities.AddedCampaign
import test.org.kastatest.data.entities.Campaign
import test.org.kastatest.data.interfaces.ICampaignsType


class CampaignsAdapter : RecyclerView.Adapter<CampaignsAdapter.ViewHolder>() {

    init {
        setHasStableIds(true)
    }

    private var campaigns : List<ICampaignsType> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ICampaignsType.TYPE_CAMPAIGN) {
            ViewHolder(LayoutInflater.from(parent.context).inflate(test.org.kastatest1.R.layout.campaign_item, parent, false))
        }else{
            ViewHolder(LayoutInflater.from(parent.context).inflate(test.org.kastatest1.R.layout.added_campaign_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder.itemView) {
            is CampaignItemView -> holder.itemView.bindTo(campaigns[position] as Campaign, position)
            is AddedCampaignItemView -> holder.itemView.bindTo(campaigns[position] as AddedCampaign)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return campaigns[position].type
    }

    override fun getItemId(position: Int): Long {
        with(campaigns[position]){
            return if (type == ICampaignsType.TYPE_CAMPAIGN){
                (this as Campaign).id.toLong()
            }else{
                (this as AddedCampaign).id.toLong()
            }
        }
    }

    override fun getItemCount(): Int {
        return campaigns.size
    }

    fun replaceWith(@Nullable items: List<ICampaignsType>?) {
        if (items == null) {
            this.campaigns = java.util.ArrayList()
        } else {
            this.campaigns = items
        }
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
