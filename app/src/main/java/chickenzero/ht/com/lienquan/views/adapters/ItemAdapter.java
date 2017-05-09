package chickenzero.ht.com.lienquan.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SectionRecycleView.SectionedRecyclerViewAdapter;
import chickenzero.ht.com.lienquan.customize.SectionRecycleView.SectionedViewHolder;
import chickenzero.ht.com.lienquan.customize.SquareImageView;
import chickenzero.ht.com.lienquan.models.Item;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/14/17.
 */

public class ItemAdapter extends SectionedRecyclerViewAdapter<SectionedViewHolder> {

    private List<Item> mNumberData;
    private MainActivity mContext;
    private OnItemClickListener mListener;

    private List<Item> level1 = new ArrayList<>();
    private List<Item> level2 = new ArrayList<>();
    private List<Item> level3 = new ArrayList<>();

    public ItemAdapter(List<Item> data, MainActivity context){
        this.mNumberData = data;
        this.mContext = context;
        for (Item item : mNumberData){
            if(item.getLevel() == 1) level1.add(item);
            else if(item.getLevel() == 2) level2.add(item);
            else level3.add(item);
        }
    }

    public void setmNumberData(List<Item> mNumberData) {
        this.mNumberData = mNumberData;
        level1 = new ArrayList<>();
        level2 = new ArrayList<>();
        level3 = new ArrayList<>();
        for (Item item : mNumberData){
            if(item.getLevel() == 1) level1.add(item);
            else if(item.getLevel() == 2) level2.add(item);
            else level3.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getSectionCount() {
        if(level3.size() > 0) return 3;
        return 2;
    }

    @Override
    public int getItemCount(int section) {
        switch (section){
            case 0:{
                return level1.size();
            }
            case 1:{
                return level2.size();
            }
            case 2:{
                return level3.size();
            }
        }
        return 0;
    }

    @Override
    public void onBindHeaderViewHolder(SectionedViewHolder holder, int section, boolean expanded) {
        switch (section){
            case 0:{
                ((HeaderHolder)holder).title.setText("Trang bị cấp 1");
                return;
            }
            case 1:{
                ((HeaderHolder)holder).title.setText("Trang bị cấp 2");
                return;
            }
            case 2:{
                ((HeaderHolder)holder).title.setText("Trang bị cấp 3");
                return;
            }
        }
    }

    @Override
    public void onBindViewHolder(SectionedViewHolder holder, int section, final int relativePosition, int absolutePosition) {
        Item item = null;
        switch (section){
            case 0:{
                item = level1.get(relativePosition);
                break;
            }
            case 1:{
                item = level2.get(relativePosition);
                break;
            }
            case 2:{
                item = level3.get(relativePosition);
                break;
            }
        }
        if(item == null) return;
        String imgUrl = "file:///android_asset/LienQuan/Items/".concat(String.valueOf(item.getId())).concat(".jpg");
        PicassoLoader.getInstance(mContext).with(mContext).load(imgUrl).into(((ItemAdapter.ViewHolder)holder).imgItem);
        ((ViewHolder)holder).txtNameItem.setText(item.getName());
        final Item finalItem = item;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(finalItem);
            }
        });
    }

    @Override
    public SectionedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_HEADER:{
                layout = R.layout.item_header_item;
                View v1 = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                return new HeaderHolder(v1);
            }


            case VIEW_TYPE_ITEM:{
                layout = R.layout.item_item;
                View v = LayoutInflater.from(mContext).inflate(layout, parent, false);
                return new ViewHolder(v);
            }
            default:{
                layout = R.layout.item_header_item;
                View v1 = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                return new HeaderHolder(v1);
            }
        }
    }

    /* Setter for listener. */
    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(Item position);
    }

    public static class HeaderHolder extends SectionedViewHolder{
        final TextView title;

        HeaderHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.section_text);
        }
    }

    public static class ViewHolder extends SectionedViewHolder {
        public SquareImageView imgItem;
        public TextView txtNameItem;

        public ViewHolder(View v) {
            super(v);
            imgItem = (SquareImageView) v.findViewById(R.id.img_item);
            txtNameItem = (TextView) v.findViewById(R.id.txt_name);
        }
    }
}
