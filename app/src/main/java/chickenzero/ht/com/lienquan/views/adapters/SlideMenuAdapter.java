package chickenzero.ht.com.lienquan.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;

/**
 * Created by QuyDV on 5/4/17.
 */

public class SlideMenuAdapter extends RecyclerView.Adapter<SlideMenuAdapter.SimpleViewHolder> {

    private final MainActivity mContext;
    private int[] mTitle;
    private int[] mImage;
    private OnItemClickListener listener;

    public SlideMenuAdapter(MainActivity context) {
        mContext = context;
        mTitle = new int[]{R.string.str_hero,R.string.str_item, R.string.str_support_skill
                ,R.string.str_challenger,R.string.str_league, R.string.str_news,R.string.str_free_hero};
        mImage = new int[]{R.drawable.icon_hero, R.drawable.icon_item, R.drawable.icon_support_skill,
                android.R.drawable.ic_menu_compass, android.R.drawable.ic_menu_slideshow,
                android.R.drawable.ic_menu_recent_history,android.R.drawable.ic_menu_rotate};
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item_slide_menu, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(mTitle[position]);
        holder.image.setImageResource(mImage[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(position, mTitle[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitle.length;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView image;
        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtNameMenu);
            image = (ImageView) view.findViewById(R.id.img_item);
        }
    }

    public interface OnItemClickListener {
        void onClick(int index, int title);
    }
}
