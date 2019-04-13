package com.rhino.ed;

import android.view.View;

import com.rhino.easydev.base.BaseSimpleTitleHttpActivity;
import com.rhino.ed.databinding.ActivityMainBinding;
import com.rhino.ed.http.BaseResult;
import com.rhino.ed.http.HttpApi;
import com.rhino.ui.utils.LogUtils;
import com.rhino.ui.utils.ui.ToastUtils;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseSimpleTitleHttpActivity<ActivityMainBinding> {

    @Override
    public void setContent() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
    }

    public void onViewClick(View v) {
        int id = v.getId();
        if (id == R.id.button) {
            getVersion();
        }
    }

    private void getVersion() {
        httpUtils.loadingDialogFragment.setText("检测升级中");
        httpUtils.request(HttpApi.api().getVersion(), (Consumer<BaseResult<String>>) result -> {
            LogUtils.d("请求成功：" + (result != null ? result.toString() : null));
            showToast("请求成功！" + (result != null ? result.toString() : null));
        }, null);
    }

    private void showToast(String msg) {
        ToastUtils.show(msg);
    }

}
