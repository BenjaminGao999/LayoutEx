package com.gaos.layoutex.views;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.gaos.layoutex.R;

/**
 * Author:　Created by benjamin
 * DATE :  2017/4/18 9:36
 * versionCode:　1.0.0
 */

public class MyFrameLayoutA extends FrameLayout {
    public MyFrameLayoutA(Context context) {
        super(context);
        addChildView();
    }

    private void addChildView() {
        View childView1 =  View.inflate(getContext(), R.layout.layout_fl_a, this);
    }
}
