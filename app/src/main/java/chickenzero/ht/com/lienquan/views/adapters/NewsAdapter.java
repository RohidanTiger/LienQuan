package chickenzero.ht.com.lienquan.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.models.NewsList;
import chickenzero.ht.com.lienquan.utils.DateTimeUtil;

import static chickenzero.ht.com.lienquan.R.id.imgVideo;

/**
 * Created by QuyDV on 4/24/17.
 */

public class NewsAdapter extends RecyclerView.Adapter{
    private List<NewsList.Item> mDataSet;
    private MainActivity mContext;
    private OnItemClickListener listener;

    public NewsAdapter(MainActivity context, List<NewsList.Item> listItems) {
        this.mContext = context;
        this.mDataSet = listItems;
    }

    public void setmDataSet(List<NewsList.Item> mDataSet) {
        this.mDataSet = mDataSet;
        notifyDataSetChanged();
    }

    public void setListener(NewsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new View
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_item_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final NewsList.Item item = mDataSet.get(position);
        ((ViewHolder) holder).textViewName.setText(item.getTitle());

        Calendar currentTime = Calendar.getInstance();
        Calendar publsihTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.DATE_TIME_FORMAT2, Locale.getDefault());
        try {
            publsihTime.setTime(sdf.parse(item.getPubDate().replace("([+-])(\\d\\d):(\\d\\d)$", "$1$2$3")));
        } catch (ParseException e) {
            Log.i("Error",e.toString());
        }
        ((ViewHolder) holder).textViewTime.setText(DateTimeUtil.findTimeAgo(currentTime,publsihTime));

        if(position > 0 && (position+1) %10 == 0){
            ((ViewHolder) holder).layoutAd.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).mAdView.loadAd(mContext.adRequest);
        }else{
            ((ViewHolder) holder).layoutAd.setVisibility(View.GONE);
        }

        ((ViewHolder) holder).layoutContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(item,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout layoutContent;
        public TextView textViewName;
        public TextView textViewTime;

        public RelativeLayout layoutAd;
        public AdView mAdView;

        public ViewHolder(View v) {
            super(v);
            layoutContent = (RelativeLayout) v.findViewById(R.id.layout_content);
            textViewName = (TextView) v.findViewById(R.id.txt_title);
            textViewTime = (TextView) v.findViewById(R.id.txt_time);
            layoutAd = (RelativeLayout) v.findViewById(R.id.layout_ad);
            mAdView = (AdView) v.findViewById(R.id.adView);
        }
    }

    public interface OnItemClickListener {
        void onClick(NewsList.Item item, int position);
    }
}
