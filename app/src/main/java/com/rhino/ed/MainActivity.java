package com.rhino.ed;

import android.util.Log;
import android.view.View;

import com.rhino.easydev.base.BaseSimpleTitleHttpActivity;
import com.rhino.ed.databinding.ActivityMainBinding;
import com.rhino.ed.http.BaseResult;
import com.rhino.ed.http.HttpUtils;
import com.rhino.ed.http.result.HomeModel;
import com.rhino.ui.utils.ui.ToastUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
            getHomeModel();
        }
    }

    private void getHomeModel() {
        Disposable disposable = HttpUtils.getApiService().getHomeModel()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showToast("加载中...");
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("DDDD", "请求完成！");
                        showToast("请求完成！");
                    }
                })
                .subscribe(new Consumer<BaseResult<String>>() {
                               @Override
                               public void accept(BaseResult<String> result) throws Exception {
                                   Log.d("DDDD", "请求成功：" + (result != null ? result.toString() : null));
                                   showToast("请求成功！" + (result != null ? result.toString() : null));
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("DDDD", "请求失败：" + throwable.toString());
                                showToast("请求失败！" + throwable.toString());
                            }
                        });
    }

    private void showToast(String msg) {
        ToastUtils.show(msg);
    }

}
