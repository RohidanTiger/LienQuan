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
import chickenzero.ht.com.lienquan.models.FreeHero;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 5/23/17.
 */

public class FreeHeroAdapter extends RecyclerView.Adapter{

    private List<FreeHero> mNumberData;
    private MainActivity mContext;
    private OnItemClickListener mListener;

    public FreeHeroAdapter(List<FreeHero> data, MainActivity context){
        this.mNumberData = data;
        this.mContext = context;
    }

    public void setmNumberData(List<FreeHero> mNumberData) {
        this.mNumberData.addAll(mNumberData);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PicassoLoader.getInstance(mContext).with(mContext).load(mNumberData.get(position).getIcon_hero()).into(((ViewHolder)holder).imgItem);
        ((ViewHolder) holder).txtNameItem.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(mNumberData.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNumberData.size();
    }

    /* Setter for listener. */
    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(FreeHero position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView imgItem;
        public TextView txtNameItem;

        public ViewHolder(View v) {
            super(v);
            imgItem = (SquareImageView) v.findViewById(R.id.img_item);
            txtNameItem = (TextView) v.findViewById(R.id.txt_name);
        }
    }
}
