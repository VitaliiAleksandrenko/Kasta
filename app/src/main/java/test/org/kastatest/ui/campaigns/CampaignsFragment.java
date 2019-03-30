package test.org.kastatest.ui.campaigns;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit.Ok3Client;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import test.org.kastatest.data.RestApi;
import test.org.kastatest.data.facilities.RestDateTypeAdapter;
import test.org.kastatest1.BuildConfig;
import test.org.kastatest1.R;

public class CampaignsFragment extends Fragment {

    private RestApi mRestApi;

    private CampaignsViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new RestDateTypeAdapter("yyyy-MM-dd'T'HH:mm:ss"))
                .create();

        mRestApi = new RestAdapter.Builder()
                .setClient(new Ok3Client(httpClientBuilder.build()))
                .setConverter(new GsonConverter(gson))
                .setEndpoint(Endpoints.newFixedEndpoint("https://kasta.ua"))
                .build()
                .create(RestApi.class);

        viewModel = ViewModelProviders.of(this).get(CampaignsViewModel.class);
        viewModel.init(mRestApi);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CampaignsView view = (CampaignsView)inflater.inflate(R.layout.fragment_main_content, container, false);
        view.setup(viewModel, this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadCampaigns();
    }

}
