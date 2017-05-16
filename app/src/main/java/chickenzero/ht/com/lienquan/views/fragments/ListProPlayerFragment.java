package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.ChannelInfo;
import chickenzero.ht.com.lienquan.models.ProPlayer;
import chickenzero.ht.com.lienquan.service.ServiceGenerator;
import chickenzero.ht.com.lienquan.utils.ConnectivityReceiver;
import chickenzero.ht.com.lienquan.views.adapters.ProPlayerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by QuyDV on 5/10/17.
 */

public class ListProPlayerFragment extends BaseFragment implements ConnectivityReceiver.ConnectivityReceiverListener{
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
        context.showLoading(R.string.cmn_loading);
        Call<List<ProPlayer>> call = ServiceGenerator.leagueService.loadProPlayerList();
        call.enqueue(new Callback<List<ProPlayer>>() {
            @Override
            public void onResponse(Call<List<ProPlayer>> call, Response<List<ProPlayer>> response) {
                context.hideLoading();
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
                context.hideLoading();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        setHasOptionsMenu(true);
        context.setConnectivityListener(this);
        if(!ConnectivityReceiver.isConnected()){
            Toast.makeText(context,context.getResources().getString(R.string.cmn_no_internet_access),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isConnected){
            getLeagueList();
        }else{
            Toast.makeText(context,context.getResources().getString(R.string.cmn_no_internet_access),Toast.LENGTH_LONG).show();
        }
    }
}
