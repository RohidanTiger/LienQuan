package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.Wallpaper;
import chickenzero.ht.com.lienquan.service.ServiceGenerator;
import chickenzero.ht.com.lienquan.views.adapters.WallpaperAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by quydv on 2/24/18.
 */

public class WallpaperFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recycle_wallpaper)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private WallpaperAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private List<Wallpaper> mListWallpaper = new ArrayList<>();

    @Override
    protected int getViewContent() {
        return R.layout.fragment_wallpaper;
    }

    @Override
    protected void initUI() {
        mAdapter = new WallpaperAdapter(mListWallpaper,context);
        mLayoutManager = new GridLayoutManager(context,2);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_smaller);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter.setLayoutManager((GridLayoutManager) mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                getListWallpaper();
            }
        });

//        mRecyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener
//                (context, mRecyclerView, new GalleryAdapter.ClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View view, int position) {
////                Bundle bundle = new Bundle();
////                bundle.putSerializable("images", images.get(position));
//                WallpaperDetailFragment newFragment =  WallpaperDetailFragment.newInstance();
//                //newFragment.setArguments(bundle);
//
//                newFragment.setSharedElementEnterTransition(new DetailsTransition());
//                newFragment.setEnterTransition(new Fade());
//                setExitTransition(new Fade());
//                newFragment.setSharedElementReturnTransition(new DetailsTransition());
//
//                newFragment.show(context.fragmentStackManager.getFragmentManager(),"Wallpaper");
//
////                context.fragmentStackManager.getFragmentManager().beginTransaction()
////                        .add(R.id.frameLayoutContainer, newFragment).addToBackStack(null).commit();
//
////                        getActivity().getSupportFragmentManager()
////                        .beginTransaction()
////                        .addSharedElement(holder.image, "kittenImage")
////                        .replace(R.id.container, kittenDetails)
////                        .addToBackStack(null)
////                        .commit();
//
//                //context.pushFragments(newFragment,null,true);
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));
    }

    private void getListWallpaper(){
        Call<List<Wallpaper>> call = ServiceGenerator.leagueService.loadWallpaperList();

        call.enqueue(new Callback<List<Wallpaper>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Wallpaper>> call, Response<List<Wallpaper>> response) {
                mListWallpaper = response.body();
                mListWallpaper.sort(new Comparator<Wallpaper>() {
                    @Override
                    public int compare(Wallpaper o1, Wallpaper o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                mAdapter.setmNumberData(mListWallpaper);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Wallpaper>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getListWallpaper();
    }
}
