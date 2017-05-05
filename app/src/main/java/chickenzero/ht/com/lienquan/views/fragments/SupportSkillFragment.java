package chickenzero.ht.com.lienquan.views.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SpacesItemDecoration;
import chickenzero.ht.com.lienquan.models.SupportSkill;
import chickenzero.ht.com.lienquan.views.adapters.SupportSkillAdapter;
import chickenzero.ht.com.lienquan.views.dialog.VideoPreviewDialog;

/**
 * Created by QuyDV on 4/28/17.
 */

public class SupportSkillFragment extends BaseFragment {
    @BindView(R.id.recycle_skill)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    List<SupportSkill> skillList = new ArrayList<>();

    private SupportSkillAdapter mAdapter;
    @Override
    protected int getViewContent() {
        return R.layout.fragment_support_skill;
    }

    @Override
    protected void initUI() {
        loadAllSkillDetailFromFile();
        if(skillList == null) return;
        mAdapter = new SupportSkillAdapter(skillList,context);
        mLayoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(mLayoutManager);
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(getContext(), R.dimen.padding_smaller);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setmListener(new SupportSkillAdapter.OnItemClickListener() {
            @Override
            public void onClick(SupportSkill supportSkill) {
                VideoPreviewDialog dialog = new VideoPreviewDialog(context,supportSkill);
                dialog.show();
            }
        });
    }

    private void loadAllSkillDetailFromFile(){
        String json = null;
        try {
            InputStream is = context.getAssets().open("LienQuan/SupportSkill/SupportSkill.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            skillList = new Gson().fromJson(json,new TypeToken<List<SupportSkill>>(){}.getType());
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
