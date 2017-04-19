package com.gaos.layoutex.views;

import android.content.Intent;
import android.view.KeyEvent;

/**
 * Author:　Created by benjamin
 * DATE :  2017/4/18 16:24
 * versionCode:　1.0.0
 * view和Activity生命周期保持一致，返回等操作信息传递
 */

public interface IPage {
    public boolean onBack();//按返回键时调用此接口，如果处理了返回true否则返回false

    public boolean onStop();//主框架Activity的onStop事件

    public boolean onPause();//主框架Activity的onPause事件

    public boolean onDestroy();//主框架Activity的onDestroy事件

    public boolean onStart();//主框架Activity的onStart事件

    public boolean onResume();//主框架Activity的onResume事件

    public boolean onActivityResult(int requestCode, int resultCode, Intent data);//主框架Activity的onActivityResult事件

    public boolean onActivityKeyDown(int keyCode, KeyEvent event);//主框架Activity的onKeyDown事件

    public boolean onActivityKeyUp(int keyCode, KeyEvent event);//主框架Activity的onKeyUp事件

    public void onClose();//关闭页面时调用此接口通知页面

    public void onRestore();//从其它页面返回时调用
}
