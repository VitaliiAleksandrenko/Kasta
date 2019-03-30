package test.org.kastatest.ui.image;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.org.kastatest1.R;

public class NotificationImageView extends ConstraintLayout {

    @BindView(R.id.single_image_from_net)
    ImageView mSingleImageFromNet;

    public NotificationImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotificationImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadImage(String imageUrl) {
        Glide.with(getContext())
                .load(imageUrl)
                .animate(R.anim.zoom_in_animation)
                .into(mSingleImageFromNet);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
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
