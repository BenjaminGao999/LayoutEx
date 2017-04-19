
package com.gaos.layoutex.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


import com.gaos.layoutex.viewStackManager.PageStackManager;
import com.gaos.layoutex.views.IPage;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    public static final int PAGE_MAIN = 0;                                                         // 页面标记id
    public static final int PAGE_MIX = 1;
    public static final int PAGE_EDIT = 2;
    public static final int PAGE_SAVE = 3;
    private static final int PAGE_HELP = 4;
    private static final int PAGE_SHARE = 5;
    private static final int PAGE_BINDPOCO = 6;
    private static final int PAGE_WELCOME = 7;
    private static final int PAGE_ABOUT = 8;
    public static final int PAGE_CLOUD_ALBUM = 9;
    private static final int PAGE_FIRSPAGE = 10;

    public static MainActivity main = null;
    protected static boolean sBoolKill;
    private int bg_current = 0;

    private FrameLayout mMainFrameLayout;
    protected boolean mPressedExit = false;
    protected boolean mIsSaveAD = false;
    protected boolean mIsUpdateBG = false;
    private FrameLayout mBgContainer;
    private FrameLayout mContainer;
    private FrameLayout mPopupPageContainer;
    private int mCurrentPage = -1;
    private int mLastPage = -1;
    private IPage mPage = null;
    private IPage mTopPage = null;
    private IPage mPopupPage = null;
    private ArrayList<IPage> mPopupPageStack = new ArrayList<IPage>();
    private Bitmap BG_Blue;
    public String relRootUrl;
    public boolean isDeBug;
    private long oldTime;
    private int delay = 1500;
    private boolean flag = false;
    public static long s_run_time = System.currentTimeMillis();

    private boolean mHandleNewIntent;                                                               // 获取素材数据是否成功标志


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sBoolKill = false;
        if (main != null && main != this) {
            exit(false);
        }
        main = this;


        init();
        openWelcomePage(flag);                                                                      // 进入welcome页面
    }


    // 执行需要初始化api
    private void init() {
        // 注册素材下载完成的广播
        //初始化统计
        initUI();                                                                                   // 初始化UI
    }

    //初始化程序主界面UI
    private void initUI() {
        mMainFrameLayout = new FrameLayout(this);
        LayoutParams mParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mMainFrameLayout.setLayoutParams(mParams);

        setContentView(mMainFrameLayout);

        // 底层背景
        mBgContainer = new FrameLayout(this);
        mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mMainFrameLayout.addView(mBgContainer, mParams);

        // 存放不同页面的父控件
        mContainer = new FrameLayout(this);
        mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mMainFrameLayout.addView(mContainer, mParams);

        mPopupPageContainer = new FrameLayout(this);
        mParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mMainFrameLayout.addView(mPopupPageContainer, mParams);
    }


    //直接跳回到主页
    public boolean backToHomePage() {
        if (mPopupPage != null) {
            popPopupPage();
            return true;
        }
        int page = PageStackManager.backToHomePageStack();
        if (page == -1) {
            return false;
        }


        restorePage(page);
        PageStackManager.clearStackInfo();
        return true;
    }

    //直接打开主界面
    public void openMainPage() {
//        MainPage mMainPage = (MainPage) setActivePage(PAGE_MAIN, true);
//        mMainPage.setEffectType(0);

    }


    /**
     * 开启后台更新网络上的资源函数
     */
    public void startBgUpdate() {
//        if (NetWorkUtils.isNetworkConnected(getApplicationContext()) && !mIsUpdateBG) {
//            mIsUpdateBG = true;
////            ResBundlesUpdate.getInstance().bgUpdate();
//            ResAdUpdate.getInstance().setDownloadListener(adDownloadListener);
//            ResAdUpdate.getInstance().bgUpdate();           // 下载广告的图片
//        }
    }


    public void openAboutPage() {
//        AboutPage aboutPage = (AboutPage) setActivePage(PAGE_ABOUT, true);
        startBgUpdate();
    }


    //跳转到帮助页
    public void openHelpPage(String key, int pos) {
//        List<String> expl = DataOperate.getBundleExpl(key);
//        if (expl == null || expl.isEmpty()) {                                                      // 没有说明图则直接跳转到合成页面
//            openMixPage(key);
//        } else {
//            //发送http统计
//            TongJi.addCount(DataOperate.getBundleTJ_code_enter(key));
//            HelpPage page = (HelpPage) setActivePage(PAGE_HELP, true);
//            page.setEffectType(key);
//            PageStack.setStackInfo(PAGE_HELP, new Object[]{key, pos});
//            startBgUpdate();
//        }
    }


    public void openCloudAlbum(String userId) {
//        //mLastOP = PAGE_CLOUD_ALBUM;
//        CloudAlbumPage page = (CloudAlbumPage) setActivePage(PAGE_CLOUD_ALBUM, true);
//        page.OpenAlbum(null, null);
//        Object[] objs = new Object[5];
//        objs[0] = userId;
//        PageStack.setStackInfo(PAGE_CLOUD_ALBUM, objs);
    }

    //跳转到选图页面
    public void openMixPage(String key) {
//        if (Configure.queryHelpFlag("intropage_mixpage_1.0")) {
//            openIntroPage(new int[]{R.drawable.tips2_i5}, new Runnable() {
//
//                @Override
//                public void run() {
//                    popPopupPage();
//                    Configure.clearHelpFlag("intropage_mixpage_1.0");
//                }
//            }, false);
//        }
//        TongJi.addCount(DataOperate.getBundleTJ_code_pick(key));
//        MixPage page = (MixPage) setActivePage(PAGE_MIX, true);
//        page.setEffectType(key, false);
//        PageStack.setStackInfo(PAGE_MIX, new Object[]{key});
//        startBgUpdate();
    }

    //跳转到编辑效果页面
    public void openEditPage(String key, Bitmap mixC) {
//        TongJi.addCount(DataOperate.getBundleTJ_code_effect(key));
//        if (DataOperate.shadeType.isEmpty()) {
//            DataOperate.initSahdeType();
//        }
//        EditPage3 page = (EditPage3) setActivePage(PAGE_EDIT, true);
//        page.setDatasInfo(key, mixC);
//        PageStack.setStackInfo(PAGE_EDIT, new Object[]{key});
//        startBgUpdate();
    }


    //跳转到保存页面
    public void openSavePage(Bitmap E) {
//        SavePage page = (SavePage) setActivePage(PAGE_SAVE, true);
//        page.setEffectType(E);
//        PageStack.setStackInfo(PAGE_SAVE, new Object[]{null});
//        startBgUpdate();
    }


