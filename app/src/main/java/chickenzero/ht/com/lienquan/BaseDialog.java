package chickenzero.ht.com.lienquan;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by billymobile on 1/9/17.
 */

public abstract class BaseDialog extends Dialog {

    protected Context mContext;
    private Handler mHandler;
    protected MainActivity mActivity;
    protected Unbinder mUnbinder;

    public BaseDialog(Context context) {
        super(context);
        mContext = context;
        mActivity=(MainActivity) context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(mContext, R.color.cmn_transparent)));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = View.inflate(getContext(), getViewContent(), null);
        mUnbinder = ButterKnife.bind(this,rootView);
        setContentView(rootView);
        initUI();
        mHandler = new Handler(Looper.getMainLooper());
    }



    protected Handler getMainHandler() {
        return mHandler;
    }

    protected abstract int getViewContent();

    protected abstract void initUI();

}
