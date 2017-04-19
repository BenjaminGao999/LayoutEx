package com.gaos.layoutex.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gaos.layoutex.R;

/**
 * Author:　Created by benjamin
 * DATE :  2017/4/18 9:36
 * versionCode:　1.0.0
 */

public class MyFrameLayoutB extends FrameLayout {
    public MyFrameLayoutB(Context context) {
        super(context);
        addChildView();
    }

    private void addChildView() {
        View child1 = LayoutInflater.from(getContext()).inflate(R.layout.layout_fl_b, null, false);
        addView(child1, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
