package chickenzero.ht.com.lienquan.views.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
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
import chickenzero.ht.com.lienquan.YoutubePlayerActivity;
import chickenzero.ht.com.lienquan.models.Skill;
import chickenzero.ht.com.lienquan.utils.Logger;
import chickenzero.ht.com.lienquan.views.adapters.SkillDetailAdapter;

import static chickenzero.ht.com.lienquan.YoutubePlayerActivity.YOUTUBE_ID;

/**
 * Created by QuyDV on 4/25/17.
 */

public class HeroDetailSkillsFragment extends BaseFragment {
    @BindView(R.id.recycle_skill)
    RecyclerView recyclerViewSkill;

    private String heroID;
    private SkillDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Skill> skillList = new ArrayList<>();

    @Override
    protected int getViewContent() {
        return R.layout.fragment_hero_skills;
    }

    @Override
    protected void initUI() {
        heroID = getArguments().getString(HeroDetailFragment.HERO_DETAIL);
        loadAllSkillDetailFromFile();
        mAdapter = new SkillDetailAdapter(context,skillList,heroID);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerViewSkill.setLayoutManager(mLayoutManager);
        recyclerViewSkill.setAdapter(mAdapter);
        mAdapter.setListener(new SkillDetailAdapter.OnItemClickListener() {
            @Override
            public void onClick(String position) {
                Intent intent = new Intent(context,YoutubePlayerActivity.class);
                intent.putExtra(YOUTUBE_ID,position);
                startActivity(intent);
            }
        });
    }

    private void loadAllSkillDetailFromFile(){
        String json = null;
        try {
            InputStream is = context.getAssets().open("LienQuan/Heroes/"+ heroID + "/skills.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            skillList = new Gson().fromJson(json,new TypeToken<List<Skill>>(){}.getType());
            Logger.d("Data",String.valueOf(skillList.size()));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
