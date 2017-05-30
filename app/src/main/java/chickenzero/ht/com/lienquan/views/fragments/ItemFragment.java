package chickenzero.ht.com.lienquan.views.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.CustomSpinner;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.Item;
import chickenzero.ht.com.lienquan.views.adapters.BaseSpinerAdapter;
import chickenzero.ht.com.lienquan.views.adapters.ItemAdapter;
import chickenzero.ht.com.lienquan.views.dialog.ItemDetailDialog;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by QuyDV on 4/14/17.
 */

public class ItemFragment extends BaseFragment{
    @BindView(R.id.recycleItem)
    RecyclerView recyclerViewItems;

    @BindView(R.id.spinner_item_type)
    CustomSpinner mSpinner;

    private ItemAdapter itemAdapter;
    private List<Item> listItem;
    private int mHeroSelectedPos = 0;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_item;
    }

    @Override
    protected void initUI() {
        requestItems(realm);
        itemAdapter = new ItemAdapter(listItem,context);
        mLayoutManager = new GridLayoutManager(context,4);
        recyclerViewItems.setLayoutManager(mLayoutManager);
        itemAdapter.setLayoutManager((GridLayoutManager) mLayoutManager);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_smaller);
        recyclerViewItems.addItemDecoration(itemDecoration);
        recyclerViewItems.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(Item position) {
                ItemDetailDialog dialog = new ItemDetailDialog(context,position);
                dialog.show();
            }
        });
        setUpSpinner(Arrays.asList(getResources().getStringArray(R.array.array_item_type)));
    }

    private void requestItems(Realm realm){
        RealmResults<Item> results = realm.where(Item.class).findAll();
        listItem = results.subList(0,results.size());
        Log.i("Data",listItem.size()+"");
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
                    List<Item>items = new ArrayList<Item>();
                    for(Item item : listItem){
                        if(item.getType() == position -1) items.add(item);
                    }
                    itemAdapter.setmNumberData(items);
                }else{
                    itemAdapter.setmNumberData(listItem);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
