package chickenzero.ht.com.lienquan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import chickenzero.ht.com.lienquan.config.Contants;
import chickenzero.ht.com.lienquan.customize.CustomSpinner;
import chickenzero.ht.com.lienquan.service.FragmentStackManager;
import chickenzero.ht.com.lienquan.utils.ConnectivityReceiver;
import chickenzero.ht.com.lienquan.utils.DialogUtil;
import chickenzero.ht.com.lienquan.utils.PrefConfig;
import chickenzero.ht.com.lienquan.views.adapters.SectionedMenuAdapter;
import chickenzero.ht.com.lienquan.views.adapters.SlideMenuAdapter;
import chickenzero.ht.com.lienquan.views.fragments.FreeHeroFragment;
import chickenzero.ht.com.lienquan.views.fragments.HeroFragment;
import chickenzero.ht.com.lienquan.views.fragments.ItemFragment;
import chickenzero.ht.com.lienquan.views.fragments.LeagueListFragment;
import chickenzero.ht.com.lienquan.views.fragments.ListProPlayerFragment;
import chickenzero.ht.com.lienquan.views.fragments.NewsFrament;
import chickenzero.ht.com.lienquan.views.fragments.SupportSkillFragment;
import chickenzero.ht.com.lienquan.views.fragments.WallpaperFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public SCApplication mApplication;
    public FragmentStackManager fragmentStackManager;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private SlideMenuAdapter menuAdapter;
    private Toolbar toolbar;
    private TextView btnRateApp;
    private TextView btnOpenCamera;
    private ProgressDialog pDialog;
    private RecyclerView slideMenu;
    private int mCurrentPosition = 0;

    // Number Fragment In Stack
    private int currentStackSize = 0;
    public BaseFragment currentFragment;
    public PrefConfig mPrefConfig = PrefConfig.getInstance();
    public AdRequest adRequest;
    public InterstitialAd mInterstitialAd;
    public CustomSpinner mSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnRateApp = (TextView) findViewById(R.id.btnRateApp);
        btnOpenCamera = (TextView) findViewById(R.id.btnOpenCamera);
        setSupportActionBar(toolbar);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        mApplication = (SCApplication) getApplication();

        // init ad
        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.unit_ad_unit_id));
        adRequest = new AdRequest.Builder().addTestDevice("867826023574924").build();
        requestNewInterstitial();

        setUpDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initFragmentStackManager();
        enableNetworkOnMainThread();
        initFirstScreen();
        btnOpenCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp("ht.com.aov_camera");
            }
        });
        btnRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp("com.ht.chickenzero.lienquan");
            }
        });
        mSpinner = (CustomSpinner) findViewById(R.id.filter_spinner);
    }

    private void setUpDrawer(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        slideMenu = (RecyclerView) findViewById(R.id.navList);
        initSlideMenu();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerToggle.syncState();
    }

    private void initFragmentStackManager() {
        fragmentStackManager = FragmentStackManager.forContainer(this,
                R.id.frameLayoutContainer, new FragmentStackManager.Callback() {
                    @Override
                    public void onStackChanged(int stackSize,
                                               Fragment topFragment) {
                        currentFragment = (BaseFragment) topFragment;
                        currentStackSize = stackSize;
                        if (currentStackSize > 1) {
                            mDrawerToggle.setDrawerIndicatorEnabled(false);
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);// show back button
                            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    onBackPressed();
                                }
                            });
                        } else {
                            //show hamburger
                            mDrawerToggle.setDrawerIndicatorEnabled(true);
                            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                            mDrawerToggle.syncState();
                            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    drawer.openDrawer(GravityCompat.START);
                                }
                            });
                        }
                    }
                });

        fragmentStackManager.setDefaultAnimation(R.anim.slide_in_right,
                R.anim.slide_out_left, R.anim.slide_in_left,
                R.anim.slide_out_right);
    }

    private void enableNetworkOnMainThread() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    private void initFirstScreen(){
        mCurrentPosition = mPrefConfig.getRememberMenuPosition(this);
        switch (mCurrentPosition){
            case 0:{
                setActionBarTitle(R.string.str_hero);
                pushFragments(new HeroFragment(), false, true);
                break;
            }
            case 1:{
                setActionBarTitle(R.string.str_item);
                pushFragments(new ItemFragment(), false, true);
                break;
            }
            case 2:{
                setActionBarTitle(R.string.str_support_skill);
                pushFragments(new SupportSkillFragment(), false, true);
                break;
            }
            case 3:{
                setActionBarTitle(R.string.str_challenger);
                pushFragments(new ListProPlayerFragment(), false, true);
                break;
            }
            case 4:{
                setActionBarTitle(R.string.str_league);
                pushFragments(new HeroFragment(), false, true);
                break;
            }
            case 5:{
                setActionBarTitle(R.string.str_news);
                pushFragments(new NewsFrament(), false, true);
                break;
            }
            case 6:{
                setActionBarTitle(R.string.str_free_hero);
                pushFragments(new FreeHeroFragment(), false, true);
                break;
            }
            case 7:{
                setActionBarTitle(R.string.str_wallpaper);
                pushFragments(new WallpaperFragment(), false, true);
                break;
            }
        }
    }

    private void initSlideMenu(){
        slideMenu.setLayoutManager(new LinearLayoutManager(this));
        slideMenu.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        //Your RecyclerView.Adapter
        menuAdapter = new SlideMenuAdapter(this);


        //This is the code to provide a sectioned list
        List<SectionedMenuAdapter.Section> sections =
                new ArrayList<SectionedMenuAdapter.Section>();

        //Sections
        sections.add(new SectionedMenuAdapter.Section(0,"Cẩm nang"));
        sections.add(new SectionedMenuAdapter.Section(3,"Giải trí - Học hỏi"));

        //Add your adapter to the sectionAdapter
        SectionedMenuAdapter.Section[] dummy = new SectionedMenuAdapter.Section[sections.size()];
        SectionedMenuAdapter mSectionedAdapter = new SectionedMenuAdapter(this,R.layout.item_section,R.id.section_text,menuAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        slideMenu.setAdapter(mSectionedAdapter);

        menuAdapter.setOnItemClickListener(new SlideMenuAdapter.OnItemClickListener() {
            @Override
            public void onClick(int index, int title) {
                setActionBarTitle(title);
                mCurrentPosition = index;
                switch (index){
                    case 0:{
                        clearAllPreviousFragment();
                        pushFragments(new HeroFragment(), false, true);
                        break;
                    }
                    case 1:{
                        clearAllPreviousFragment();
                        pushFragments(new ItemFragment(), false, true);
                        break;
                    }
                    case 2:{
                        clearAllPreviousFragment();
                        pushFragments(new SupportSkillFragment(), false, true);
                        break;
                    }
                    case 3:{
                        clearAllPreviousFragment();
                        pushFragments(new ListProPlayerFragment(), false, true);
                        break;
                    }
                    case 4:{
                        clearAllPreviousFragment();
                        pushFragments(new LeagueListFragment(), false, true);
                        break;
                    }
                    case 5:{
                        clearAllPreviousFragment();
                        pushFragments(new NewsFrament(), false, true);
                        break;
                    }
                    case 6:{
                        clearAllPreviousFragment();
                        pushFragments(new FreeHeroFragment(), false, true);
                        break;
                    }case 7:{
                        clearAllPreviousFragment();
                        pushFragments(new WallpaperFragment(), false, true);
                        break;
                    }
                }
            }
        });
    }

    // Default tab is TAB_HOME

    public void pushFragments(Fragment fragment, Bundle bundle,
                              boolean shouldAdd) {
        pushFragments(Contants.TAB_HOME, fragment, bundle, true, shouldAdd);
    }

    public void pushFragments(Fragment fragment, boolean shouldAnimate,
                              boolean shouldAdd) {
        pushFragments(Contants.TAB_HOME, fragment, null, shouldAnimate,
                shouldAdd);
    }

    public void pushFragments(Fragment fragment, Bundle bundle,
                              boolean shouldAnimate, boolean shouldAdd) {
        pushFragments(Contants.TAB_HOME, fragment, bundle, shouldAnimate,
                shouldAdd);
    }

    public void pushFragments(String tag, Fragment fragment, Bundle bundle,
                              boolean shouldAnimate, boolean shouldAdd) {
        fragmentStackManager.push(fragment, fragment.getClass().getSimpleName(),
                bundle, shouldAnimate, shouldAdd);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
    }

    public void popFragments() {
        popFragments(true);
    }

    public void popFragments(boolean isSlideBack) {
        fragmentStackManager.pop(isSlideBack);
    }
    //
    // Clear all current fragment
    public void clearAllPreviousFragment() {
        fragmentStackManager.clearAllScreen();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        if (currentStackSize == 1) {
            DialogUtil.showYesNoDialog(this, R.string.msg_confirm_quit_app, R.string.cmn_yes, R.string.cmn_no, onOKClick);
            return;
        }
        if (currentStackSize > 1 && !currentFragment.canPressBack()) {
            if (fragmentStackManager.pop(true))
                return;
        } else{
            finish();
        }
    }

    DialogInterface.OnClickListener onOKClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (currentStackSize > 1 && !currentFragment.canPressBack()) {
            popFragments();
        }else{
            mDrawerToggle.setDrawerIndicatorEnabled(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void showLoading(final int message) {
        try {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        pDialog.show();
                        pDialog.setMessage(getString(message));
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoading(final String message) {
        try {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        pDialog.show();
                        pDialog.setMessage(message);
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoading() {
        try {
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        pDialog.show();
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void hideLoading() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (pDialog != null) {
                        pDialog.hide();
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
        });
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void setActionBarTitle(int title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPrefConfig.setRememberMenuPosition(this,mCurrentPosition);
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public void requestNewInterstitial(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_unit_id));
        mInterstitialAd.loadAd(adRequest);
    }

    void openApp(String applicationId) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=".concat(applicationId)));
        startActivity(myIntent);
    }
}
