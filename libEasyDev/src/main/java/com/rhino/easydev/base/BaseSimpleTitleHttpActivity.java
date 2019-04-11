package com.rhino.easydev.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

import com.rhino.ui.base.BaseSimpleTitleActivity;

/**
 * @author rhino
 * @since Create on 2018/9/19.
 **/
public abstract class BaseSimpleTitleHttpActivity<T extends ViewDataBinding> extends BaseSimpleTitleActivity {

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
        return true;
    }

}
