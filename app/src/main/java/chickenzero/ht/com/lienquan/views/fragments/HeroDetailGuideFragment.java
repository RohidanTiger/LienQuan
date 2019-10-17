package chickenzero.ht.com.lienquan.views.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.HeroDetail;
import chickenzero.ht.com.lienquan.models.Item;
import chickenzero.ht.com.lienquan.models.RecommentItem;
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

    @BindView(R.id.txt_title1)
    TextView textTitle1;

    @BindView(R.id.recycleItem1)
    RecyclerView recyclerViewItem1;

    @BindView(R.id.txt_title2)
    TextView textTitle2;

    @BindView(R.id.recycleItem2)
    RecyclerView recyclerViewItem2;

    @BindView(R.id.txt_title3)
    TextView textTitle3;

    @BindView(R.id.recycleItem3)
    RecyclerView recyclerViewItem3;

    @BindView(R.id.adView)
    public AdView mAdView;

    private String heroID;
    private RecommentItemAdapter itemAdapter1;
    private RecommentItemAdapter itemAdapter2;
    private RecommentItemAdapter itemAdapter3;

    private List<Item> listItem;

    private RecyclerView.LayoutManager mLayoutManager1;
    private RecyclerView.LayoutManager mLayoutManager2;
    private RecyclerView.LayoutManager mLayoutManager3;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_hero_guide;
    }

    @Override
    protected void initUI() {
        heroID = getArguments().getString(HeroDetailFragment.HERO_DETAIL);
        listItem = new ArrayList<>();
        String imgSkill = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/skill").concat(".jpg");
        PicassoLoader.getInstance(context).with(context).load(imgSkill).into(imgSkillUpgrade);

        String imgUrl = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/pearl").concat(".jpg");
        PicassoLoader.getInstance(context).with(context).load(imgUrl).into(imgRune);

        mLayoutManager1 = new GridLayoutManager(context,3);
        mLayoutManager2 = new GridLayoutManager(context,3);
        mLayoutManager3 = new GridLayoutManager(context,3);

        recyclerViewItem1.setLayoutManager(mLayoutManager1);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_small);
        recyclerViewItem1.addItemDecoration(itemDecoration);

        recyclerViewItem2.setLayoutManager(mLayoutManager2);
        recyclerViewItem2.addItemDecoration(itemDecoration);

        recyclerViewItem3.setLayoutManager(mLayoutManager3);
        recyclerViewItem3.addItemDecoration(itemDecoration);

        getListItem();

        mAdView.loadAd(context.adRequest);
    }

    private void getListItem(){
        HeroDetail heroDetail = realm.where(HeroDetail.class).equalTo("id",heroID).findFirst();
        RecommentItem recommentItem = realm.where(RecommentItem.class).equalTo("id",heroID).findFirst();
        if(recommentItem == null) return;
        if(recommentItem.getTitle1() != null && recommentItem.getListItem1() != null) {
            textTitle1.setText("Bộ trang bị " + recommentItem.getTitle1());
            String[] items = recommentItem.getListItem1().split("--");
            List<Item> listItem1 = new ArrayList<>();
            for(int i = 0; i < items.length; i++){
                Item item = realm.where(Item.class).equalTo("id",Integer.parseInt(items[i])).findFirst();
                listItem1.add(item);
            }
            itemAdapter1 = new RecommentItemAdapter(listItem1,context);
            recyclerViewItem1.setAdapter(itemAdapter1);
            itemAdapter1.setOnItemClickListener(new RecommentItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(Item position) {
                ItemDetailDialog dialog = new ItemDetailDialog(context,position);
                dialog.show();
            }
        });
        }

        if(recommentItem.getTitle2() != null && recommentItem.getListItem2() != null) {
            String[] items = recommentItem.getListItem2().split("--");
            textTitle2.setText("Bộ trang bị " + recommentItem.getTitle2());
            List<Item> listItem2 = new ArrayList<>();
            for(int i = 0; i < items.length; i++){
                Item item = realm.where(Item.class).equalTo("id",Integer.parseInt(items[i])).findFirst();
                listItem2.add(item);
            }
            itemAdapter2 = new RecommentItemAdapter(listItem2,context);
            recyclerViewItem2.setAdapter(itemAdapter2);
            itemAdapter2.setOnItemClickListener(new RecommentItemAdapter.OnItemClickListener() {
                @Override
                public void onClick(Item position) {
                    ItemDetailDialog dialog = new ItemDetailDialog(context,position);
                    dialog.show();
                }
            });
        }

        if(recommentItem.getTitle3() != null && recommentItem.getListItem3() != null) {
            String[] items = recommentItem.getListItem3().split("--");
            textTitle3.setText("Bộ trang bị " + recommentItem.getTitle3());
            List<Item> listItem3 = new ArrayList<>();
            for(int i = 0; i < items.length; i++){
                Item item = realm.where(Item.class).equalTo("id",Integer.parseInt(items[i])).findFirst();
                listItem3.add(item);
            }
            itemAdapter3 = new RecommentItemAdapter(listItem3,context);
            recyclerViewItem3.setAdapter(itemAdapter3);
            itemAdapter3.setOnItemClickListener(new RecommentItemAdapter.OnItemClickListener() {
                @Override
                public void onClick(Item position) {
                    ItemDetailDialog dialog = new ItemDetailDialog(context,position);
                    dialog.show();
                }
            });
        }

    }
}
