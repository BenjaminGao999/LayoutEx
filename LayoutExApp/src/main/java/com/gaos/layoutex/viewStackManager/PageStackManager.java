package com.gaos.layoutex.viewStackManager;

/**
 * Author:　Created by benjamin
 * DATE :  2017/4/18 16:19
 * versionCode:　1.0.0
 * 保存view返回栈信息和view页面的各种参数
 */

import java.util.ArrayList;
import java.util.HashMap;

public class PageStackManager {
    protected static ArrayList<Integer> mPageStack = new ArrayList<Integer>();
    protected static HashMap<Integer, Object[]> mMapStackInfo = new HashMap<Integer, Object[]>();

    /**
     * 取出栈顶数据，并删除栈顶
     *
     * @return
     */
    public static int popStackTopPage() {
        int len = mPageStack.size();
        if (len >= 1) {
            int page = mPageStack.get(len - 1);
            mPageStack.remove(len - 1);
            return page;
        }
        return -1;
    }

    /**
     * 将相对于当前页的前一页取出来，没有删除栈顶，只是取
     *
     * @return
     */
    public static int peekLastPage() {
        int len = mPageStack.size();
        if (len >= 2) {
            return mPageStack.get(len - 2);
        }
        return -1;
    }

    /**
     * 清空所有页面堆栈数据
     */
    public static void clearPageStack() {
        mPageStack.clear();
    }

    /**
     * 将倒数第二个栈顶数据取出来，并且将栈顶数据删除
     *
     * @return
     */
    public static int popFromPageStack() {
        int len = mPageStack.size();
        if (len < 2) {
            return -1;
        }
        mPageStack.remove(len - 1);
        len = mPageStack.size();
        int page = mPageStack.get(len - 1);
        return page;
    }

    /**
     * 将最栈底的页面显示出来，这里相当于直接回到主页面
     *
     * @return
     */
    public static int backToHomePageStack() {
        int len = mPageStack.size();
        while (len > 1) {
            mPageStack.remove(len - 1);
            len = mPageStack.size();
        }
        int page = mPageStack.get(len - 1);
        mPageStack.remove(len - 1);
        return page;
    }

    /**
     * 将页面压入堆栈顶
     *
     * @param page
     */
    public static void pushToPageStack(int page) {
        if (mPageStack.contains(page) == true) {
            mPageStack.remove(mPageStack.indexOf(page));
        }
        mPageStack.add(page);
    }

    public static void clearStackInfo() {
        mMapStackInfo.clear();
    }

    public static Object[] getStackInfo(int page) {
        return mMapStackInfo.get(page);
    }

    public static void setStackInfo(int page, Object[] infos) {
        mMapStackInfo.put(page, infos);
    }


}

