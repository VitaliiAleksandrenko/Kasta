package test.org.kastatest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import test.org.kastatest.ui.image.NotificationImageFragment;
import test.org.kastatest1.R;

import static test.org.kastatest.notification.KastaFirebaseMessagingService.IMG_URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkExistenceImageUrl(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkExistenceImageUrl(intent);
    }

    private void checkExistenceImageUrl(Intent intent){
        boolean hasImageUrl = intent.getExtras() != null && intent.getExtras().getString(IMG_URL) != null;
        if (hasImageUrl){
            String imageUrl = intent.getExtras().getString(IMG_URL);
            NotificationImageFragment notificationImageFragment = NotificationImageFragment.newInstance(imageUrl);
            replaceFragment(notificationImageFragment);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_main, fragment);
        transaction.commitAllowingStateLoss();
    }
}
