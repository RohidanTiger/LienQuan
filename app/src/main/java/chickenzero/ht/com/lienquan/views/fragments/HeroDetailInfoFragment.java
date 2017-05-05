package chickenzero.ht.com.lienquan.views.fragments;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.YoutubePlayerActivity;
import chickenzero.ht.com.lienquan.models.HeroDetail;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;
import chickenzero.ht.com.lienquan.views.adapters.HeroInfoAdapter;
import chickenzero.ht.com.lienquan.views.adapters.SkillDetailAdapter;

/**
 * Created by QuyDV on 4/25/17.
 */

public class HeroDetailInfoFragment extends BaseFragment {
    @BindView(R.id.recycleInfo)
    RecyclerView recyclerViewInfo;

    private RecyclerView.LayoutManager mLayoutManager;
    private HeroInfoAdapter mAdapter;

    private HeroDetail heroDetail;
    private String heroID;

    @BindView(R.id.img_hero)
    ImageView imageHero;

    @BindView(R.id.txtHeroName)
    TextView txtHeroName;

    @BindView(R.id.txtRole)
    TextView txtRole;

    @BindView(R.id.txt_move_value)
    TextView txtMoveValue;

    @BindView(R.id.txt_range_value)
    TextView txtRangeValue;

    @BindView(R.id.txt_lore_value)
    TextView txtLore;

    @BindView(R.id.txt_watch_video)
    TextView txtWatchVideo;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_hero_infor;
    }

    @Override
    protected void initUI() {
        heroID = getArguments().getString(HeroDetailFragment.HERO_DETAIL);
        heroDetail = context.realm.where(HeroDetail.class).equalTo("id",heroID).findFirst();
        if(heroDetail == null) return;
        context.setActionBarTitle(heroDetail.getName());
        String imgUrl = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/"+heroID).concat("_avata").concat(".png");
        PicassoLoader.getInstance(context).with(context).load(imgUrl).into(imageHero);
        txtHeroName.setText(heroDetail.getName());
        txtRole.setText(heroDetail.getRole());
        txtMoveValue.setText(heroDetail.getMovementSpeed());
        txtRangeValue.setText(heroDetail.getRange());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            txtLore.setText(Html.fromHtml(heroDetail.getLore(),Html.FROM_HTML_MODE_COMPACT));
        }else{
            txtLore.setText(Html.fromHtml(heroDetail.getLore()));
        }

        if(heroDetail.getYoutube() != null && heroDetail.getYoutube().length()>0){
            txtWatchVideo.setText(Html.fromHtml("<a href=\"" + heroDetail.getYoutube() + "\">" + "Watch video" + "</a> "));
        }

        mAdapter = new HeroInfoAdapter(context,heroDetail);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerViewInfo.setLayoutManager(mLayoutManager);
        recyclerViewInfo.setAdapter(mAdapter);

    }

    @OnClick(R.id.txt_watch_video)
    void watchVideo(){
        Intent intent = new Intent(context,YoutubePlayerActivity.class);
        intent.putExtra(YoutubePlayerActivity.YOUTUBE_ID,heroDetail.getYoutube());
        startActivity(intent);
    }
}
