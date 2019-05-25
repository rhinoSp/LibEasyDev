package com.rhino.ed.ui;

import android.view.View;

import com.rhino.dialog.BottomSelectDialogFragment;
import com.rhino.dialog.impl.IOnDialogKeyClickListener;
import com.rhino.dialog.picker.DatePickerDialogFragment;
import com.rhino.easydev.base.BaseSimpleTitleHttpActivity;
import com.rhino.easydev.dialog.DialogFactory;
import com.rhino.easydev.dialog.WheelSelectDialogFragment;
import com.rhino.ed.R;
import com.rhino.ed.databinding.ActivityMainBinding;
import com.rhino.ed.http.result.BaseResult;
import com.rhino.ed.http.HttpApi;
import com.rhino.ui.utils.LogUtils;
import com.rhino.ui.utils.TimeUtils;
import com.rhino.ui.utils.ui.ToastUtils;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseSimpleTitleHttpActivity<ActivityMainBinding> {

    @Override
    public void setContent() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        mActionBarHelper.setTitleBackKeyVisible(false);
    }

    public void onViewClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_request) {
            httpUtils.loadingDialogFragment.setText("检测升级中");
            httpUtils.request(HttpApi.api().getVersion(), (Consumer<BaseResult<String>>) result -> {
                LogUtils.d("请求成功：" + (result != null ? result.toString() : null));
                showToast("请求成功！" + (result != null ? result.toString() : null));
            }, null);
        } else if (id == R.id.bt_date_picker) {
            DialogFactory.buildDatePicker(this, DatePickerDialogFragment.STYLE_YYYY_MM_DD_HH_MM_SS, new IOnDialogKeyClickListener<DatePickerDialogFragment>() {
                @Override
                public void onClick(DatePickerDialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                    showToast(TimeUtils.formatTime(dialogFragment.getCalendar().getTimeInMillis(), "yyyy-MM-dd HH:mm:ss"));
                }
            }).show(this);
        } else if (id == R.id.bt_take_picture) {
            DialogFactory.showTakePhotoDialog(this, new IOnDialogKeyClickListener<BottomSelectDialogFragment>() {
                @Override
                public void onClick(BottomSelectDialogFragment dialogFragment) {
                    showToast("拍照");
                }
            }, new IOnDialogKeyClickListener<BottomSelectDialogFragment>() {
                @Override
                public void onClick(BottomSelectDialogFragment dialogFragment) {
                    showToast("相册");
                }
            });
        } else if (id == R.id.bt_wheel_select) {
            String[] texts = new String[] {"A", "B", "C", "D", "E", "F"};
            DialogFactory.buildWheelSelectDialog(this, "选择类型", texts, new IOnDialogKeyClickListener<WheelSelectDialogFragment>() {
                @Override
                public void onClick(WheelSelectDialogFragment dialogFragment) {
                    dialogFragment.dismiss();
                    showToast(dialogFragment.getWheel1SelectText());
                }
            }).show(this);
        }
    }

}
