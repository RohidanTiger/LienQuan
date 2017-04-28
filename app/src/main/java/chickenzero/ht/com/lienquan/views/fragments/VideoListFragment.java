package chickenzero.ht.com.lienquan.views.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.YoutubePlayerActivity;
import chickenzero.ht.com.lienquan.models.PlayListYoutube;
import chickenzero.ht.com.lienquan.service.ServiceGenerator;
import chickenzero.ht.com.lienquan.views.adapters.VideoDetailAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by QuyDV on 4/19/17.
 */

public class VideoListFragment extends BaseFragment {
    @BindView(R.id.listVideo)
    RecyclerView mRecyclerView;

    private VideoDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<PlayListYoutube.Item> listItems = new ArrayList<>();
    @Override
    protected int getViewContent() {
        return R.layout.fragment_list_video;
    }

    @Override
    protected void initUI() {
        mAdapter = new VideoDetailAdapter(context,listItems);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(new VideoDetailAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(context,YoutubePlayerActivity.class);
                startActivity(intent);
            }
        });
        getListVideo();
    }

    private void getListVideo(){
        Call<PlayListYoutube> call = ServiceGenerator.youtubeService.
                loadQuestions("PLGHhjtXdaONf-g8SDMsRxvEvi2rwux7xR","AIzaSyD-47Uhd_ssBQqjRe3jIhTjBY9MkPmAOfM",50);
        call.enqueue(new Callback<PlayListYoutube>() {
            @Override
            public void onResponse(Call<PlayListYoutube> call, Response<PlayListYoutube> response) {
                mAdapter.setmDataSet(response.body().getItems());
            }

            @Override
            public void onFailure(Call<PlayListYoutube> call, Throwable t) {

            }
        });
    }
}
