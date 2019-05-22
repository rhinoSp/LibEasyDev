package com.rhino.easydev.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;

import com.rhino.easydev.utils.CommonHttpUtils;
import com.rhino.ui.base.BaseSimpleTitleFragment;

import java.lang.ref.WeakReference;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public abstract class BaseSimpleTitleHttpFragment<T extends ViewDataBinding> extends BaseSimpleTitleFragment {

    public CommonHttpUtils httpUtils;
    public T dataBinding;
    public Handler handler;

    @Override
    public void setContentView(int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, null, false));
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        dataBinding = DataBindingUtil.bind(view);
    }

    @Override
    public boolean initData() {
        httpUtils = new CommonHttpUtils(getActivity());
        handler = new MyHandler(this);
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (httpUtils != null) {
            httpUtils.dismissLoadingDialog();
        }
    }

    public void handleMessageOs(@NonNull Message message) {
    }

    private static final class MyHandler extends Handler {
        private final WeakReference<BaseSimpleTitleHttpFragment> reference;

        private MyHandler(BaseSimpleTitleHttpFragment obj) {
            reference = new WeakReference<>(obj);
        }

        @Override
        public void handleMessage(Message message) {
            BaseSimpleTitleHttpFragment activity = reference.get();
            if (activity != null && message != null) {
                activity.handleMessageOs(message);
            }
        }
    }

}
