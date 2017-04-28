package chickenzero.ht.com.lienquan.views.adapters;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SquareImageView;
import chickenzero.ht.com.lienquan.models.HeroDetail;

/**
 * Created by QuyDV on 4/27/17.
 */

public class HeroInfoAdapter extends RecyclerView.Adapter{
    private int mNumberData;
    private MainActivity mContext;
    private HeroDetail mHeroDetail;

    public HeroInfoAdapter(MainActivity mContext, HeroDetail mHeroDetail) {
        this.mNumberData = 6;
        this.mContext = mContext;
        this.mHeroDetail = mHeroDetail;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_hero_info, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position){
            case 0:{
                ((ViewHolder)holder).txtTitle.setTextColor(ContextCompat.getColor(mContext, R.color.red_dark));
                ((ViewHolder)holder).txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.text_title));
                ((ViewHolder)holder).txtTitle.setTypeface(Typeface.DEFAULT_BOLD);
                ((ViewHolder)holder).txtTitle.setText("Chỉ số");
                ((ViewHolder)holder).txtLv1.setText("Level 1");
                ((ViewHolder)holder).txtLv8.setText("Level 8");
                ((ViewHolder)holder).txtLv15.setText("Level 15");
                break;
            }
            case 1:{
                ((ViewHolder)holder).txtTitle.setText("Công vật lý");
                ((ViewHolder)holder).txtLv1.setText(mHeroDetail.getAttackDame());
                int att = Integer.parseInt(mHeroDetail.getAttackDame());
                int inc = Integer.parseInt(mHeroDetail.getAttackIncreaseDame());
                ((ViewHolder)holder).txtLv8.setText(String.valueOf(att+(7*inc)));
                ((ViewHolder)holder).txtLv15.setText(String.valueOf(att+(14*inc)));
                break;
            }
            case 2:{
                ((ViewHolder)holder).txtTitle.setText("Công phép");
                ((ViewHolder)holder).txtLv1.setText(mHeroDetail.getAbilityDame());
                int att = Integer.parseInt(mHeroDetail.getAbilityDame());
                int inc = Integer.parseInt(mHeroDetail.getAbilityIncreaseDame());
                ((ViewHolder)holder).txtLv8.setText(String.valueOf(att+(7*inc)));
                ((ViewHolder)holder).txtLv15.setText(String.valueOf(att+(14*inc)));
                break;
            }
            case 3:{
                ((ViewHolder)holder).txtTitle.setText("Máu tối đa");
                ((ViewHolder)holder).txtLv1.setText(mHeroDetail.getHitPoint());
                int att = Integer.parseInt(mHeroDetail.getHitPoint());
                int inc = Integer.parseInt(mHeroDetail.getHitPointIncrease());
                ((ViewHolder)holder).txtLv8.setText(String.valueOf(att+(7*inc)));
                ((ViewHolder)holder).txtLv15.setText(String.valueOf(att+(14*inc)));
                break;
            }
            case 4:{
                ((ViewHolder)holder).txtTitle.setText("Giáp");
                ((ViewHolder)holder).txtLv1.setText(mHeroDetail.getArmor());
                int att = Integer.parseInt(mHeroDetail.getArmor());
                int inc = Integer.parseInt(mHeroDetail.getArmorIncrease());
                ((ViewHolder)holder).txtLv8.setText(String.valueOf(att+(7*inc)));
                ((ViewHolder)holder).txtLv15.setText(String.valueOf(att+(14*inc)));
                break;
            }
            case 5:{
                ((ViewHolder)holder).txtTitle.setText("Giáp phép");
                ((ViewHolder)holder).txtLv1.setText(mHeroDetail.getAbilityArmor());
                int att = Integer.parseInt(mHeroDetail.getAbilityArmor());
                int inc = Integer.parseInt(mHeroDetail.getAbilityArmorIncrease());
                ((ViewHolder)holder).txtLv8.setText(String.valueOf(att+(7*inc)));
                ((ViewHolder)holder).txtLv15.setText(String.valueOf(att+(14*inc)));
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mNumberData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtLv1;
        public TextView txtLv8;
        public TextView txtLv15;

        public ViewHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.txt_title);
            txtLv1 = (TextView) v.findViewById(R.id.txt_lv1);
            txtLv8 = (TextView) v.findViewById(R.id.txt_lv8);
            txtLv15 = (TextView) v.findViewById(R.id.txt_lv15);
        }
    }
}