//    public void openSharePage(String key, int pageType, int shareType, String filePath, String shareText, String shareURL, ShareHelper.ShareCallback shareCallback) {
//        if (pageType == ShareHelper.PAGE_TYPE_SAVE) {
//            TongJi.addCount(DataOperate.getBundleTJ_code_share(key));
//        }
//        if (pageType == ShareHelper.PAGE_TYPE_ABOUT || pageType == ShareHelper.PAGE_TYPE_UNLOCK || shareType == ShareType.WEIXIN || shareType == ShareType.WEIXIN_FRIEND) {
//            //不用界面
//            mShareHelper = new ShareHelper(this);
//            mShareHelper.setShareData(key, pageType, shareType, filePath, shareText, shareURL);
//            mShareHelper.setShareCallback(shareCallback);
//            mShareHelper.share();
//
//        } else {
////            if (pageType == ShareHelper.PAGE_TYPE_SAVE && (shareType == ShareType.QZONE || shareType == ShareType.SINA)) {
////            }
//            //保存页  分享到qq sina
//            SharePage page = (SharePage) setActivePage(PAGE_SHARE, true);
//            Object[] objs = new Object[3];
//            objs[0] = key;
//            objs[1] = shareType;
//            PageStack.setStackInfo(PAGE_SHARE, objs);
//            page.setShareData(key, pageType, shareType, filePath, shareText, shareURL);
//            page.setShareCallback(shareCallback);
//        }
//    }


//    private void openFirstPage() {
//        FirstPage firstPage = (FirstPage) setActivePage(PAGE_FIRSPAGE, false);
//        int[] res = null;
//        if (Configure.isFirstRun()) {
//            res = new int[]{
//                    R.drawable.lead1,
//                    R.drawable.lead2,
//                    R.drawable.lead3
//            };
//        } else {
//            res = new int[]{
//                    R.drawable.lead1,
//                    R.drawable.lead2,
//                    R.drawable.lead3
//            };
//        }
//        firstPage.setPageData(res, new Runnable() {
//            @Override
//            public void run() {
//                Configure.saveConfig(getApplicationContext());
//                openMainPage();
//            }
//        });
//
//    }

    //跳转到欢迎界面
    private void openWelcomePage(boolean isLoading) {
//        WelcomePage page = (WelcomePage) setActivePage(PAGE_WELCOME, false);
//        if (isLoading) {
//            ResAdUpdate.getInstance().setDownloadListener(adDownloadListener);
//            ResAdUpdate.getInstance().bgUpdate();                                                   // 下载广告的图片
//            saveADBundle();
//            Global.recycleBitmap();
//            page.setEffect();
//        } else {
//            DataOperate.initialMainBundle(getApplicationContext());                                 // 初始化本地主页数据
//            DataOperate.initSahdeType();                                                            // 初始化遮罩映射表
//            DataOperate.initMaterialBundle();                                                       // 初始化素材数据
//            DataOperate.initialADBundles(getApplicationContext());                                  // 初始化ad.json
//            Global.recycleBitmap();
//            startBgUpdate();
//        }
    }

