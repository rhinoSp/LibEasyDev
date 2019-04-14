package com.rhino.easydev.dialog;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.rhino.dialog.base.BaseSimpleDialogFragment;
import com.rhino.easydev.R;
import com.rhino.easydev.databinding.DialogWheelSelectBinding;


/**
 * @author rhino
 * @since Create on 2019/4/13.
 **/
public class WheelSelectDialogFragment extends BaseSimpleDialogFragment {

    private DialogWheelSelectBinding dataBinding;
    private String[] wheel1SelectTextArray;
    private String[] wheel2SelectTextArray;
    private String[] wheel3SelectTextArray;
    private String wheel1Label;
    private String wheel2Label;
    private String wheel3Label;

    public WheelSelectDialogFragment() {
        setTitle("Choose");
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AnimationTranBottomDialog);
        setWindowGravity(Gravity.BOTTOM);
        setWindowWidth(WindowManager.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void setContent() {
        setContentView(R.layout.dialog_wheel_select);
    }

    @Override
    protected boolean initData() {
        return true;
    }

    @Override
    protected void initView() {
        dataBinding = DataBindingUtil.bind(mContentView);
        mLlDialog.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        mLlDialog.setBackgroundColor(Color.WHITE);

        dataBinding.wheelView1.setLabel(wheel1Label);
        dataBinding.wheelView2.setLabel(wheel2Label);
        dataBinding.wheelView3.setLabel(wheel3Label);
        dataBinding.wheelView1.setDisplayedValues(wheel1SelectTextArray);
        dataBinding.wheelView2.setDisplayedValues(wheel2SelectTextArray);
        dataBinding.wheelView3.setDisplayedValues(wheel3SelectTextArray);

        dataBinding.wheelView1.setVisibility(wheel1SelectTextArray == null ? View.GONE : View.VISIBLE);
        dataBinding.wheelView2.setVisibility(wheel2SelectTextArray == null ? View.GONE : View.VISIBLE);
        dataBinding.wheelView3.setVisibility(wheel3SelectTextArray == null ? View.GONE : View.VISIBLE);
    }

    public void setWheel1Label(String label) {
        this.wheel1Label = label;
        if (dataBinding != null) {
            dataBinding.wheelView1.setLabel(label);
        }
    }

    public void setWheel1SelectList(String[] textArray) {
        this.wheel1SelectTextArray = textArray;
        if (dataBinding != null) {
            dataBinding.wheelView1.setDisplayedValues(textArray);
        }
    }

    public int getWheel1SelectIndex() {
        return dataBinding.wheelView1.getValue()-1;
    }

    public String getWheel1SelectText() {
        return wheel1SelectTextArray[getWheel1SelectIndex()];
    }

    public void setWheel2Label(String label) {
        this.wheel2Label = label;
        if (dataBinding != null) {
            dataBinding.wheelView2.setLabel(label);
        }
    }

    public void setWheel2SelectList(String[] textArray) {
        this.wheel2SelectTextArray = textArray;
        if (dataBinding != null) {
            dataBinding.wheelView2.setDisplayedValues(textArray);
        }
    }

    public int getWheel2SelectIndex() {
        return dataBinding.wheelView2.getValue()-1;
    }

    public String getWheel2SelectText() {
        return wheel2SelectTextArray[getWheel2SelectIndex()];
    }

    public void setWheel3Label(String label) {
        this.wheel3Label = label;
        if (dataBinding != null) {
            dataBinding.wheelView3.setLabel(label);
        }
    }

    public void setWheel3SelectList(String[] textArray) {
        this.wheel3SelectTextArray = textArray;
        if (dataBinding != null) {
            dataBinding.wheelView3.setDisplayedValues(textArray);
        }
    }

    public int getWheel3SelectIndex() {
        return dataBinding.wheelView3.getValue()-1;
    }

    public String getWheel3SelectText() {
        return wheel3SelectTextArray[getWheel3SelectIndex()];
    }


}
