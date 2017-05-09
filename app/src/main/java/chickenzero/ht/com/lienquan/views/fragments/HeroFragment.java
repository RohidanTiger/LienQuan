package chickenzero.ht.com.lienquan.views.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.config.Contants;
import chickenzero.ht.com.lienquan.customize.CarouselPageTransformer;
import chickenzero.ht.com.lienquan.customize.CustomSpinner;
import chickenzero.ht.com.lienquan.customize.PagerContainer;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.customize.TransformableViewPager;
import chickenzero.ht.com.lienquan.models.Hero;
import chickenzero.ht.com.lienquan.views.adapters.BaseSpinerAdapter;
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

    @BindView(R.id.filter_spinner)
    CustomSpinner mSpinner;

    private boolean carousel = false;

    private HeroGridAdapter heroGridAdapterAdapter;

    private List<Hero> listHero;
    private List<Hero> heros;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mHeroSelectedPos = 0;

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

        setUpSpinner(Arrays.asList(getResources().getStringArray(R.array.array_role)));
    }

    private void requestHeroes(Realm realm){
        RealmResults<Hero> results = realm.where(Hero.class).findAll();
        listHero = results.subList(0,results.size());
        listHero = results.subList(0,results.size());
        heros = new ArrayList<>();
        heros.addAll(listHero);
    }

    @OnClick(R.id.btn_change_type)
    void actionChangeViewType(){
        btnChangeType.setImageResource(carousel ? R.drawable.carousel : R.drawable.gridview);
        recyclerViewHeros.setVisibility(carousel ? View.VISIBLE : View.GONE);
        mContainer.setVisibility(carousel ? View.GONE : View.VISIBLE);
        if(carousel) loadHeroesForCarousel(heros);
        else loadHeroesForPage(heros);
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
        HeroCarouselAdapter heroCarouselAdapter = new HeroCarouselAdapter(context,listHero);
        pager.setAdapter(heroCarouselAdapter);
        heroCarouselAdapter.setOnItemClickListener(new HeroCarouselAdapter.OnItemClickListener() {
            @Override
            public void onClick(Hero hero) {
                Bundle bundle = new Bundle();
                bundle.putString(HeroDetailFragment.HERO_DETAIL,hero.getId());
                context.pushFragments(new HeroDetailFragment(),bundle,true,true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        context.getSupportActionBar().setTitle(R.string.str_hero);
    }

    private void setUpSpinner(final List<String> data){
        final BaseSpinerAdapter mAdapter = new BaseSpinerAdapter(getContext(), R.layout.item_spinner, data);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setSelection(mHeroSelectedPos);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setSelection(position);
                mHeroSelectedPos = position;
                if(position != 0){
                    final String role = data.get(position);
                    heros = new ArrayList<Hero>();
                    for(Hero hero : listHero){
                        if(Contants.getRole(Integer.parseInt(hero.getRole())).toLowerCase()
                                .equals(role.toLowerCase())) heros.add(hero);
                    }
                    heroGridAdapterAdapter.setmNumberData(heros);
                    loadHeroesForPage(heros);
                }else{
                    heroGridAdapterAdapter.setmNumberData(listHero);
                    loadHeroesForPage(listHero);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
