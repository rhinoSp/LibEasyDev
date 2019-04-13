package com.rhino.easydev.dialog;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;

import com.rhino.dialog.BottomSelectDialogFragment;
import com.rhino.dialog.impl.DefaultDialogListener;
import com.rhino.dialog.impl.IOnDialogKeyClickListener;
import com.rhino.dialog.picker.DatePickerDialogFragment;
import com.rhino.easydev.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class DialogFactory {

    /**
     * 底部Wheel选择对话框
     *
     * @param fragmentActivity         fragmentActivity
     * @param title                    标题
     * @param textArray                选项数组
     * @param onDialogKeyClickListener 监听
     */
    public static WheelSelectDialogFragment buildWheelSelectDialog(FragmentActivity fragmentActivity, String title, String[] textArray, IOnDialogKeyClickListener<WheelSelectDialogFragment> onDialogKeyClickListener) {
        WheelSelectDialogFragment dialogFragment = new WheelSelectDialogFragment();
        dialogFragment.setTitle(title);
        dialogFragment.setNegativeKeyText("取消");
        dialogFragment.setPositiveKeyText("确定");
        dialogFragment.setTitleTextColor(fragmentActivity.getResources().getColor(R.color.theme_color));
        dialogFragment.setNegativeKeyColor(fragmentActivity.getResources().getColor(R.color.color_nor_themcolor_pre_themecolorlight));
        dialogFragment.setPositiveKeyColor(fragmentActivity.getResources().getColor(R.color.color_nor_themcolor_pre_themecolorlight));
        dialogFragment.setPositiveKeyClickListener(onDialogKeyClickListener);
        dialogFragment.setWheel1SelectList(textArray);
        return dialogFragment;
    }

    /**
     * 选择时间对话框
     *
     * @param fragmentActivity         fragmentActivity
     * @param onDialogKeyClickListener 监听
     */
    public static DatePickerDialogFragment buildDatePicker(FragmentActivity fragmentActivity, int style, IOnDialogKeyClickListener<DatePickerDialogFragment> onDialogKeyClickListener) {
        DatePickerDialogFragment dialogFragment = new DatePickerDialogFragment();
        dialogFragment.setStyle(style);
        dialogFragment.setYearCount(60);
        dialogFragment.setTitle("选择时间");
        dialogFragment.setNegativeKeyText("取消");
        dialogFragment.setPositiveKeyText("确定");
        dialogFragment.setTitleTextColor(fragmentActivity.getResources().getColor(R.color.theme_color));
        dialogFragment.setNegativeKeyColor(fragmentActivity.getResources().getColor(R.color.color_nor_themcolor_pre_themecolorlight));
        dialogFragment.setPositiveKeyColor(fragmentActivity.getResources().getColor(R.color.color_nor_themcolor_pre_themecolorlight));
        dialogFragment.setPositiveKeyClickListener(onDialogKeyClickListener);
        return dialogFragment;
    }

    /**
     * 显示从底部弹出选择对话框
     *
     * @param title    标题
     * @param itemList 选择数据
     * @param listener 监听
     */
    public static BottomSelectDialogFragment buildBottomSheetSelectDialog(String title,
                                                                          List<BottomSelectDialogFragment.Item> itemList,
                                                                          BottomSelectDialogFragment.IOnItemClickListener listener) {
        BottomSelectDialogFragment dialogFragment = new BottomSelectDialogFragment();
        dialogFragment.setTitleText(title);
        dialogFragment.setCancelable(true);
        dialogFragment.setIOnDialogListener(new DefaultDialogListener());
        dialogFragment.setItems(itemList);
        dialogFragment.setOnItemClickListener(listener);
        dialogFragment.setItemCancelText("取消");
        return dialogFragment;
    }

    /**
     * 显示选择照片对话框
     *
     * @param fragmentActivity  fragmentActivity
     * @param takePhotoListener 拍照监听
     * @param takeAlbumListener 相册监听
     */
    public static BottomSelectDialogFragment showTakePhotoDialog(FragmentActivity fragmentActivity, IOnDialogKeyClickListener<BottomSelectDialogFragment> takePhotoListener,
                                                                 IOnDialogKeyClickListener<BottomSelectDialogFragment> takeAlbumListener) {
        List<BottomSelectDialogFragment.Item> list = new ArrayList<>();
        list.add(BottomSelectDialogFragment.Item.build("拍照", Color.BLACK));
        list.add(BottomSelectDialogFragment.Item.build("相册", Color.BLACK));
        BottomSelectDialogFragment dialogFragment = buildBottomSheetSelectDialog("", list, new BottomSelectDialogFragment.IOnItemClickListener() {
            @Override
            public void onItemClick(BottomSelectDialogFragment dialog, BottomSelectDialogFragment.Item item) {
                dialog.dismiss();
                if ("拍照".equals(item.mText)) {
                    takePhotoListener.onClick(dialog);
                } else if ("相册".equals(item.mText)) {
                    takeAlbumListener.onClick(dialog);
                }
            }
        });
        dialogFragment.show(fragmentActivity);
        return dialogFragment;
    }

}
