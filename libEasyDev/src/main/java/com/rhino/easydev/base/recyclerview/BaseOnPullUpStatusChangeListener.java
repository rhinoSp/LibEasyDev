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
 * <p>上拉加载</p>
 *
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class BaseOnPullUpStatusChangeListener implements IOnPullStatusChangeListener {
    public View mPullFooterView;
    public ImageView mPullFooterImage;
    public TextView mPullFooterText;

    public BaseOnPullUpStatusChangeListener(Context context) {
        mPullFooterView = LayoutInflater.from(context).inflate(R.layout.layout_pull_up_load_footer, null, false);
        mPullFooterImage = mPullFooterView.findViewById(R.id.footer_progress);
        mPullFooterText = mPullFooterView.findViewById(R.id.footer_txt);
    }

    @Override
    public View getView() {
        return mPullFooterView;
    }

    @Override
    public void onPullChanged(PullStatus status) {
        switch (status) {
            case CANCEL_REFRESH:
                stopRotateAnim();
                break;
            case PULL_REFRESH:
                stopRotateAnim();
                mPullFooterImage.setVisibility(View.GONE);
                mPullFooterText.setText("上拉加载");
                break;
            case RELEASE_TO_REFRESH:
                stopRotateAnim();
                mPullFooterImage.setVisibility(View.GONE);
                mPullFooterText.setText("释放立即加载");
                break;
            case START_REFRESHING:
                startRotateAnim();
                mPullFooterImage.setVisibility(View.VISIBLE);
                mPullFooterText.setText("正在加载");
                break;
            case STOP_REFRESH:
                stopRotateAnim();
                mPullFooterImage.setVisibility(View.GONE);
                mPullFooterText.setText("加载完成");
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
        mPullFooterImage.startAnimation(anim);
    }

    public void stopRotateAnim() {
        mPullFooterImage.clearAnimation();
    }
}
