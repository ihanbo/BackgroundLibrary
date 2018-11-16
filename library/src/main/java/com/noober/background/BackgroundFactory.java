package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;


public class BackgroundFactory implements PPInject.Factory3 {


    private static final ArrayList<IBGProcesser> PROCESSERS = new ArrayList<>(8);

    static {
        PROCESSERS.add(new TextViewColorProcesser());
        PROCESSERS.add(new BackgroundProcesser());
    }


    @Override
    public void onDecorateView(View createdView, String name, Context context, AttributeSet attrs, View parent) {
        if (createdView == null) {
            return;
        }

        for (int i = 0, size = PROCESSERS.size(); i < size; i++) {
            PROCESSERS.get(i).process(createdView, context, attrs);
        }
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
