package chickenzero.ht.com.lienquan.views.adapters;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SectionRecycleView.SectionedRecyclerViewAdapter;
import chickenzero.ht.com.lienquan.customize.SectionRecycleView.SectionedViewHolder;
import chickenzero.ht.com.lienquan.models.Wallpaper;
import chickenzero.ht.com.lienquan.views.WallpaperActivity;

/**
 * Created by QuyDV on 4/17/17.
 */

public class WallpaperAdapter extends SectionedRecyclerViewAdapter<SectionedViewHolder> {

    private List<Wallpaper> mNumberData;
    private MainActivity mContext;
    private OnItemClickListener mListener;

    public WallpaperAdapter(List<Wallpaper> data, MainActivity context){
        this.mNumberData = data;
        this.mContext = context;
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(mContext).inflate(R.layout.item_wallpaper, parent, false);
//        ViewHolder vh = new ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//
//    }

    @Override
    public int getSectionCount() {
        return mNumberData.size();
    }

    @Override
    public int getItemCount(int section) {
        return mNumberData.get(section).getUrls().size();
    }

    @Override
    public void onBindHeaderViewHolder(SectionedViewHolder holder, int section, boolean expanded) {
        String key = mNumberData.get(section).getName();
        ((MainVH) holder).title.setText(key);
        ((MainVH) holder).caret.setImageResource(expanded ? R.drawable.ic_collapse : R.drawable.ic_expand);
    }

    @Override
    public void onBindViewHolder(final SectionedViewHolder holder, int section, final int relativePosition, int absolutePosition) {
        final Wallpaper wallpaper =  mNumberData.get(section);

        Glide.with(mContext).load(wallpaper.getUrls().get(relativePosition)).placeholder(R.drawable.placeholder).into(((ViewHolder)holder).imgItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int[] screenLocation = new int[2];
                        ((ViewHolder)holder).imgItem.getLocationInWindow(screenLocation);
                        Intent i = new Intent(new Intent(mContext, WallpaperActivity.class));
                        i.putExtra(WallpaperActivity.url_image,wallpaper.getUrls().get(relativePosition));

                        if (mContext.mInterstitialAd.isLoaded()) {
                            mContext.mInterstitialAd.show();
                        } else {
                            mContext.requestNewInterstitial();
                        }

                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            ActivityOptions options = ActivityOptions
                                    .makeSceneTransitionAnimation(mContext,
                                            new Pair[]{Pair.create(((ViewHolder) holder).imgItem, "image_transition")});
                            mContext.startActivity(i, options.toBundle());
                        }else{
                            mContext.startActivity(i);
                        }

                    }
                });
                //mListener.onClick(wallpaper);
            }
        });
    }


    /* Setter for List Data. */

    public void setmNumberData(List<Wallpaper> mNumberData) {
        this.mNumberData = mNumberData;
        notifyDataSetChanged();
    }

    /* Setter for listener. */
    public void setOnItemClickListener(final OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public SectionedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                layout = R.layout.list_item_header;
                View v1 = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                return new MainVH(v1, this);

            case VIEW_TYPE_ITEM:
                layout = R.layout.item_wallpaper;
                View v2 = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                return new ViewHolder(v2);

            default:
                layout = R.layout.list_item_header;
                View v3 = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                return new MainVH(v3, this);

        }
    }


    public interface OnItemClickListener {
        void onClick(Wallpaper wallpaper);
    }

    static class MainVH extends SectionedViewHolder implements View.OnClickListener {

        Toast toast;
        final TextView title;
        final ImageView caret;
        final WallpaperAdapter adapter;

        MainVH(View itemView, WallpaperAdapter adapter) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.caret = (ImageView) itemView.findViewById(R.id.caret);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (isHeader()) {
                adapter.toggleSectionExpanded(getRelativePosition().section());
            } else {
                if (toast != null) {
                    toast.cancel();
                }
                toast =
                        Toast.makeText(view.getContext(), getRelativePosition().toString(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public static class ViewHolder extends SectionedViewHolder {
        public ImageView imgItem;
        public TextView txtNameItem;

        public ViewHolder(View v) {
            super(v);
            imgItem = (ImageView) v.findViewById(R.id.img_wallpaper);
            txtNameItem = (TextView) v.findViewById(R.id.txt_name);
        }
    }
}
