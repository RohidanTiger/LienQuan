package chickenzero.ht.com.lienquan.views.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.Item;
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
    private ItemAdapter itemAdapter;
    private List<Item> listItem;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_item;
    }

    @Override
    protected void initUI() {
        requestItems(context.realm);
        itemAdapter = new ItemAdapter(listItem,context);
        mLayoutManager = new GridLayoutManager(context,4);
        recyclerViewItems.setLayoutManager(mLayoutManager);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_smaller);
        recyclerViewItems.addItemDecoration(itemDecoration);
        recyclerViewItems.setAdapter(itemAdapter);
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Item item = listItem.get(position);
                ItemDetailDialog dialog = new ItemDetailDialog(context,item);
                dialog.show();
            }
        });

    }

    private void requestItems(Realm realm){
        RealmResults<Item> results = realm.where(Item.class).findAll();
        listItem = results.subList(0,results.size());
        Log.i("Data",listItem.size()+"");
    }
}
