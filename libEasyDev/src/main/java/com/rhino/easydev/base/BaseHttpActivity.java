package com.rhino.easydev.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;

import com.rhino.easydev.utils.CommonHttpUtils;
import com.rhino.ui.base.BaseActivity;
import com.rhino.ui.utils.ui.ToastUtils;

import java.lang.ref.WeakReference;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public abstract class BaseHttpActivity<T extends ViewDataBinding> extends BaseActivity {

    public CommonHttpUtils httpUtils;
    public T dataBinding;
    public MyHandler handler;

    @Override
    public void setContentView(int layoutResID) {
        setContentView(getLayoutInflater().inflate(layoutResID, null, false));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        dataBinding = DataBindingUtil.bind(view);
    }

    @Override
    public boolean initData() {
        httpUtils = new CommonHttpUtils(this);
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

    public void showToast(String message) {
        ToastUtils.show(message);
    }

    public void handleMessageOs(@NonNull Message message) {
    }

    private static final class MyHandler extends Handler {
        private final WeakReference<BaseHttpActivity> reference;

        private MyHandler(BaseHttpActivity obj) {
            reference = new WeakReference<>(obj);
        }

        @Override
        public void handleMessage(Message message) {
            BaseHttpActivity activity = reference.get();
            if (activity != null && message != null) {
                activity.handleMessageOs(message);
            }
        }
    }

}