package com.rhino.easydev.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rhino.easydev.R;
import com.rhino.easydev.base.BaseSimpleTitleHttpActivity;
import com.rhino.easydev.databinding.ActivityImageDetailsBinding;
import com.rhino.easydev.databinding.LayoutZoomImageBinding;
import com.rhino.easydev.glide.GlideApp;

import java.util.ArrayList;

/**
 * <p>查看大图的Activity界面。</p>
 *
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class ImageDetailsActivity extends BaseSimpleTitleHttpActivity<ActivityImageDetailsBinding> implements OnPageChangeListener {

    private ArrayList<String> mImageUrlList;
    private int mPosition;
    private int mCurrentPosition = 0;

    public static void showThisPage(Activity activity, ArrayList<String> mImageUrlList, int mPosition, int requestCode) {
        Intent intent = new Intent(activity, ImageDetailsActivity.class);
        intent.putExtra("mImageUrlList", mImageUrlList);
        intent.putExtra("mPosition", mPosition);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void setContent() {
        setContentView(R.layout.activity_image_details);
    }

    @Override
    public void initExtraData() {
        super.initExtraData();
        mImageUrlList = getIntent().getStringArrayListExtra("mImageUrlList");
        mCurrentPosition = mPosition = getIntent().getIntExtra("mPosition", 0);
    }

    @Override
    public void initView() {
        ViewPagerAdapter viewAdapter = new ViewPagerAdapter();
        dataBinding.viewPager.setAdapter(viewAdapter);
        dataBinding.viewPager.setCurrentItem(mPosition);
        dataBinding.viewPager.addOnPageChangeListener(this);
        dataBinding.viewPager.setEnabled(false);
        dataBinding.viewPager.setOffscreenPageLimit(3);
        dataBinding.viewPager.setCurrentItem(mCurrentPosition);
        // 设定当前的页数和总页数
        dataBinding.pageText.setText((mPosition + 1) + "/" + mImageUrlList.size());

        mActionBarHelper.setTitle("图片查看");
    }

    class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageUrlList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            LayoutZoomImageBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getApplicationContext()), R.layout.layout_zoom_image, container, false);
            final String imageUrl = mImageUrlList.get(position);
            GlideApp.with(getApplicationContext())
                    .load(imageUrl)
                    .fitCenter()
                    .placeholder(R.mipmap.ic_none_img)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(binding.zoomImageView);
            container.addView(binding.getRoot(), 0);
            return binding.getRoot();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int currentPage) {
        mCurrentPosition = currentPage;
        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        dataBinding.pageText.setText((currentPage + 1) + "/" + mImageUrlList.size());
    }

}
