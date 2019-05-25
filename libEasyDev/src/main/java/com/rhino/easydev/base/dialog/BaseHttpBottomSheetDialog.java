package com.rhino.easydev.base.dialog;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.rhino.easydev.utils.CommonHttpUtils;
import com.rhino.ui.impl.IOnNoMultiClickListener;
import com.rhino.ui.utils.ui.ScreenUtils;
import com.rhino.ui.utils.ui.ToastUtils;

import java.lang.ref.WeakReference;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class BaseHttpBottomSheetDialog<T extends ViewDataBinding> extends BottomSheetDialog {

    public CommonHttpUtils httpUtils;
    public T dataBinding;
    public Handler handler;

    /**
     * The parent view.
     */
    public View mParentView;
    /**
     * The OnClickListener.
     */
    public IOnNoMultiClickListener mBaseOnClickListener;
    /**
     * The OnLongClickListener.
     */
    public View.OnLongClickListener mBaseOnLongClickListener;


    public BaseHttpBottomSheetDialog(@NonNull FragmentActivity activity) {
        super(activity);
        setOwnerActivity(activity);
    }

    public BaseHttpBottomSheetDialog(@NonNull FragmentActivity activity, int theme) {
        super(activity, theme);
        setOwnerActivity(activity);
    }

    protected BaseHttpBottomSheetDialog(@NonNull FragmentActivity activity, boolean cancelable, OnCancelListener cancelListener) {
        super(activity, cancelable, cancelListener);
        setOwnerActivity(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        httpUtils = new CommonHttpUtils((FragmentActivity) getOwnerActivity());
        handler = new MyHandler(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mParentView = view;
        initBaseView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        mParentView = view;
        initBaseView();
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        mParentView = View.inflate(getContext(), layoutResId, null);
        initBaseView();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (httpUtils != null) {
            httpUtils.dismissLoadingDialog();
        }
    }

    /**
     * Init base view.
     */
    private void initBaseView() {
        dataBinding = DataBindingUtil.bind(mParentView);
        mParentView.setOnClickListener(v -> {
        });
        View parent = (View) mParentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        mParentView.measure(0, 0);
        behavior.setPeekHeight(ScreenUtils.dip2px(getContext(), 400));
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) parent.getLayoutParams();
        params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
        parent.setLayoutParams(params);
        getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(0x00000000);
    }

    /**
     * Register a callback to be invoked when this view is clicked. If this view is not
     * clickable, it becomes clickable.
     *
     * @param views the view
     */
    public void setBaseOnClickListener(View... views) {
        if (views != null) {
            for (View v : views) {
                if (null != v) {
                    v.setOnClickListener(getBaseOnClickListener());
                }
            }
        }
    }

    /**
     * Register a callback to be invoked when this view is clicked and held. If this view is not
     * long clickable, it becomes long clickable.
     *
     * @param views the view
     */
    public void setBaseOnLongClickListener(View... views) {
        if (views != null) {
            for (View v : views) {
                if (null != v) {
                    v.setOnLongClickListener(getBaseOnLongClickListener());
                }
            }
        }
    }

    /**
     * Get the OnClickListener
     *
     * @return the OnClickListener
     */
    public View.OnClickListener getBaseOnClickListener() {
        if (mBaseOnClickListener == null) {
            mBaseOnClickListener = new IOnNoMultiClickListener() {
                @Override
                public void onNoMultiClick(View v) {
                    baseOnClickListener(v);
                }
            };
        }
        return mBaseOnClickListener;
    }

    /**
     * Get the OnLongClickListener
     *
     * @return the OnLongClickListener
     */
    public View.OnLongClickListener getBaseOnLongClickListener() {
        if (mBaseOnLongClickListener == null) {
            mBaseOnLongClickListener = new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return baseOnLongClickListener(v);
                }
            };
        }
        return mBaseOnLongClickListener;
    }

    /**
     * Deal the click listener.
     *
     * @param v the click view
     */
    public void baseOnClickListener(View v) {
    }

    /**
     * Deal the long click listener.
     *
     * @param v the long click view
     */
    public boolean baseOnLongClickListener(View v) {
        return false;
    }

    public void showToast(String message) {
        ToastUtils.show(message);
    }

    public void handleMessageOs(@NonNull Message message) {
    }

    private static final class MyHandler extends Handler {
        private final WeakReference<BaseHttpBottomSheetDialog> reference;

        private MyHandler(BaseHttpBottomSheetDialog obj) {
            reference = new WeakReference<>(obj);
        }

        @Override
        public void handleMessage(Message message) {
            BaseHttpBottomSheetDialog activity = reference.get();
            if (activity != null && message != null) {
                activity.handleMessageOs(message);
            }
        }
    }
    
}
