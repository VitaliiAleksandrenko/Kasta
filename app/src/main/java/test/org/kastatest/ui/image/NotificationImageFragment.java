package test.org.kastatest.ui.image;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.org.kastatest1.R;

import static test.org.kastatest.notification.KastaFirebaseMessagingService.IMG_URL;

public class NotificationImageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationImageView view = (NotificationImageView) inflater.inflate(R.layout.fragment_notification_image, container, false);
        String imageUrl = getArguments().getString(IMG_URL);
        if (imageUrl != null){
            view.loadImage(imageUrl);
        }
        return view;
    }

    public static NotificationImageFragment newInstance(String imageUrl) {
        NotificationImageFragment myFragment = new NotificationImageFragment();
        Bundle args = new Bundle();
        args.putString(IMG_URL, imageUrl);
        myFragment.setArguments(args);
        return myFragment;
    }
}
