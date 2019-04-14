package com.rhino.easydev.base.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.rhino.rv.base.BaseHolderData;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public abstract class BaseRecvHolderData<T extends ViewDataBinding> extends BaseHolderData {

    public abstract void bindView(T dataBinding, int position);

    @NonNull
    @Override
    public String getHolderClassName() {
        return BaseRecvHolder.class.getName();
    }

}
