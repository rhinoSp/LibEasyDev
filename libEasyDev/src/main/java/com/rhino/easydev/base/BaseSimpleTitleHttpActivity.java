package com.rhino.easydev.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.rhino.dialog.LoadingDialogFragment;
import com.rhino.dialog.impl.IOnDialogListener;
import com.rhino.easydev.utils.CommonHttpUtils;
import com.rhino.ui.base.BaseSimpleTitleActivity;
import com.rhino.ui.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author rhino
 * @since Create on 2018/9/19.
 **/
public abstract class BaseSimpleTitleHttpActivity<T extends ViewDataBinding> extends BaseSimpleTitleActivity {

    public CommonHttpUtils httpUtils;
    public T dataBinding;

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
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (httpUtils != null) {
            httpUtils.dismissLoadingDialog();
        }
    }

}
