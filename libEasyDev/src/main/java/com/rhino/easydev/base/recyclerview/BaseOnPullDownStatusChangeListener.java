package com.rhino.easydev.base.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.rhino.easydev.R;
import com.rhino.rv.pull.impl.IOnPullStatusChangeListener;

/**
 * <p>下拉刷新</p>
 *
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class BaseOnPullDownStatusChangeListener implements IOnPullStatusChangeListener {
    private View mPullHeaderView;
    private ImageView mPullHeaderImage;
    private TextView mPullHeaderText;

    public BaseOnPullDownStatusChangeListener(Context context) {
        mPullHeaderView = LayoutInflater.from(context).inflate(R.layout.layout_pull_down_refresh_header, null, false);
        mPullHeaderImage = mPullHeaderView.findViewById(R.id.header_progress);
        mPullHeaderText = mPullHeaderView.findViewById(R.id.header_txt);
    }

    @Override
    public View getView() {
        return mPullHeaderView;
    }

    @Override
    public void onPullChanged(PullStatus status) {
        switch (status) {
            case CANCEL_REFRESH:
                stopRotateAnim();
                break;
            case PULL_REFRESH:
                stopRotateAnim();
                mPullHeaderImage.setVisibility(View.GONE);
                mPullHeaderText.setText("下拉刷新");
                break;
            case RELEASE_TO_REFRESH:
                stopRotateAnim();
                mPullHeaderImage.setVisibility(View.GONE);
                mPullHeaderText.setText("释放立即刷新");
                break;
            case START_REFRESHING:
                startRotateAnim();
                mPullHeaderImage.setVisibility(View.VISIBLE);
                mPullHeaderText.setText("正在刷新");
                break;
            case STOP_REFRESH:
                stopRotateAnim();
                mPullHeaderImage.setVisibility(View.GONE);
                mPullHeaderText.setText("刷新完成");
                break;
            default:
                break;
        }
    }

    public void startRotateAnim() {
        Animation anim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(Animation.RESTART);
        anim.setRepeatCount(-1);
        mPullHeaderImage.startAnimation(anim);
    }

    public void stopRotateAnim() {
        mPullHeaderImage.clearAnimation();
    }
}
