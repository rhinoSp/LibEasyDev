package com.rhino.easydev.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.rhino.dialog.LoadingDialogFragment;
import com.rhino.dialog.impl.IOnDialogListener;
import com.rhino.ui.utils.LogUtils;
import com.rhino.ui.utils.ui.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class CommonHttpUtils {

    public FragmentActivity fragmentActivity;
    public LoadingDialogFragment loadingDialogFragment;

    public CommonHttpUtils(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
        this.loadingDialogFragment = new LoadingDialogFragment();
        this.loadingDialogFragment.setText("加载中");
    }

    /**
     * 设置对话框监听
     */
    public void setIOnDialogListener(IOnDialogListener listener) {
        loadingDialogFragment.setIOnDialogListener(listener);
    }

    /**
     * 刷新加载对话框文字描述
     *
     * @param text 描述
     */
    public void updateLoadingDialogText(String text) {
        loadingDialogFragment.setText(text);
    }

    /**
     * 显示加载对话框
     **/
    public void showLoadingDialog(String text) {
        dismissLoadingDialog();
        if (null != loadingDialogFragment && !loadingDialogFragment.isAdded() && fragmentActivity != null && !fragmentActivity.isFinishing()) {
            updateLoadingDialogText(text);
            loadingDialogFragment.show(fragmentActivity);
        }
    }

    /**
     * 显示加载对话框
     **/
    public void showLoadingDialog() {
        dismissLoadingDialog();
        if (null != loadingDialogFragment && !loadingDialogFragment.isAdded() && fragmentActivity != null && !fragmentActivity.isFinishing()) {
            loadingDialogFragment.show(fragmentActivity);
        }
    }

    /**
     * 取消加载对话框
     **/
    public void dismissLoadingDialog() {
        if (null != loadingDialogFragment && loadingDialogFragment.isAdded() && !loadingDialogFragment.isDetached()) {
            loadingDialogFragment.dismiss();
        }
    }

    /**
     * 请求结束回调
     */
    public Action defaultActionFinally = this::dismissLoadingDialog;

    /**
     * 请求开始回调
     */
    public Consumer<Disposable> defaultConsumerStart = disposable -> showLoadingDialog();

    /**
     * 请求失败回调
     */
    public Consumer<Throwable> defaultConsumerError = throwable -> {
        LogUtils.e("请求失败：" + throwable.toString());
        dismissLoadingDialog();
        ToastUtils.show("请求失败！" + throwable.toString());
    };

    /**
     * 处理请求
     *
     * @param observable   observable
     * @param consumerNext consumerNext
     * @return Disposable
     */
    public Disposable request(@NonNull Observable observable, @NonNull Consumer consumerNext) {
        return request(observable, defaultConsumerStart, consumerNext, defaultConsumerError, defaultActionFinally);
    }

    /**
     * 处理请求
     *
     * @param observable    observable
     * @param consumerNext  consumerNext
     * @param consumerError consumerError
     * @return Disposable
     */
    public Disposable request(@NonNull Observable observable, @NonNull Consumer consumerNext, @Nullable Consumer<Throwable> consumerError) {
        return request(observable, defaultConsumerStart, consumerNext, consumerError, defaultActionFinally);
    }

    /**
     * 处理请求
     *
     * @param observable    observable
     * @param consumerStart consumerStart
     * @param consumerNext  consumerNext
     * @param consumerError consumerError
     * @return Disposable
     */
    public Disposable request(@NonNull Observable observable, @Nullable Consumer<Disposable> consumerStart, @NonNull Consumer consumerNext, @Nullable Consumer<Throwable> consumerError) {
        return request(observable, consumerStart, consumerNext, consumerError, defaultActionFinally);
    }

    /**
     * 处理请求
     *
     * @param observable    observable
     * @param consumerStart consumerStart
     * @param consumerNext  consumerNext
     * @param consumerError consumerError
     * @param actionFinally actionFinally
     * @return Disposable
     */
    @SuppressWarnings("unchecked")
    public Disposable request(@NonNull Observable observable, @Nullable Consumer<Disposable> consumerStart, @NonNull Consumer consumerNext, @Nullable Consumer<Throwable> consumerError, @Nullable Action actionFinally) {
        Observable o = observable.subscribeOn(Schedulers.io());
        if (consumerStart != null) {
            o = o.doOnSubscribe(consumerStart).subscribeOn(AndroidSchedulers.mainThread());
        }
        o = o.observeOn(AndroidSchedulers.mainThread());
        if (actionFinally != null) {
            o = o.doFinally(actionFinally);
        }
        return o.subscribe(consumerNext, consumerError == null ? defaultConsumerError : consumerError);
    }

}
