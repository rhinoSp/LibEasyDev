package com.rhino.easydev.base.dialog;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.rhino.dialog.base.BaseDialogFragment;
import com.rhino.easydev.utils.CommonHttpUtils;

/**
 * @author rhino
 * @since Create on 2019/2/26.
 */
public abstract class BaseHttpDialogFragment<T extends ViewDataBinding> extends BaseDialogFragment {

    public CommonHttpUtils httpUtils;
    public T dataBinding;

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
        return true;
    }

}
