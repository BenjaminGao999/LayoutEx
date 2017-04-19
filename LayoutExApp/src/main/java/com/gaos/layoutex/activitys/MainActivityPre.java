package com.gaos.layoutex.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import com.gaos.layoutex.R;
import com.gaos.layoutex.animUtils.SlideAnimationUtil;
import com.gaos.layoutex.views.MyFrameLayoutA;
import com.gaos.layoutex.views.MyFrameLayoutB;

public class MainActivityPre extends AppCompatActivity implements View.OnClickListener {


    private FrameLayout flContainer;
    private MyFrameLayoutA myFrameLayoutA;
    private MyFrameLayoutB frameLayoutB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setInitView();
    }


    private void initView() {
        Button btnToB = (Button) findViewById(R.id.btn_toB);
        btnToB.setOnClickListener(this);
        flContainer = (FrameLayout) findViewById(R.id.fl_container_main);
    }

    private void setInitView() {
        myFrameLayoutA = new MyFrameLayoutA(this);
        flContainer.addView(myFrameLayoutA, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_toB:
                toB();
                break;
            default:
                break;
        }
    }

    private void toB() {
        Animation animationA = AnimationUtils.loadAnimation(this, R.anim.slide_to_left);
        myFrameLayoutA.startAnimation(animationA);
        animationA.setFillAfter(true);
        frameLayoutB = new MyFrameLayoutB(this);
        flContainer.addView(frameLayoutB, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Animation animationB = AnimationUtils.loadAnimation(this, R.anim.slide_from_right);
        frameLayoutB.startAnimation(animationB);
        animationB.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
//                flContainer.removeView(myFrameLayoutA);
                myFrameLayoutA.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

//        myFrameLayoutA = null;
////        frameLayoutB = null;
//        System.gc();
    }

    @Override
    public void onBackPressed() {
////        super.onBackPressed();
//        myFrameLayoutA = new MyFrameLayoutA(this);
////        frameLayoutB = new MyFrameLayoutB(this);
        Animation animationA = AnimationUtils.loadAnimation(this, R.anim.slide_to_right);
        frameLayoutB.startAnimation(animationA);
        animationA.setFillAfter(true);


//        flContainer.addView(myFrameLayoutA, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        myFrameLayoutA.setVisibility(View.VISIBLE);
        Animation animationB = AnimationUtils.loadAnimation(this, R.anim.slide_from_left);
        myFrameLayoutA.startAnimation(animationB);
        animationB.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                flContainer.removeView(frameLayoutB);
//                frameLayoutB = null;
//                System.gc();
                frameLayoutB.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
