package chickenzero.ht.com.lienquan.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.models.PlayListYoutube;
import chickenzero.ht.com.lienquan.utils.DateTimeUtil;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;

/**
 * Created by QuyDV on 4/19/17.
 */

public class VideoDetailAdapter extends RecyclerView.Adapter{
    private List<PlayListYoutube.Item> mDataSet;
    private MainActivity mContext;
    private OnItemClickListener listener;

    public VideoDetailAdapter(MainActivity context, List<PlayListYoutube.Item> listItems) {
        this.mContext = context;
        this.mDataSet = listItems;
    }

    public void setmDataSet(List<PlayListYoutube.Item> mDataSet) {
        this.mDataSet.addAll(mDataSet);
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        PlayListYoutube.Snippet snippet = mDataSet.get(position).getSnippet();
        if(snippet.getThumbnails() != null){
            PicassoLoader.getInstance(mContext).load(snippet.getThumbnails().getHigh().getUrl()).placeholder(R.drawable.default_video).
                    error(R.drawable.default_video).into((((VideoDetailAdapter.ViewHolder) holder).imgVideo));
        }
        ((ViewHolder) holder).textViewName.setText(snippet.getTitle());

        Calendar currentTime = Calendar.getInstance();
        Calendar publsihTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.DATE_TIME_FORMAT1);
        try {
            publsihTime.setTime(sdf.parse(snippet.getPublishedAt()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((ViewHolder) holder).textViewTime.setText(DateTimeUtil.findTimeAgo(currentTime,publsihTime));

        ((ViewHolder) holder).layoutContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(mDataSet.get(position));
            }
        });

        if(position > 0 && position %10 == 0){
            ((ViewHolder) holder).layoutAd.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).mAdView.loadAd(mContext.adRequest);
        }else{
            ((ViewHolder) holder).layoutAd.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout layoutContent;
        public ImageView imgVideo;
        public TextView textViewName;
        public TextView textViewTime;
        public RelativeLayout layoutAd;
        public AdView mAdView;

        public ViewHolder(View v) {
            super(v);
            layoutContent = (RelativeLayout) v.findViewById(R.id.layout_content);
            imgVideo = (ImageView) v.findViewById(R.id.imgVideo);
            textViewName = (TextView) v.findViewById(R.id.txtName);
            textViewTime = (TextView) v.findViewById(R.id.txt_time);
            layoutAd = (RelativeLayout) v.findViewById(R.id.layout_ad);
            mAdView = (AdView) v.findViewById(R.id.adView);
        }
    }

    public interface OnItemClickListener {
        void onClick(PlayListYoutube.Item position);
    }
}
