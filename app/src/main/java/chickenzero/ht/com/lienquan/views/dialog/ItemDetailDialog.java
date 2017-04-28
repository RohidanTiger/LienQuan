package chickenzero.ht.com.lienquan.views.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import chickenzero.ht.com.lienquan.BaseDialog;
import chickenzero.ht.com.lienquan.R;
import chickenzero.ht.com.lienquan.models.Item;
import chickenzero.ht.com.lienquan.utils.PicassoLoader;
import chickenzero.ht.com.lienquan.views.adapters.ItemAdapter;

/**
 * Created by QuyDV on 4/17/17.
 */

public class ItemDetailDialog extends BaseDialog{
    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.txt_detail)
    TextView txtDetail;

    private Item mItem;
    private Context mContext;
    public ItemDetailDialog(Context context, Item item) {
        super(context);
        mContext = context;
        mItem = item;
    }


    @Override
    protected int getViewContent() {
        return R.layout.dialog_item_detail;
    }

    @Override
    protected void initUI() {
        String imgUrl = "file:///android_asset/LienQuan/Items/".concat(String.valueOf(mItem.getId())).concat(".jpg");
        PicassoLoader.getInstance(mContext).with(mContext).load(imgUrl).into(imageView);
        txtName.setText(mItem.getName());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            txtDetail.setText(Html.fromHtml(mItem.getDesc(),Html.FROM_HTML_MODE_COMPACT));
        }else{
            txtDetail.setText(Html.fromHtml(mItem.getDesc()));
        }

    }
}
