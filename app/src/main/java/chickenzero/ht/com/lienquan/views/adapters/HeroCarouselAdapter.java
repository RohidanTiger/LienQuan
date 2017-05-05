package chickenzero.ht.com.lienquan.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.config.Contants;
import chickenzero.ht.com.lienquan.models.Hero;
import chickenzero.ht.com.lienquan.models.Skill;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/18/17.
 */

public class HeroCarouselAdapter extends PagerAdapter{
    private List<Hero> mDataSet;
    private MainActivity mContext;
    private OnItemClickListener listener;

    public HeroCarouselAdapter(MainActivity context, List<Hero> dataSet){
        this.mDataSet = dataSet;
        this.mContext = context;
    }

    public void setmDataSet(List<Hero> mData) {
        this.mDataSet = mData;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

    public Hero getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public int getCount() {
        return mDataSet.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final ViewGroup view = (ViewGroup) layoutInflater.inflate(R.layout.item_carousel_hero, container, false);
        final Hero hero = getItem(position);
        ((TextView)view.findViewById(R.id.hero_name)).setText(hero.getName());
        String imgUrl = "file:///android_asset/LienQuan/Heroes/".concat(hero.getId()).concat("/"+hero.getId()).concat("_avata").concat(".png");
        PicassoLoader.getInstance(mContext).with(mContext).load(imgUrl).into((ImageView)view.findViewById(R.id.img_hero));
        LinearLayout skillsHolder = (LinearLayout) view.findViewById(R.id.skills_holder);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        for(int i = 0; i < Contants.NUMBER_SKILL; i++){
            ViewGroup skillView = (ViewGroup) layoutInflater.inflate(R.layout.skill_holder, skillsHolder, false);
            skillView.setLayoutParams(layoutParams);
            String skillUrl = "file:///android_asset/LienQuan/Heroes/".concat(hero.getId()).concat("/"+hero.getId()).
                    concat("_a").concat(String.valueOf(i)).concat(".png");
            PicassoLoader.getInstance(mContext).with(mContext).load(skillUrl).into((ImageView)skillView.findViewById(R.id.skill_img));
            skillsHolder.addView(skillView);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(hero);
            }
        });
        container.addView(view);
        return view;
    }

    public interface OnItemClickListener {
        void onClick(Hero hero);
    }
}
