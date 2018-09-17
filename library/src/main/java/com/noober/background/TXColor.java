package com.noober.background;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * @Author hanbo
 * @Since 2018/9/17
 */
class TXColor implements BackgroundFactory.IBGProcesser {


    private static final SparseArray<int[]> STATE_ARRAY = new SparseArray(8);

    {
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color, new int[0]);
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_pressed, new int[]{com.android.internal.R.attr.state_pressed});
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_checked, new int[]{com.android.internal.R.attr.state_checked});
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_selected, new int[]{com.android.internal.R.attr.state_selected});
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_focused, new int[]{com.android.internal.R.attr.state_focused});

    }

    @Override
    public boolean process(View view, Context context, AttributeSet attrs) {
        return _process(view, context, attrs);
    }

    private boolean _process(View view, Context context, AttributeSet attrs) {
        if (!(view instanceof TextView)) {
            return false;
        }

        TypedArray textColor = context.obtainStyledAttributes(attrs, R.styleable.mc_text_sl_color);
        try {
            int count;
            if ((count = textColor.getIndexCount()) < 2
                    || !textColor.hasValue(R.styleable.mc_text_sl_color_tx_color)) {  //这是给颜色selecotr用的，假如小于两个，或者没有默认色，则够不成selector
                return false;
            }

            int[][] stateSpecList = new int[count][1];
            int[] colorList = new int[count];


            for (int i = 0; i < count; i++) {
                int attr = textColor.getIndex(i);
                int color = textColor.getColor(attr, Color.RED); //全是颜色，所以就不做判断了。出异常的话显示大红色（基本不会出）

                stateSpecList[i] = STATE_ARRAY.get(attr);
                colorList[i] = color;
            }

            ColorStateList colorStateList = new ColorStateList(stateSpecList, colorList);
            ((TextView) view).setTextColor(colorStateList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                textColor.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
