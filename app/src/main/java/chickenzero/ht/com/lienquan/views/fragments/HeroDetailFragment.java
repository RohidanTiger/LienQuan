package chickenzero.ht.com.lienquan.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;

/**
 * Created by QuyDV on 4/25/17.
 */

public class HeroDetailFragment extends BaseFragment{
    public static String HERO_DETAIL = "hero_detail";

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;
    private String heroID;

    private HeroDetailInfoFragment infoFragment;
    private HeroDetailSkillsFragment skillsFragment;
    private HeroDetailGuideFragment guideFragment;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_hero_detail;
    }

    @Override
    protected void initUI() {
        heroID = getArguments().getString(HERO_DETAIL);

        infoFragment = new HeroDetailInfoFragment();
        skillsFragment = new HeroDetailSkillsFragment();
        guideFragment = new HeroDetailGuideFragment();

        Bundle bundle = new Bundle();
        bundle.putString(HERO_DETAIL,heroID);

        infoFragment.setArguments(bundle);
        skillsFragment.setArguments(bundle);
        guideFragment.setArguments(bundle);

        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(infoFragment, context.getString(R.string.str_information));
        adapter.addFragment(skillsFragment,context.getString(R.string.str_skills));
        adapter.addFragment(guideFragment, context.getString(R.string.str_guide));

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return infoFragment;
            }

            if(position==1){
                return skillsFragment;
            }

            if(position==2){
                return guideFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
    }
}
