package chickenzero.ht.com.lienquan.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.customize.SquareImageView;
import chickenzero.ht.com.lienquan.models.Skill;

/**
 * Created by QuyDV on 4/28/17.
 */

public class SupportSkillAdapter extends RecyclerView.Adapter{
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface OnItemClickListener {
        void onClick(Skill hero);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SquareImageView imgItem;
        public TextView txtNameItem;

        public ViewHolder(View v) {
            super(v);
            imgItem = (SquareImageView) v.findViewById(R.id.img_hero);
            txtNameItem = (TextView) v.findViewById(R.id.txt_name);
        }
    }
}
