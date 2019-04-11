package com.rhino.easydev.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.View;

import com.rhino.ui.base.BaseSimpleTitleFragment;

/**
 * @author rhino
 * @since Create on 2018/9/20.
 */
public abstract class BaseSimpleTitleHttpFragment<T extends ViewDataBinding> extends BaseSimpleTitleFragment {

    public T dataBinding;

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
        return true;
    }

}
