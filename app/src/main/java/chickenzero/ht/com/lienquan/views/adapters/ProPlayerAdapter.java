package chickenzero.ht.com.lienquan.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.models.ProPlayer;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 5/10/17.
 */

public class ProPlayerAdapter extends RecyclerView.Adapter{
    private List<ProPlayer> mNumberData;
    private MainActivity mContext;
    private OnItemClickListener mListener;

    public ProPlayerAdapter(List<ProPlayer> data, MainActivity context){
        this.mNumberData = data;
        this.mContext = context;
    }

    public void setmListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_pro_player, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ProPlayer hero =  mNumberData.get(position);
        PicassoLoader.getInstance(mContext).with(mContext).load(hero.getProfile()).into(((ViewHolder)holder).imgItem);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgItem;
        public TextView txtNameItem;

        public ViewHolder(View v) {
            super(v);
            imgItem = (ImageView) v.findViewById(R.id.img_chanel);
            txtNameItem = (TextView) v.findViewById(R.id.channel_name);
        }
    }

    public interface OnItemClickListener {
        void onClick(ProPlayer player);
    }

}
