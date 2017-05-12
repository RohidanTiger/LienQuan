package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.ChannelInfo;
import chickenzero.ht.com.lienquan.models.ProPlayer;
import chickenzero.ht.com.lienquan.service.ServiceGenerator;
import chickenzero.ht.com.lienquan.views.adapters.ProPlayerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by QuyDV on 5/10/17.
 */

public class ListProPlayerFragment extends BaseFragment {
    @BindView(R.id.recycle_pro_player)
    RecyclerView mRecycleProPlayer;

    private ProPlayerAdapter mAdapter;

    private List<ProPlayer> mProPlayer = new ArrayList<>();

    @Override
    protected int getViewContent() {
        return R.layout.fragment_pro_player;
    }

    @Override
    protected void initUI() {
        getLeagueList();
    }

    private void getLeagueList(){
        Call<List<ProPlayer>> call = ServiceGenerator.leagueService.loadProPlayerList();
        call.enqueue(new Callback<List<ProPlayer>>() {
            @Override
            public void onResponse(Call<List<ProPlayer>> call, Response<List<ProPlayer>> response) {
                mProPlayer = response.body();
                mAdapter = new ProPlayerAdapter(mProPlayer,context);
                GridLayoutManager mLayoutManager = new GridLayoutManager(context,2);
                mRecycleProPlayer.setLayoutManager(mLayoutManager);
                SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_smaller);
                mRecycleProPlayer.addItemDecoration(itemDecoration);
                mRecycleProPlayer.setAdapter(mAdapter);

                mAdapter.setmListener(new ProPlayerAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(ProPlayer player) {
                        Bundle bundle= new Bundle();
                        bundle.putSerializable(VideoListFragment.CHANNEL_INFO,player);
                        context.pushFragments(new VideoListFragment(),bundle,true);
                    }
                });
            }
            @Override
            public void onFailure(Call<List<ProPlayer>> call, Throwable t) {

            }
        });
    }



}
