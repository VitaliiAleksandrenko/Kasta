package test.org.kastatest.ui.campaigns;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.org.kastatest1.R;

public class CampaignsView extends ConstraintLayout {

    @BindView(R.id.campaigns_list)
    RecyclerView mCampaignsList;

    private CampaignsViewModel mViewModel;
    private final CampaignsAdapter mAdapter = new CampaignsAdapter();

    public CampaignsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CampaignsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setup(CampaignsViewModel viewModel, LifecycleOwner lifecycleOwner) {
        mViewModel = viewModel;
        mViewModel.getCampaigns().observe(lifecycleOwner, (campaigns)->{
            mAdapter.replaceWith(campaigns);
        });
        mViewModel.getShowError().observe(lifecycleOwner, (error)-> {
            if (error) {
                Toast toast = new Toast(getContext());
                toast.setText(R.string.internet_error);
                toast.show();
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        mCampaignsList.setAdapter(mAdapter);
        mCampaignsList.setHasFixedSize(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

}
