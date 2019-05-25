package com.rhino.easydev.base.dialog;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;

import com.rhino.dialog.base.BaseDialogFragment;
import com.rhino.easydev.utils.CommonHttpUtils;
import com.rhino.ui.utils.ui.ToastUtils;

import java.lang.ref.WeakReference;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public abstract class BaseHttpDialogFragment<T extends ViewDataBinding> extends BaseDialogFragment {

    public CommonHttpUtils httpUtils;
    public T dataBinding;
    public Handler handler;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.bind(mParentView);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (httpUtils != null) {
            httpUtils.dismissLoadingDialog();
        }
    }

    @Override
    protected boolean initData() {
        httpUtils = new CommonHttpUtils(getActivity());
        handler = new MyHandler(this);
        return true;
    }

    public void showToast(String message) {
        ToastUtils.show(message);
    }

    public void handleMessageOs(@NonNull Message message) {
    }

    private static final class MyHandler extends Handler {
        private final WeakReference<BaseHttpDialogFragment> reference;

        private MyHandler(BaseHttpDialogFragment obj) {
            reference = new WeakReference<>(obj);
        }

        @Override
        public void handleMessage(Message message) {
            BaseHttpDialogFragment activity = reference.get();
            if (activity != null && message != null) {
                activity.handleMessageOs(message);
            }
        }
    }

}
