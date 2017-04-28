package chickenzero.ht.com.lienquan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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

import chickenzero.ht.com.lienquan.config.Contants;
import chickenzero.ht.com.lienquan.service.FragmentStackManager;
import chickenzero.ht.com.lienquan.utils.DialogUtil;
import chickenzero.ht.com.lienquan.views.fragments.HeroFragment;
import chickenzero.ht.com.lienquan.views.fragments.ItemFragment;
import chickenzero.ht.com.lienquan.views.fragments.NewsFrament;
import chickenzero.ht.com.lienquan.views.fragments.VideoListFragment;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public SCApplication mApplication;
    public FragmentStackManager fragmentStackManager;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;
    private ProgressDialog pDialog;
    public Realm realm;

    // Number Fragment In Stack
    private int currentStackSize = 0;
    public BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        mApplication = (SCApplication) getApplication();
        realm = Realm.getDefaultInstance();
        setUpDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initFragmentStackManager();
        enableNetworkOnMainThread();
        pushFragments(new HeroFragment(), false, true);

    }

    private void setUpDrawer(){
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
