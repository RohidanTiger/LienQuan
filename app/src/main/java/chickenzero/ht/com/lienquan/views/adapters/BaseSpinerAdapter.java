package chickenzero.ht.com.lienquan.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import chickenzero.ht.com.lienquan.R;

/**
 * Created by QuyDV on 5/5/17.
 */

public class BaseSpinerAdapter extends ArrayAdapter<String> {
    private int mSelectedIndex = -1;

    public void setSelection(int position) {
        mSelectedIndex =  position;
        notifyDataSetChanged();
    }

    public BaseSpinerAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View itemView =  super.getDropDownView(position, convertView, parent);
        if (position == mSelectedIndex && position != getCount() - 1) {
            itemView.setBackgroundResource(R.color.cmn_text_gray);
        } else {
            itemView.setBackgroundResource(R.color.cmn_dark);
        }
        return itemView;
    }
}
