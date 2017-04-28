package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.CarouselPageTransformer;
import chickenzero.ht.com.lienquan.customize.PagerContainer;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.customize.TransformableViewPager;
import chickenzero.ht.com.lienquan.models.Hero;
import chickenzero.ht.com.lienquan.views.adapters.HeroCarouselAdapter;
import chickenzero.ht.com.lienquan.views.adapters.HeroGridAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by QuyDV on 4/17/17.
 */

public class HeroFragment extends BaseFragment {
    @BindView(R.id.recycleHero)
    RecyclerView recyclerViewHeros;

    @BindView(R.id.btn_change_type)
    ImageButton btnChangeType;

    @BindView(R.id.pager_container)
    PagerContainer mContainer;
    TransformableViewPager pager;

    private boolean carousel = false;

    private HeroGridAdapter heroGridAdapterAdapter;


    private List<Hero> listHero;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected int getViewContent() {
        return R.layout.fragment_hero;
    }

    @Override
    protected void initUI() {
        requestHeroes(context.realm);

        pager = mContainer.getViewPager();
        heroGridAdapterAdapter = new HeroGridAdapter(listHero,context);
        mLayoutManager = new GridLayoutManager(context,3);
        recyclerViewHeros.setLayoutManager(mLayoutManager);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_smaller);
        recyclerViewHeros.addItemDecoration(itemDecoration);
        recyclerViewHeros.setAdapter(heroGridAdapterAdapter);

        heroGridAdapterAdapter.setOnItemClickListener(new HeroGridAdapter.OnItemClickListener() {
            @Override
            public void onClick(Hero hero) {
                Bundle bundle = new Bundle();
                bundle.putString(HeroDetailFragment.HERO_DETAIL,hero.getId());
                context.pushFragments(new HeroDetailFragment(),bundle,true,true);
                //Toast.makeText(context,String.valueOf(pos),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestHeroes(Realm realm){
        RealmResults<Hero> results = realm.where(Hero.class).findAll();
        listHero = results.subList(0,results.size());
        listHero = results.subList(0,results.size());
    }

    @OnClick(R.id.btn_change_type)
    void actionChangeViewType(){
        btnChangeType.setImageResource(carousel ? R.drawable.carousel : R.drawable.gridview);
        recyclerViewHeros.setVisibility(carousel ? View.VISIBLE : View.GONE);
        mContainer.setVisibility(carousel ? View.GONE : View.VISIBLE);
        if(carousel) loadHeroesForCarousel(listHero);
        else loadHeroesForPage(listHero);
        carousel = !carousel;
    }

    private void loadHeroesForCarousel(List<Hero> listHero){
        heroGridAdapterAdapter.setmNumberData(listHero);

    }

    private void loadHeroesForPage(List<Hero> listHero){
        pager.setOffscreenPageLimit(5);
        pager.setPageTransformer(true, new CarouselPageTransformer());

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
        pager.setPageMargin(0);
        HeroCarouselAdapter adapter = new HeroCarouselAdapter(context,listHero);
        pager.setAdapter(adapter);
        adapter.setOnItemClickListener(new HeroCarouselAdapter.OnItemClickListener() {
            @Override
            public void onClick(Hero hero) {
                Bundle bundle = new Bundle();
                bundle.putString(HeroDetailFragment.HERO_DETAIL,hero.getId());
                context.pushFragments(new HeroDetailFragment(),bundle,true,true);
            }
        });
    }
}
