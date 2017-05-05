package chickenzero.ht.com.lienquan.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SquareImageView;
import chickenzero.ht.com.lienquan.models.SupportSkill;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/28/17.
 */

public class SupportSkillAdapter extends RecyclerView.Adapter{
    private List<SupportSkill> mNumberData;
    private MainActivity mContext;
    private OnItemClickListener mListener;

    public SupportSkillAdapter(List<SupportSkill> data, MainActivity context){
        this.mNumberData = data;
        this.mContext = context;
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_hero, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SupportSkill skill =  mNumberData.get(position);
        String skillIdID = skill.getId();
        String imgUrl = "file:///android_asset/LienQuan/SupportSkill/".concat(skillIdID).concat(".png");
        PicassoLoader.getInstance(mContext).with(mContext).load(imgUrl).into(((ViewHolder)holder).imgItem);
        ((ViewHolder)holder).txtNameItem.setText(skill.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(skill);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNumberData.size();
    }


    public interface OnItemClickListener {
        void onClick(SupportSkill supportSkill);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView imgItem;
        public TextView txtNameItem;

        public ViewHolder(View v) {
            super(v);
            imgItem = (SquareImageView) v.findViewById(R.id.img_hero);
            txtNameItem = (TextView) v.findViewById(R.id.txt_name);
        }
    }
}
