package chickenzero.ht.com.lienquan.views.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import chickenzero.ht.com.lienquan.MainActivity;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SectionRecycleView.SectionedRecyclerViewAdapter;
import chickenzero.ht.com.lienquan.customize.SectionRecycleView.SectionedViewHolder;
import chickenzero.ht.com.lienquan.models.PlayListYoutube;
import chickenzero.ht.com.lienquan.utils.DateTimeUtil;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;


/**
 * @author Aidan Follestad (afollestad)
 */
public class LeagueAdapter extends SectionedRecyclerViewAdapter<SectionedViewHolder> {

    private MainActivity mContext;
    private HashMap<String, PlayListYoutube> mapLeague;
    private OnItemClickListener listener;

    public LeagueAdapter(MainActivity mContext, HashMap<String, PlayListYoutube> mapLeague) {
        this.mContext = mContext;
        this.mapLeague = mapLeague;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getSectionCount() {
        return mapLeague.size();
    }

    @Override
    public int getItemCount(int section) {
        String key = (String) mapLeague.keySet().toArray()[section];
        return mapLeague.get(key).getItems().size();
    }


    @Override
    public void onBindHeaderViewHolder(SectionedViewHolder holder, int section, boolean expanded) {
        String key = (String) mapLeague.keySet().toArray()[section];
        ((MainVH) holder).title.setText(key);
        ((MainVH) holder).caret.setImageResource(expanded ? R.drawable.ic_collapse : R.drawable.ic_expand);
    }

    @Override
    public void onBindViewHolder(SectionedViewHolder holder, int section, final int relativePosition, int absolutePosition) {
        final String key = (String) mapLeague.keySet().toArray()[section];
        PlayListYoutube.Snippet snippet = mapLeague.get(key).getItems().get(relativePosition).getSnippet();
        if(snippet.getThumbnails()!=null)PicassoLoader.getInstance(mContext).
                load(snippet.getThumbnails().getHigh().getUrl()).placeholder(R.drawable.default_video).
                error(R.drawable.default_video).into((((LeagueAdapter.ViewHolder) holder).imgVideo));
        ((LeagueAdapter.ViewHolder) holder).textViewName.setText(snippet.getTitle());

        Calendar currentTime = Calendar.getInstance();
        Calendar publsihTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.DATE_TIME_FORMAT1);
        try {
            publsihTime.setTime(sdf.parse(snippet.getPublishedAt()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((LeagueAdapter.ViewHolder) holder).textViewTime.setText(DateTimeUtil.findTimeAgo(currentTime,publsihTime));

        ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String videoID = mapLeague.get(key).getItems().get(relativePosition).getSnippet().getResourceId().getVideoId();
                listener.onClick(videoID);
            }
        });
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        return super.getItemViewType(section, relativePosition, absolutePosition);
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
                layout = R.layout.item_league_video;
                View v2 = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                return new ViewHolder(v2);

            default:
                layout = R.layout.list_item_header;
                View v3 = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                return new MainVH(v3, this);

        }
    }

    static class MainVH extends SectionedViewHolder implements View.OnClickListener {

        Toast toast;
        final TextView title;
        final ImageView caret;
        final LeagueAdapter adapter;

        MainVH(View itemView, LeagueAdapter adapter) {
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
        public RelativeLayout layoutContent;
        public ImageView imgVideo;
        public TextView textViewName;
        public TextView textViewTime;

        public ViewHolder(View v) {
            super(v);
            layoutContent = (RelativeLayout) v.findViewById(R.id.layout_content);
            imgVideo = (ImageView) v.findViewById(R.id.imgVideo);
            textViewName = (TextView) v.findViewById(R.id.txtName);
            textViewTime = (TextView) v.findViewById(R.id.txt_time);
        }
    }

    public interface OnItemClickListener {
        void onClick(String videoID);
    }
}
