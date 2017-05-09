package chickenzero.ht.com.lienquan.views.fragments;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.YoutubePlayerActivity;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.LeagueItem;
import chickenzero.ht.com.lienquan.models.PlayListYoutube;
import chickenzero.ht.com.lienquan.service.ServiceGenerator;
import chickenzero.ht.com.lienquan.views.adapters.LeagueAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by QuyDV on 5/8/17.
 */

public class LeagueListFragment extends BaseFragment{
    @BindView(R.id.recycleNews)
    RecyclerView recyclerViewLeague;

    HashMap<String,PlayListYoutube> mapLeague = new HashMap<>();

    private List<LeagueItem> leagueItems = new ArrayList<>();

    LeagueAdapter mAdapter;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initUI() {
        context.showLoading(R.string.cmn_loading);
        getLeagueList(new FinishListenner() {
            @Override
            public void onFinish() {
                context.hideLoading();
                mAdapter = new LeagueAdapter(context,mapLeague);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                recyclerViewLeague.setLayoutManager(manager);
                mAdapter.setLayoutManager(manager);
                SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_small);
                recyclerViewLeague.addItemDecoration(itemDecoration);
                mAdapter.shouldShowHeadersForEmptySections(false);
                recyclerViewLeague.setAdapter(mAdapter);
                mAdapter.setListener(new LeagueAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(String videoID) {
                        Intent intent = new Intent(context,YoutubePlayerActivity.class);
                        intent.putExtra(YoutubePlayerActivity.YOUTUBE_ID,videoID);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void getLeagueList(final FinishListenner listenner){
        Call<List<LeagueItem>> call = ServiceGenerator.leagueService.loadLeagueList();
        call.enqueue(new Callback<List<LeagueItem>>() {
            @Override
            public void onResponse(Call<List<LeagueItem>> call, Response<List<LeagueItem>> response) {
                leagueItems = response.body();
                Collections.reverse(leagueItems);
                final int[] index = {0};
                for(int i = 0; i < leagueItems.size(); i++){
                    final LeagueItem item = leagueItems.get(i);
                    Call<PlayListYoutube> callListVideo = ServiceGenerator.youtubeService.
                            loadQuestions(item.getYoutube(),"AIzaSyD-47Uhd_ssBQqjRe3jIhTjBY9MkPmAOfM",50);
                    callListVideo.enqueue(new Callback<PlayListYoutube>() {
                        @Override
                        public void onResponse(Call<PlayListYoutube> call, Response<PlayListYoutube> response) {
                            index[0]++;
                            mapLeague.put(item.getName(),response.body());
                            if(index[0] == leagueItems.size()) listenner.onFinish();
                        }

                        @Override
                        public void onFailure(Call<PlayListYoutube> call, Throwable t) {
                            index[0]++;
                            mapLeague.put(item.getName(),new PlayListYoutube());
                            if(index[0] == leagueItems.size()) listenner.onFinish();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<LeagueItem>> call, Throwable t) {
                context.hideLoading();
            }
        });
    }

    private interface FinishListenner{
        public void onFinish();
    }
}
