package chickenzero.ht.com.lienquan.views.fragments;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.views.adapters.ItemAdapter;

/**
 * Created by QuyDV on 4/14/17.
 */

public class ItemFragment extends BaseFragment{
    @BindView(R.id.recycleItem)
    RecyclerView recyclerViewItems;
    private ItemAdapter itemAdapter;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_item;
    }

    @Override
    protected void initUI() {

    }
}
