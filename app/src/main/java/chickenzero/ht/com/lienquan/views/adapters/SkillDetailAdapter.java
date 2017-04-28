package chickenzero.ht.com.lienquan.views.adapters;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.models.Skill;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/26/17.
 */

public class SkillDetailAdapter extends RecyclerView.Adapter{
    private List<Skill> mDataSet;
    private MainActivity mContext;
    private String heroID;

    public SkillDetailAdapter(MainActivity context, List<Skill> listItems, String heroID) {
        this.mContext = context;
        this.mDataSet = listItems;
        this.heroID = heroID;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_skill_detail, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Skill skill = mDataSet.get(position);
        String imgUrl = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/"+heroID).concat("_a").
                concat(String.valueOf(position)).concat(".png");
        PicassoLoader.getInstance(mContext).with(mContext).load(imgUrl).into(((ViewHolder)holder).imgSkill);
        ((ViewHolder)holder).textSkillName.setText(skill.getName());
        ((ViewHolder)holder).textSkillMana.setText(skill.getMana());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            ((ViewHolder)holder).textSkillDesc.setText(Html.fromHtml(skill.getDesc(),Html.FROM_HTML_MODE_COMPACT));
        }else{
            ((ViewHolder)holder).textSkillDesc.setText(Html.fromHtml(skill.getDesc()));
        }
        if(skill.getYoutube() != null){
            ((ViewHolder)holder).textSkillVideo.setText(Html.fromHtml("<a href=\"" + skill.getYoutube() + "\">"
                    + "Watch video" + "</a> "));
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgSkill;
        public TextView textSkillName;
        public TextView textSkillMana;
        public TextView textSkillDesc;
        public TextView textSkillVideo;

        public ViewHolder(View v) {
            super(v);
            imgSkill = (ImageView) v.findViewById(R.id.img_skill);
            textSkillName = (TextView) v.findViewById(R.id.txt_skill_mame);
            textSkillMana = (TextView) v.findViewById(R.id.txt_mana);
            textSkillDesc = (TextView) v.findViewById(R.id.txt_description);
            textSkillVideo = (TextView) v.findViewById(R.id.txt_watch_video);
        }
    }
}
