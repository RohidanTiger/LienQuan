package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.models.NewsList;
import chickenzero.ht.com.lienquan.service.ServiceGenerator;
import chickenzero.ht.com.lienquan.views.adapters.NewsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by QuyDV on 4/20/17.
 */

public class NewsFrament extends BaseFragment{

    @BindView(R.id.listVideo)
    RecyclerView mRecyclerView;

    private NewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NewsList.Item> listItems = new ArrayList<>();
    @Override
    protected int getViewContent() {
        return R.layout.fragment_list_video;
    }

    @Override
    protected void initUI() {
        mAdapter = new NewsAdapter(context,listItems);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onClick(NewsList.Item item, int pos) {
                Bundle bundle = new Bundle();
                bundle.putString(NewsDetailFragment.URL_DETAIL,item.getLink());
                bundle.putInt(NewsDetailFragment.URL_POSITION,pos);
                context.pushFragments(new NewsDetailFragment(),bundle,true,true);
            }
        });
        getListNews();
    }

    private void getListNews(){
        Call<NewsList> call = ServiceGenerator.newsService.loadListNew();
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                mAdapter.setmDataSet(response.body().getChannel().getItem());
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("Data",t.toString());
            }
        });

    }


}
