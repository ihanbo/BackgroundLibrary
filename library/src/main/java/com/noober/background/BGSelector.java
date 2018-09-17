package com.noober.background;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 *
 * @Author hanbo
 * @Since 2018/9/17
 */
public class BGSelector implements BackgroundFactory.IBGProcesser {


    private static final SparseArray<int[]> STATE_ARRAY = new SparseArray(8);

    {
        STATE_ARRAY.put(R.styleable.mc_bg_sl_bg_normal, new int[0]);
        STATE_ARRAY.put(R.styleable.mc_bg_sl_bg_pressed, new int[]{com.android.internal.R.attr.state_pressed});
        STATE_ARRAY.put(R.styleable.mc_bg_sl_bg_checked, new int[]{com.android.internal.R.attr.state_checked});
        STATE_ARRAY.put(R.styleable.mc_bg_sl_bg_selected, new int[]{com.android.internal.R.attr.state_selected});
    }

    @Override
    public boolean process(View view, Context context, AttributeSet attrs) {
        return _process(view, context, attrs);
    }

    private boolean _process(View view, Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_sl);
        try {
            int count;
            if ((count = a.getIndexCount()) < 2
                    || !a.hasValue(R.styleable.mc_bg_sl_bg_normal)) {  //这是给背景selecotr用的，假如小于两个，或者没有默认背景，则够不成selector
                return false;
            }

            StateListDrawable bg = new StateListDrawable();
            for (int i = 0; i < count; i++) {
                int attr = a.getIndex(i);
                Drawable drawable = a.getDrawable(attr);
                bg.addState(STATE_ARRAY.get(attr), drawable);
            }
            view.setBackgroundDrawable(bg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                a.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
