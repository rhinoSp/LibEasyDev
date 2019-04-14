package com.rhino.easydev.base.recyclerview;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.rhino.rv.base.BaseHolder;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class BaseRecvHolder extends BaseHolder<BaseRecvHolderData> {


    public BaseRecvHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void bindView(BaseRecvHolderData data, int position) {
        data.bindView(DataBindingUtil.bind(itemView), position);
    }


}
