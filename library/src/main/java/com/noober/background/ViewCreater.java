package com.noober.background;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 创建View
 * @Author hanbo
 * @Since 2018/9/20
 */
public class ViewCreater {

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };


    private LayoutInflater mInflater;

    public ViewCreater(LayoutInflater inflater) {
        mInflater = inflater;
    }


    public View createView(View parent, String name, AttributeSet attrs) {
        return createView(name, attrs);
    }

    public View createView(String name, AttributeSet attrs) {
        for (String prefix : sClassPrefixList) {
            try {
                View view = mInflater.createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
