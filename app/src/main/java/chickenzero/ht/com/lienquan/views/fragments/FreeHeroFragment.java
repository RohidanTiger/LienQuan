package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.FreeHero;
import chickenzero.ht.com.lienquan.service.FreeHeroRequest;
import chickenzero.ht.com.lienquan.utils.ConnectivityReceiver;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;
import chickenzero.ht.com.lienquan.views.adapters.FreeHeroAdapter;

/**
 * Created by QuyDV on 5/22/17.
 */

public class FreeHeroFragment extends BaseFragment implements ConnectivityReceiver.ConnectivityReceiverListener{

    @BindView(R.id.recycle_free_hero)
    RecyclerView recyclerViewHero;

    @BindView(R.id.img_content)
    ImageView imgContent;

    private FreeHeroAdapter mAdapter;

    private List<FreeHero> heroList;

    private String url = "https://lienquan.garena.vn";

    String str1 = "<html><head><style>.title{color:red;} .type{color:#038000;}a{color:#285afd; text-decoration:" +
            "none;pointer-events: none;cursor: default;}</style></head><body>";
    private String str2 = "</body></html>";

    private LoaderManager.LoaderCallbacks<List<FreeHero>> newsListener = new LoaderManager.LoaderCallbacks<List<FreeHero>>(){

        @Override
        public Loader<List<FreeHero>> onCreateLoader(int id, Bundle args) {
            return new FreeHeroRequest(context,url);
        }

        @Override
        public void onLoadFinished(Loader<List<FreeHero>> loader, List<FreeHero> data) {
            context.hideLoading();
            if(heroList != null) heroList.clear();
            heroList = data;
            if(heroList == null || heroList.size() == 0) {
                Toast.makeText(context,context.getResources().getString(R.string.cmn_no_response),Toast.LENGTH_LONG).show();
                return;
            }
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.setmNumberData(heroList);
                    recyclerViewHero.setAdapter(mAdapter);
                    PicassoLoader.getInstance(context).with(context).load(heroList.get(0).getContent_hero()).into(imgContent);
                }
            });
        }

        @Override
        public void onLoaderReset(Loader<List<FreeHero>> loader) {

        }
    };

    @Override
    protected int getViewContent() {
        return R.layout.fragment_free_hero;
    }

    @Override
    protected void initUI() {
        heroList = new ArrayList<>();
        mAdapter = new FreeHeroAdapter(heroList,context);
        GridLayoutManager mLayoutManager = new GridLayoutManager(context,3);
        recyclerViewHero.setLayoutManager(mLayoutManager);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_small);
        recyclerViewHero.addItemDecoration(itemDecoration);
        mAdapter.setOnItemClickListener(new FreeHeroAdapter.OnItemClickListener() {
            @Override
            public void onClick(FreeHero position) {
                PicassoLoader.getInstance(context).with(context).load(position.getContent_hero()).into(imgContent);
            }
        });
        context.showLoading(R.string.cmn_loading);
        context.getSupportLoaderManager().initLoader(20, null, newsListener).forceLoad();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isConnected){
            context.getSupportLoaderManager().restartLoader(20, null, newsListener).forceLoad();
        }else{
            Toast.makeText(context,context.getResources().getString(R.string.cmn_no_internet_access),Toast.LENGTH_LONG).show();
        }
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
}
