package chickenzero.ht.com.lienquan.views.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;
import java.util.Random;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SquareImageView;
import chickenzero.ht.com.lienquan.models.Hero;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/17/17.
 */

public class HeroGridAdapter extends RecyclerView.Adapter{

    private List<Hero> mNumberData;
    private MainActivity mContext;
    private OnItemClickListener mListener;

    public HeroGridAdapter(List<Hero> data, MainActivity context){
        this.mNumberData = data;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_hero, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        holder.setIsRecyclable(false);
        final Hero hero =  mNumberData.get(position);
        String heroID = hero.getId();
        String imgUrl = "file:///android_asset/LienQuan/Heroes/".concat(heroID).concat("/"+heroID).concat("_avata").concat(".png");
        PicassoLoader.getInstance(mContext).with(mContext).load(imgUrl).into(((ViewHolder)holder).imgItem);
        ((ViewHolder)holder).txtNameItem.setText(mNumberData.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(hero);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNumberData.size();
    }

    /* Setter for List Data. */

    public void setmNumberData(List<Hero> mNumberData) {
        this.mNumberData = mNumberData;
        notifyDataSetChanged();
    }

    /* Setter for listener. */
    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(Hero hero);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView imgItem;
        public TextView txtNameItem;
        public RelativeLayout test;

        public ViewHolder(View v) {
            super(v);
            imgItem = (SquareImageView) v.findViewById(R.id.img_hero);
            txtNameItem = (TextView) v.findViewById(R.id.txt_name);
        }
    }
}
