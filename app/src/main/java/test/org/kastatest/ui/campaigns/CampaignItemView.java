package test.org.kastatest.ui.campaigns;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.org.kastatest1.R;
import test.org.kastatest.data.entities.Campaign;

public class CampaignItemView extends ConstraintLayout {

    private final static float ASPECT_RATIO = 0.55f;

    @BindView(R.id.campaign_name)
    TextView textName;

    @BindView(R.id.image_from_net)
    ImageView image;

    public CampaignItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CampaignItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int heightAspectRatio = (int)(width * ASPECT_RATIO);
        if (width != 0 && height != heightAspectRatio) {
            setMeasuredDimension(width, heightAspectRatio);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightAspectRatio, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(Campaign campaign, int position) {
        textName.setText(campaign.name);
        if (TextUtils.isEmpty(campaign.mImageUrl)) {
            image.setImageDrawable(null);
        } else {
            Glide.with(getContext())
                    .load("https://m.cdnmk.net/imgw/loc/360x198/" + campaign.mImageUrl)
                    .into(image);
        }
    }

}
