package chickenzero.ht.com.lienquan.views.fragments;

import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;

/**
 * Created by QuyDV on 4/28/17.
 */

public class SupportSkillFragment extends BaseFragment {
    @BindView(R.id.recycle_skill)
    RecyclerView recyclerView;
    @Override
    protected int getViewContent() {
        return R.layout.fragment_support_skill;
    }

    @Override
    protected void initUI() {

    }
}
