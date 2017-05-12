package chickenzero.ht.com.lienquan.views.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.ads.AdListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.YoutubePlayerActivity;
import chickenzero.ht.com.lienquan.models.PlayListYoutube;
import chickenzero.ht.com.lienquan.models.ProPlayer;
import chickenzero.ht.com.lienquan.service.EndlessRecyclerOnScrollListener;
import chickenzero.ht.com.lienquan.service.ServiceGenerator;
import chickenzero.ht.com.lienquan.views.adapters.VideoDetailAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static chickenzero.ht.com.lienquan.YoutubePlayerActivity.YOUTUBE_ID;

/**
 * Created by QuyDV on 4/19/17.
 */

public class VideoListFragment extends BaseFragment {
    public static String CHANNEL_INFO = "channel-info";
    @BindView(R.id.listVideo)
    RecyclerView mRecyclerView;

    private VideoDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<PlayListYoutube.Item> listItems = new ArrayList<>();

    private String nextPageToken = "";

    private ProPlayer mPlayer;
    @Override
    protected int getViewContent() {
        return R.layout.fragment_list_video;
    }

    @Override
    protected void initUI() {
        mPlayer = (ProPlayer) getArguments().getSerializable(CHANNEL_INFO);
        if(mPlayer == null) return;
        context.setActionBarTitle(mPlayer.getName());
        mAdapter = new VideoDetailAdapter(context,listItems);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(new VideoDetailAdapter.OnItemClickListener() {
            @Override
            public void onClick(final PlayListYoutube.Item item) {
                if (context.mInterstitialAd.isLoaded()) {
                    context.mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(context,YoutubePlayerActivity.class);
                    intent.putExtra(YOUTUBE_ID,item.getSnippet().getResourceId().getVideoId());
                    startActivity(intent);
                }
                context.mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        context.requestNewInterstitial();
                        Intent intent = new Intent(context,YoutubePlayerActivity.class);
                        intent.putExtra(YOUTUBE_ID,item.getSnippet().getResourceId().getVideoId());
                        startActivity(intent);
                    }
                });
            }
        });

        getListVideo(mPlayer.getYoutube_list());
        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener((LinearLayoutManager) mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if(nextPageToken != null) getListVideo(mPlayer.getYoutube_list());
            }
        });
    }

    private void getListVideo(String listID){
        context.showLoading(R.string.cmn_loading);
        Call<PlayListYoutube> call = ServiceGenerator.youtubeService.
                loadQuestions(listID,"AIzaSyD-47Uhd_ssBQqjRe3jIhTjBY9MkPmAOfM",50,nextPageToken);
        call.enqueue(new Callback<PlayListYoutube>() {
            @Override
            public void onResponse(Call<PlayListYoutube> call, Response<PlayListYoutube> response) {
                mAdapter.setmDataSet(response.body().getItems());
                nextPageToken = response.body().getNextPageToken();
                context.hideLoading();
            }

            @Override
            public void onFailure(Call<PlayListYoutube> call, Throwable t) {
                context.hideLoading();
            }
        });
    }

    @Override
    public void onDestroy() {
        context.setActionBarTitle(R.string.str_challenger);
        super.onDestroy();
    }
}