//    private ADDownloadListener adDownloadListener = new ADDownloadListener() {
//
//        @Override
//        public void onDownloadComplete() {
//            mIsSaveAD = true;
//        }
//    };
//
//    //打开第一次安装了软件的介绍页
//    public void openIntroPage(int[] res, Runnable completeListener, boolean needButton) {
//        IntroPage page;
//        page = new IntroPage(this);
//
//        if (needButton == true) {
//            page.setMainIntorImage(res);
//        } else {
//            page.setIntorImage(res);
//        }
//        page.setCompleteListener(completeListener);
//        //弹出窗口
//        popupPage(page);
//    }


    //返回键，返回到上一个页面
    @Override
    public void onBackPressed() {
        boolean handled = false;
        if (mTopPage != null) {
            handled = mTopPage.onBack();//当前页相关操作是否完毕， 比如保存等
//            PLog.out("MainDebug", "handled:" + handled);
        }
        if (handled == false) {
            if (backToLastPage() == false)//返回到上一个页面
            {
                onBack();
            }
        }
    }


    //返回键响应
    private void onBack() {
        if (false == backToLastPage()) {
            confirmExit(this);
        }
    }

    private void confirmExit(Context context) {
//        if (mPressedExit == false) {
//            Toast.makeText(context, "再按一次返回键退出图片合成器", Toast.LENGTH_SHORT).show();
//            mPressedExit = true;
//            Handler handler = new Handler();
//            handler.postDelayed(mResetExitFlagRunnable, 3000);
//        } else {
//            if (!TextUtils.isEmpty(Constant.TEMP_PIC_NAME)) {// 删掉之前临时图片，产生新图片的名称进行保存
//                FileUtils.deleteFile(Constant.TEMP_PIC_DIR, Constant.TEMP_PIC_NAME);
//            }
//
//            //查看下载队列里面是否还有下载资源的任务，有的话，提醒用户是否退出程序
//            final ResBase[] download = ResBundlesUpdate.getInstance().getDownloadQueue();
//            if (download != null && download.length > 0) {
//                AlertDialog alert = new AlertDialog.Builder(this).create();
//                alert.setTitle("提示");
//                alert.setMessage("还有" + download.length + "个资源在下载，是否继续退出程序?");
//                alert.setButton(AlertDialog.BUTTON_POSITIVE, "是", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        for (int i = 0; i < download.length; i++) {
//                            ResBase res = download[i];
//                            if (res.status == ResBase.STATUS_LOADING) {
//                                String filePath = FileUtils.getSDPath() + Constant.Bundle_Path + res.zip;
//                                File f = new File(filePath);
//                                if (f.exists()) {
//                                    f.delete();
//                                }
//                            }
//                        }
//                        exit(true);
//                    }
//                });
//                alert.setButton(AlertDialog.BUTTON_NEGATIVE, "否", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                alert.show();
//            } else {
//                //退出程序
//                exit(true);
//            }
//        }
    }

    /**
     * 获取当前页
     *
     * @return
     */
    public int getCurrentPage() {
        return mCurrentPage;
    }

    /**
     * 获取最后一页
     *
     * @return
     */
    public int getLastPage() {
        return mLastPage;
    }


    public IPage setActivePage(int page, boolean pushToStack) {
        return setActivePage(page, pushToStack, null);
    }

    /**
     * 设置跳转的Actvity页面
     *
     * @param page        Activity页面，常量表示
     * @param pushToStack 是否添加到栈中
     * @param args
     * @return IPage
     */
    public IPage setActivePage(int page, boolean pushToStack, Object[] args) {
        mLastPage = mCurrentPage;
        if (page == -1 || page == mCurrentPage) {
            return mPage;
        }

        if (mPage != null) {
            mPage.onClose();
            mContainer.removeAllViews();
        }

        if (pushToStack == true && page != PAGE_WELCOME) {
//            PageStack.pushToPageStack(page);
        }

        mCurrentPage = page;            //将该页面换成当前页
        View view = null;

        mContainer.setFocusable(true);
        mContainer.setFocusableInTouchMode(true);
        mContainer.requestFocus();
        /*mContainer.setOnFocusChangeListener(new OnFocusChangeListener(){
            @Override
			public void onFocusChange(View v, boolean hasFocus){
				if(hasFocus){
					PLog.out("AC", "mContainer focus!");
				}else{
					PLog.out("AC", "mContainer unfocus!");
				}
			}
		});*/
        switch (page) {
//            case PAGE_MAIN:
//                view = new MainPage(this);      // 首页
//                break;
//            case PAGE_MIX:
//                view = new MixPage(this);       // 选图页
//                break;
//            case PAGE_EDIT:
//                view = new EditPage3(this);     // 编辑页
//                break;
//            case PAGE_SAVE:
//                view = new SavePage(this);      // 保存页
//                break;
//            case PAGE_SHARE:
//                view = new SharePage(this);     // 分享页
//                break;
//            case PAGE_HELP:
//                view = new HelpPage(this);      // 玩法帮助页
//                break;
//            case PAGE_BINDPOCO:
//                view = new SharePage(this);
//                break;
//            case PAGE_WELCOME:
//                view = new WelcomePage(this);
//                break;
//            case PAGE_ABOUT:
//                view = new AboutPage(this);     // 关于页
//                break;
//            case PAGE_CLOUD_ALBUM:
//                view = new CloudAlbumPage(this);
//                break;
//            case PAGE_FIRSPAGE:
//                view = new FirstPage(this);
//                break;
//            default:
//                break;
        }

        if (view != null) {
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mContainer.addView(view, params);
            mPage = (IPage) view;
            if (mPopupPage == null) {
                mTopPage = mPage;
//                PLog.out("MainDebug", "mPopupPage != null");
            }
            return mPage;
        }
//        PLog.out("MainDebug", "view == null");
        return mPage;
    }


    //恢复到上一个栈顶页
    protected void restorePage(int page) {
//        Object[] info = PageStack.getStackInfo(page);
//        IPage pg = null;
//        pg = setActivePage(page, true);//这里是要重新new一个页面的
//        pg.onRestore();
//        if (info != null) {
//            if (page == PAGE_MIX) {
//                String key = (String) info[0];
//                if (key != null) {
//                    MixPage mixPage = (MixPage) pg;
//                    mixPage.setEffectType(key, true);
//                }
//            } else if (page == PAGE_EDIT) {
//                EditPage3 editPage = (EditPage3) pg;
//                editPage.restoreInfo();
//            } else if (page == PAGE_SAVE) {
//                SavePage savePage = (SavePage) pg;
//                savePage.setEffectType();
//            } else if (page == PAGE_SHARE) {
//                String key = (String) info[0];
//                String id = (String) info[1];
//                String path = (String) info[2];
//                if (key != null && id != null && path != null) {
//                    PopUpPage sharePage = (PopUpPage) pg;
//                    sharePage.setEffectType(key, id, path);
//                }
//            } else if (page == PAGE_HELP) {
//                String key = (String) info[0];
//                if (key != null) {
//                    HelpPage helpPage = (HelpPage) pg;
//                    helpPage.setEffectType(key);
//                }
//            } else if (page == PAGE_CLOUD_ALBUM) {
//                String userid = (String) info[0];
//                if (userid != null) {
//                    CloudAlbumPage cp = (CloudAlbumPage) pg;
//                    cp.OpenAlbum(userid, null);
//                }
//            }
//        } else {
//            if (page == PAGE_MAIN) {
//                MainPage mainPage = (MainPage) pg;
//                Object[] helpInfo = PageStack.getStackInfo(PAGE_HELP);
//                int pos = 0;
//                if (helpInfo != null) {
//                    pos = (Integer) helpInfo[1];
//                }
//                if (bbv != null) {
//                    bbv.recycledAllBitmap();
//                    bbv = null;
//                }
//                Global.recycleBitmap();
//                mainPage.setEffectType(pos);
//            }
//        }
//
//        // 切换debug和正式的时候退出到主页不让后台下载资源
//        if (!Constant.isSwitch) {
//            startBgUpdate();
//        }
    }

    //返回到上一页
    protected boolean backToLastPage() {
//        if (mPopupPage != null) {//弹出页  不为空
//            popPopupPage();//弹出被覆盖的页面做为当前页
//            return true;
//        }
//        if (mCurrentPage == PAGE_MAIN) {
//            return false;
//        }
//        int page = PageStack.popFromPageStack();//弹出当前页，把上一页 作为最新当前页
//        PLog.out("MainDebug", "page:" + page);
//        PLog.out("MainDebug", "currentpage:" + mCurrentPage);
//        if (page == -1) {
//            return false;
//        }
//        PLog.out("MainDebug", "page1:" + page);
//        restorePage(page);////弹出当前页，把上一页 作为最新当前页 (并把之前的各种参数进行恢复)
        return true;
    }


    public void popPopupPage() {
        if (mPopupPageStack.contains(mPopupPage)) {//当前页是有   弹出页
            View view = (View) mPopupPage;//停止一切当前弹出页， 进行先关的生命周期，后移除弹出页
            mPopupPage.onPause();
            mPopupPage.onStop();
            mPopupPage.onClose();
            mPopupPageContainer.removeView(view);
            mPopupPageStack.remove(mPopupPage);
            if (mPopupPageStack.size() == 0) {
                mPopupPage = null;
                if (mTopPage != mPage) {
                    if (mPage != null) {
                        mPage.onStart();
                        mPage.onResume();
                    }
                }
                mTopPage = mPage;
                mPopupPageContainer.setVisibility(View.GONE);
            } else {//当前页是没有   弹出页     直接返回前一页
                mTopPage = mPopupPageStack.get(mPopupPageStack.size() - 1);
                mPopupPage = mTopPage;
                mPopupPage.onStart();
                mPopupPage.onResume();
            }
        }
    }

    public void popupPage(IPage page) {
        if (page != null && page != mPopupPage) {
            mPopupPage = page;
            mTopPage = mPopupPage;
            mPopupPageStack.add(page);
            View view = (View) page;
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            view.setClickable(true);
            mPopupPageContainer.addView(view, params);
            if (mPopupPageContainer.getVisibility() != View.VISIBLE) {
                mPopupPageContainer.setVisibility(View.VISIBLE);
            }
        }
    }

    // 销毁所有弹出页面
    public void closeAllPopupPage() {
        IPage page = null;
        if (mPopupPage != null) {
            mPopupPage.onPause();
            mPopupPage.onStop();
        }
        for (int i = 0; i < mPopupPageStack.size(); i++) {
            page = mPopupPageStack.get(i);
            page.onClose();
        }
        if (mPopupPageStack.size() > 0 && mTopPage != mPage) {
            if (mPage != null) {
                mPage.onStart();
                mPage.onResume();
            }
        }
        mPopupPageContainer.removeAllViews();
        mPopupPageStack.clear();
        mPopupPage = null;
        mTopPage = mPage;
        mPopupPageContainer.setVisibility(View.GONE);
    }

    // 销毁指定弹出页面
    public void closePopupPage(IPage page) {
        if (page != null && mPopupPageStack.contains(page)) {
            View view = (View) page;
            if (page == mPopupPage) {
                page.onPause();
                page.onStop();
            }
            page.onClose();
            mPopupPageContainer.removeView(view);
            mPopupPageStack.remove(page);
            if (mPopupPageStack.size() == 0) {
                mPopupPage = null;
                if (mTopPage != mPage) {
                    if (mPage != null) {
                        mPage.onStart();
                        mPage.onResume();
                    }
                }
                mTopPage = mPage;
                mPopupPageContainer.setVisibility(View.GONE);
            } else if (page == mPopupPage) {
                mTopPage = mPopupPageStack.get(mPopupPageStack.size() - 1);
                mPopupPage = mTopPage;
                mPopupPage.onStart();
                mPopupPage.onResume();
            }
        }
    }

    //检查本地本地缓存文件并删除
    public void deleteCacheFile() {
//        Context appContext = getApplicationContext();
//        if (appContext != null) {
//            String path = appContext.getFilesDir().getAbsolutePath();
//
//            File file = new File(path);
//            File[] files = file.listFiles();
//            if (files != null) {
//                int len = files.length;
//                for (int i = 0; i < len; i++) {
//                    files[i].delete();
//                }
//            }
//        }
//        File path = new File(Utils.getSdcardPath() + Constant.PATH_TEMP);
//        if (path.exists() == true) {
//            File[] files = path.listFiles();
//            if (files != null) {
//                int len = files.length;
//                for (int i = 0; i < len; i++) {
//                    files[i].delete();
//                }
//            }
//        }
//        path = new File(Utils.getSdcardPath() + Constant.PATH_CACHE);
//        if (path.exists() == true) {
//            File[] files = path.listFiles();
//            if (files != null) {
//                int len = files.length;
//                for (int i = 0; i < len; i++) {
//                    files[i].delete();
//                }
//            }
//        }
    }


    public void executeCommand(String command, ArrayList<String> args) {
//        PLog.out("executeCommand:" + command);
        if (command == null) {
            return;
        }
        if (command.equals("AdvBeauty")) {
            String channel = "";
            if (args.size() > 0) {
                String[] pair = args.get(0).split("=");
                if (pair.length == 2) {
                    if (pair[0].equals("channel_value")) {
                        channel = pair[1];
                    }
                }
            }
            if (channel.length() > 0) {
                /*ActivityMgr.finishAll();
                ActPage act = (ActPage)setActivePage(PAGE_BUSINESS);
				if(act != null)
				{
					act.gotoAct(channel);
				}*/
            }
        } else if (command.equals("inApp")) {
            if (args.size() > 0) {
                String gotoPage = "";
                String[] pair = args.get(0).split("=");
                if (pair.length == 2) {
                    if (pair[0].equals("goto_page")) {
                        gotoPage = pair[1];
                    }
                }
                if (gotoPage.length() > 0) {
                    /*ActivityMgr.finishAll();
                    if(gotoPage.equals("online_resources"))
					{
						setActivePage(PAGE_MATERIALMGR);
					}*/
                }
            }
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: ");

//        if (intent != null) {
//            if (mMdBean != null) {
//                mMdBean = null;
//            }
//
//            mMdBean = new MeterialBDBean();
//            if (intent.hasExtra("is_downloaded")) {
//                String isDownloadedStr = intent.getStringExtra("is_downloaded");
//                boolean isDownloaded = false;
//                if (!TextUtils.isEmpty(isDownloadedStr)) {
//                    isDownloaded = Boolean.parseBoolean(isDownloadedStr);
//                }
//                mMdBean.setIsdownload(isDownloaded);
//            }
//            if (intent.hasExtra("is_unlocked")) {
//                mMdBean.setIsUnlocked(Boolean.parseBoolean(intent.getStringExtra("is_unlocked")));
//            }
//            if (intent.hasExtra("id")) {
//                mMdBean.setId(intent.getStringExtra("id"));
//            }
//            if (intent.hasExtra("add_time")) {
//                mMdBean.setAddTime(intent.getStringExtra("add_time"));
//            }
//            if (intent.hasExtra("zip_path")) {
//                mMdBean.setZipPath(intent.getStringExtra("zip_path"));
//            }
//            if (intent.hasExtra("thumb_url")) {
//                mMdBean.setThumbUrl(intent.getStringExtra("thumb_url"));
//            }
//            if (intent.hasExtra("name")) {
//                mMdBean.setName(intent.getStringExtra("name"));
//            }
//            if (intent.hasExtra("material_type")) {
//                mMdBean.setMaterialType(intent.getStringExtra("material_type"));
//            }
//            if (mMdBean.getZipPath() != null && mMdBean.getThumbUrl() != null) {
//                mHandleNewIntent = true;
//            }
//        }
    }


//    private DownloadedMaterialReceiver mDownloadedMaterialReceiver;

    // 素材下载完，但是还没点击立即使用的广播
//    private void registerDownloadMaterialReceiver() {
//        mDownloadedMaterialReceiver = new DownloadedMaterialReceiver(new Handler());
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("cn.poco.materialcenter.ui.aty.MaterialDetailActivity.action_download_success");
//        intentFilter.setPriority(999);
//        registerReceiver(mDownloadedMaterialReceiver, intentFilter);
//    }


    // 监听器
//    public interface MainpageListener {
//        MainPage onSetMainpageListener();
//    }


    // mainpage页面注入内容，将内存传到广播中去
//    public void setMainPageObj(final MainPage mainPage) {
//        if (mDownloadedMaterialReceiver != null) {
//            mDownloadedMaterialReceiver.setMainPageListener(new MainpageListener() {
//                @Override
//                public MainPage onSetMainpageListener() {
//                    return mainPage;
//                }
//            });
//        }
//    }


    @Override
    protected void onResume() {
        if (mTopPage != null) {
            mTopPage.onResume();
        }
//        PLog.out("MainDebug", "onResume");
//        super.onResume();
//        StatService.onResume(main);
//        isDealMaterialData();
    }


    private void isDealMaterialData() {
        if (mHandleNewIntent) {                                                                     // 素材中心的操作，当有素材下载的时候才会触发
            mHandleNewIntent = false;
          /*  isNeedUnClocked();                                                                      // 是否需要解锁
            if (!mMdBean.getIsdownload()) {                                                         // 该玩法是否下载完成
                unDownload();                                                                       // 下载
            } else {
                downloaded();                                                                       // 未下载
            }*/
            downloaded();
        }
    }


    private void isNeedUnClocked() {
//        boolean isUnlocked = mMdBean.getIsUnlocked();
//        if (isUnlocked) {
//            String id = mMdBean.getId();
//            if (DataOperate.listBundles != null && !DataOperate.listBundles.isEmpty()) {
//                for (Map.Entry<String, Bundles> entry : DataOperate.listBundles.entrySet()) {
//                    Bundles bundles = entry.getValue();
//                    if (bundles != null) {
//                        String key = entry.getKey();
//                        if (id.equals(key)) {
//                            String iap = bundles.getIap();
//                            if (!"free".equals(iap)) {
//                                bundles.setIap("free");
//                                bundles.setSharePos(null);
//                                bundles.setShareStr(null);
//                                bundles.setShareUrl(null);
//                                final ResBundlesUpdate bundleUpdate = ResBundlesUpdate.getInstance();
//                                bundleUpdate.saveMainBundle();
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }


    // 该玩法已经下载
    private void downloaded() {
//        String id = mMdBean.getId();
//        if (DataOperate.listBundles != null && !DataOperate.listBundles.isEmpty()) {
//            for (Map.Entry<String, Bundles> entry : DataOperate.listBundles.entrySet()) {
//                String key = entry.getKey();
//                if (!TextUtils.isEmpty(key) && id.equals(key)) {
//                    Bundles bundles = entry.getValue();
//                    if (bundles != null) {
//                        String isMaterial = bundles.getIsMaterial();
//                        if (!TextUtils.isEmpty(isMaterial)) {
//                            if ("net".equals(isMaterial)) {
//                                if (DataOperate.ReadSubBundle(key, 1)) {
//                                    openHelpPage(key, 0);
//                                }
//                            } else if ("material".equals(isMaterial)) {
//                                if (DataOperate.ReadSubBundle(key, 2)) {
//                                    openHelpPage(key, 0);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }


    // 玩法还没下载
    private void unDownload() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final IconItemInfo iconItemInfo = materialOperation(mMdBean);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (iconItemInfo != null && iconItemInfo.key != null) {
//                            if (DataOperate.ReadSubBundle(iconItemInfo.key, 2)) {
//                                openHelpPage(iconItemInfo.key, 0);
//                            }
//                            Material.getInstance().clearInstace();
//                        }
//                    }
//                });
//            }
//        }).start();
    }


//    /**
//     * 素材数据的操作
//     */
//    private IconItemInfo materialOperation(MeterialBDBean bean) {
//
//        if (bean != null) {
//            Material instance = Material.getInstance();
//            IconItemInfo iconItemInfo = instance.materialOperation(bean, true);
//            if (iconItemInfo != null) {
//                return iconItemInfo;
//            }
//        }
//        return null;
//    }


    @Override
    protected void onPause() {
//        if (mTopPage != null) {
//            mTopPage.onPause();
//        }
//        PLog.out("MainDebug", "onPause");
//        super.onPause();
//        StatService.onPause(main);
    }

    @Override
    protected void onStart() {
        s_run_time = System.currentTimeMillis();

        if (mTopPage != null) {
            mTopPage.onStart();
        }
//        PLog.out("MainDebug", "onStart");
        super.onStart();

    }

    @Override
    protected void onStop() {

        if (mTopPage != null) {
            mTopPage.onStop();
        }
//        PLog.out("MainDebug", "onStop");
//        if (sBoolKill == false) {
//            Configure.saveConfig(this);
//        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mTopPage != null) {
            mTopPage.onDestroy();
        }
//        if (bbv != null) {
//            bbv.recycledAllBitmap();
//            bbv = null;
//        }
//
//        UpdateAPK.onDestroy();
//        //清除页面堆栈数据
//        PageStack.clearPageStack();
//        PageStack.clearStackInfo();
//        unregisterReceiver(mReceiver);
//        Log.d("MainDebug", "onDestroy");
//        super.onDestroy();
//
//        if (mImageContentObserver != null) {
//            getContentResolver().unregisterContentObserver(mImageContentObserver);
//            mImageContentObserver = null;
//        }
//        if (main == this) {
//            main = null;
//        }
//
//        if (Constant.isSwitch) {
//            FileUtils.deleteFile(FileUtils.getDefaultPath());
//        }
//
//        if (sBoolKill == true) {
//            android.os.Process.killProcess(android.os.Process.myPid());
//            PLog.out("killProcess");
//        }
//
//
//        if (mUnlockedBroadcastReceiver != null) {           // 注销解锁广播
//            unregisterReceiver(mUnlockedBroadcastReceiver);
//            mUnlockedBroadcastReceiver = null;
//        }
//
//        if (mDownloadedMaterialReceiver != null) {          // 注销素材下载广播
//            unregisterReceiver(mDownloadedMaterialReceiver);
//            mDownloadedMaterialReceiver = null;
//        }
//
//        if (mMdBean != null) {
//            mMdBean = null;
//        }
    }

    private Runnable mResetExitFlagRunnable = new Runnable() {

        @Override
        public void run() {
            mPressedExit = false;
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean handled = false;
        if (mTopPage != null) {
            handled = mTopPage.onActivityResult(requestCode, resultCode, data);
        }
        if (handled == false) {
            super.onActivityResult(requestCode, resultCode, data);
        }
//        if (mShareHelper != null) {
//            mShareHelper.onActivityResult(requestCode, resultCode, data);
//        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mTopPage != null) {
            if (true == mTopPage.onActivityKeyDown(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mTopPage != null) {
            if (true == mTopPage.onActivityKeyUp(keyCode, event)) {
                return true;
            }
            if (keyCode == 168 || keyCode == 169 || keyCode == 256 || keyCode == 261)//samsung galasy zoom
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    //退出app
    public void exit(boolean isKill) {
//        Configure.saveConfig(this);
//        sBoolKill = isKill;
//
//        //释放全局变量Bitmap
//        Global.recycleBitmap();
//        //保存最新json数据到文件
////        ResBundlesUpdate.getInstance().saveRes();                                                   // 注销保存load网络数据
//        //清除无用子功能项
//        ResBundlesUpdate.getInstance().clearInvalidRes();
        saveADBundle();
        //		if(mIsSaveAD){
        //			//更改adv文件夹名为ad
        //			File file = new File(FileUtils.getSDPath() + Constant.AD_Path);
        //			if(file.exists()){
        //				FileUtils.deleteFile(file.getPath());
        //			}
        //			FileUtils.renameToNewFile(FileUtils.getSDPath() + Constant.AD_Path1, FileUtils.getSDPath()+Constant.AD_Path);
        //			file = new File(FileUtils.getSDPath()+Constant.Ad_Bundles_Path);
        //			if(file.exists()){
        //				FileUtils.deleteFile(file.getPath());
        //			}
        //			FileUtils.renameToNewFile(FileUtils.getSDPath() + Constant.Ad_Bundles_Path1, FileUtils.getSDPath()+Constant.Ad_Bundles_Path);
        //			FileUtils.copySDFile2DataCustom(getApplicationContext(), FileUtils.getSDPath()+Constant.Ad_Bundles_Path, Constant.BundleDir, Constant.Ad_bundles1);
        //		}else{
        //			File f = new File(FileUtils.getSDPath() + Constant.AD_Path1);
        //			if(f.exists()){
        //				FileUtils.deleteFile(f.getPath());
        //			}
        //			f = new File(FileUtils.getSDPath() + Constant.Ad_Bundles_Path1);
        //			if(f.exists()){
        //				FileUtils.deleteFile(f.getPath());
        //			}
        //		}
//        deleteTempFile();
//
//
//        if (FileUtils.isExistFiles(FileUtils.getSDPath() + "Poload")) {
//            File file = new File(FileUtils.getSDPath() + "Poload");
//            FileUtils.deleteSubFile(file.getPath());
//        }

        main.finish();
    }

    public void saveADBundle() {
/*        if (isDeBug == true) {
            if (mIsSaveAD) {
                //更改adv文件夹名为ad
                File file = new File(FileUtils.getSDPath() + Constant.AD_Path);
                if (file.exists()) {
                    FileUtils.deleteFile(file.getPath());
                }
                FileUtils.renameToNewFile(FileUtils.getSDPath() + Constant.AD_Path1, FileUtils.getSDPath() + Constant.AD_Path);
                FileUtils.copySDFile2DataCustom(getApplicationContext(), FileUtils.getSDPath() + Constant.Ad_Bundles_Path1, Constant.BundleDir, Constant.Ad_bundles_SB);
                FileUtils.deleteFile(FileUtils.getSDPath() + Constant.Ad_Bundles_Path1);
            } else {
                File f = new File(FileUtils.getSDPath() + Constant.AD_Path1);
                if (f.exists()) {
                    FileUtils.deleteFile(f.getPath());
                }
                f = new File(FileUtils.getSDPath() + Constant.Ad_Bundles_Path1);
                if (f.exists()) {
                    FileUtils.deleteFile(f.getPath());
                }
            }

        } else {*/
        if (mIsSaveAD) {
//            // 将adv改名为ad
//            File file = new File(FileUtils.getSDPath() + Constant.AD_Path);
//            if (file.exists()) {
//                FileUtils.deleteFile(file.getPath());
//            }
//            FileUtils.renameToNewFile(FileUtils.getSDPath() + Constant.AD_Path1, FileUtils.getSDPath() + Constant.AD_Path);
//
//            // 将adv.json改为ad.json
//            file = new File(FileUtils.getSDPath() + Constant.Ad_Bundles_Path);
//            if (file.exists()) {
//                FileUtils.deleteFile(file.getPath());
//            }
//            FileUtils.renameToNewFile(FileUtils.getSDPath() + Constant.Ad_Bundles_Path1, FileUtils.getSDPath() + Constant.Ad_Bundles_Path);
//            FileUtils.copySDFile2DataCustom(getApplicationContext(), FileUtils.getSDPath() + Constant.Ad_Bundles_Path, Constant.BundleDir, Constant.Ad_bundles);
//        } else {
//            File f = new File(FileUtils.getSDPath() + Constant.AD_Path1);
//            if (f.exists()) {
//                FileUtils.deleteFile(f.getPath());
//            }
//
//            f = new File(FileUtils.getSDPath() + Constant.Ad_Bundles_Path1);         // adv.json
//            if (f.exists()) {
////                    FileUtils.deleteFile(f.getPath());
//                FileUtils.renameToNewFile(FileUtils.getSDPath() + Constant.Ad_Bundles_Path1, FileUtils.getSDPath() + Constant.Ad_Bundles_Path);
//            }
//        }
        }
    }

}
