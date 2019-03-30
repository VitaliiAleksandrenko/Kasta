package test.org.kastatest.ui.campaigns;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.org.kastatest.data.entities.AddedCampaign;
import test.org.kastatest1.R;

public class AddedCampaignItemView extends ConstraintLayout {

    private final static float ASPECT_RATIO = 0.55f;

    @BindView(R.id.custom_image_from_net)
    ImageView image;

    public AddedCampaignItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddedCampaignItemView(Context context, AttributeSet attrs, int defStyleAttr) {
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

    public void bindTo(AddedCampaign campaign) {
        if (TextUtils.isEmpty(campaign.getmImageUrl())) {
            image.setImageDrawable(null);
        } else {
            Glide.with(getContext())
                    .load(campaign.getmImageUrl())
                    .into(image);
        }
    }
}
