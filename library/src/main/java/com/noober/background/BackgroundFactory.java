package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;


public class BackgroundFactory implements LayoutInflater.Factory2 {


    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };

    private static final ArrayList<IBGProcesser> PROCESSERS = new ArrayList<>(8);

    static {
        PROCESSERS.add(new TXColorProcesser());
        PROCESSERS.add(new BGProcesser());
    }

    //AppCompatActivity用的来创建view
    private LayoutInflater.Factory mViewCreateFactory;
    //用于创建view
    private LayoutInflater mInflater;

    public BackgroundFactory(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public void setInterceptFactory(LayoutInflater.Factory2 factory) {
        mViewCreateFactory = factory;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        if (mViewCreateFactory != null) {
            view = mViewCreateFactory.onCreateView(name, context, attrs);
        }

        if (view == null) {
            view = createView(mInflater, name, attrs);
        }

        if (view == null) {
            return null;
        }

        for (int i = 0, size = PROCESSERS.size(); i < size; i++) {
            PROCESSERS.get(i).process(view, context, attrs);
        }

        return view;
    }


    private View createView(LayoutInflater inflater, String name, AttributeSet attrs) {
        for (String prefix : sClassPrefixList) {
            try {
                View view = inflater.createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }
        return null;
    }


    public static abstract class  IBGProcesser {
        abstract boolean process(View view, Context context, AttributeSet attrs);
        public void safeRecycle(TypedArray a) {
            if (a == null) {
                return;
            }
            try {
                a.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
