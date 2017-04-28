package chickenzero.ht.com.lienquan.views.fragments;

import android.widget.ImageView;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseFragment;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/25/17.
 */

public class HeroDetailGuideFragment extends BaseFragment {
    @BindView(R.id.img_skill_upgrade)
    ImageView imgSkillUpgrade;

    @BindView(R.id.img_rune)
    ImageView imgRune;

    private String heroID;

    @Override
    protected int getViewContent() {
        return R.layout.fragment_hero_guide;
    }

    @Override
    protected void initUI() {
        heroID = getArguments().getString(HeroDetailFragment.HERO_DETAIL);

        String imgSkill = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/skill").concat(".jpg");
        PicassoLoader.getInstance(context).with(context).load(imgSkill).into(imgSkillUpgrade);

        String imgUrl = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/pearl").concat(".jpg");
        PicassoLoader.getInstance(context).with(context).load(imgUrl).into(imgRune);
    }
}
