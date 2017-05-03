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
import chickenzero.ht.com.lienquan.customize.SquareImageView;
import chickenzero.ht.com.lienquan.models.Item;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/14/17.
 */

public class ItemAdapter extends RecyclerView.Adapter{

    private List<Item> mNumberData;
    private MainActivity mContext;
    private OnItemClickListener mListener;

    public ItemAdapter(List<Item> data, MainActivity context){
        this.mNumberData = data;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String imgUrl = "file:///android_asset/LienQuan/Items/".concat(String.valueOf(mNumberData.get(position).getId())).concat(".jpg");
        PicassoLoader.getInstance(mContext).with(mContext).load(imgUrl).into(((ItemAdapter.ViewHolder)holder).imgItem);
        ((ViewHolder)holder).txtNameItem.setText(mNumberData.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(position);
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
        void onClick(int position);
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
