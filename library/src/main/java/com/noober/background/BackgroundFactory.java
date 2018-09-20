package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;


public class BackgroundFactory implements LayoutInflater.Factory2 {


    private static final ArrayList<IBGProcesser> PROCESSERS = new ArrayList<>(8);

    static {
        PROCESSERS.add(new TXColorProcesser());
        PROCESSERS.add(new BGProcesser());
    }

    //AppCompatActivity用的来创建view
    private LayoutInflater.Factory mViewCreateFactory;
    //用于创建view
    private ViewCreater mViewCreater;

    public BackgroundFactory(LayoutInflater inflater) {
        mViewCreater = new ViewCreater(inflater);
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
            view = mViewCreater.createView(parent, name, attrs);
        }

        if (view == null) {
            return null;
        }

        for (int i = 0, size = PROCESSERS.size(); i < size; i++) {
            PROCESSERS.get(i).process(view, context, attrs);
        }

        return view;
    }




    public static abstract class IBGProcesser {
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

        public void safeRecycles(TypedArray... a) {
            if (a == null || a.length == 0) {
                return;
            }
            for (int i = 0, len = a.length; i < len; i++) {
                safeRecycle(a[i]);
            }
        }
    }


}
