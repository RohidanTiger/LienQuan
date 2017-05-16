package chickenzero.ht.com.lienquan.views.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.HeroDetail;
import chickenzero.ht.com.lienquan.models.Item;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;
import chickenzero.ht.com.lienquan.views.adapters.RecommentItemAdapter;
import chickenzero.ht.com.lienquan.views.dialog.ItemDetailDialog;

/**
 * Created by QuyDV on 4/25/17.
 */

public class HeroDetailGuideFragment extends BaseFragment {
    @BindView(R.id.img_skill_upgrade)
    ImageView imgSkillUpgrade;

    @BindView(R.id.img_rune)
    ImageView imgRune;

    @BindView(R.id.recycleItem)
    RecyclerView recyclerViewItem;

    @BindView(R.id.adView)
    public AdView mAdView;

    private String heroID;
    private RecommentItemAdapter itemAdapter;
    private List<Item> listItem;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_hero_guide;
    }

    @Override
    protected void initUI() {
        heroID = getArguments().getString(HeroDetailFragment.HERO_DETAIL);
        listItem = new ArrayList<>();
        getListItem();
        String imgSkill = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/skill").concat(".jpg");
        PicassoLoader.getInstance(context).with(context).load(imgSkill).into(imgSkillUpgrade);

        String imgUrl = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/pearl").concat(".jpg");
        PicassoLoader.getInstance(context).with(context).load(imgUrl).into(imgRune);

        itemAdapter = new RecommentItemAdapter(listItem,context);
        mLayoutManager = new GridLayoutManager(context,3);
        recyclerViewItem.setLayoutManager(mLayoutManager);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_small);
        recyclerViewItem.addItemDecoration(itemDecoration);
        recyclerViewItem.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new RecommentItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(Item position) {
                ItemDetailDialog dialog = new ItemDetailDialog(context,position);
                dialog.show();
            }
        });
        mAdView.loadAd(context.adRequest);
    }

    private void getListItem(){
        HeroDetail heroDetail = context.realm.where(HeroDetail.class).equalTo("id",heroID).findFirst();
        if(heroDetail == null) return;
        String[] items = heroDetail.getItems().split("--");
        for(int i = 0; i < items.length; i++){
            Item item = context.realm.where(Item.class).equalTo("id",Integer.parseInt(items[i])).findFirst();
            listItem.add(item);
        }
    }
}
