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
 * 文本颜色
 *
 * @Author hanbo
 * @Since 2018/9/17
 */
class TextViewColorProcesser extends BackgroundFactory.IBGProcesser {


    public static final SparseArray<int[]> STATE_ARRAY = new SparseArray(8);

    static {
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color, new int[0]);
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_pressed, new int[]{android.R.attr.state_pressed});
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_checked, new int[]{android.R.attr.state_checked});
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_selected, new int[]{android.R.attr.state_selected});
        STATE_ARRAY.put(R.styleable.mc_text_sl_color_tx_color_focused, new int[]{android.R.attr.state_focused});
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
            int indexCount;
            if ((indexCount = textColor.getIndexCount()) < 2
                    || !textColor.hasValue(R.styleable.mc_text_sl_color_tx_color)) {  //这是给颜色selecotr用的，假如小于两个，或者没有默认色，则够不成selector
                return false;
            }

            int[][] stateSpecList = new int[indexCount][1];
            int[] colorList = new int[indexCount];

            //默认色要放最后一位，不然selector无效果
            int defaultColor = textColor.getColor(R.styleable.mc_text_sl_color_tx_color, Color.RED);
            stateSpecList[indexCount - 1] = new int[0];
            colorList[indexCount - 1] = defaultColor;

            int jumpDefaultColor = 0;
            for (int i = 0; i < indexCount; i++) {
                int attr = textColor.getIndex(i);
                if (attr == R.styleable.mc_text_sl_color_tx_color) {    //跳过默认色
                    jumpDefaultColor++;
                    continue;
                }
                int color = textColor.getColor(attr, Color.RED);        //全是颜色，所以就不做判断了。出异常的话显示大红色（基本不会出）
                stateSpecList[i - jumpDefaultColor] = STATE_ARRAY.get(attr);
                colorList[i - jumpDefaultColor] = color;
            }

            ColorStateList colorStateList = new ColorStateList(stateSpecList, colorList);
            ((TextView) view).setTextColor(colorStateList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            safeRecycle(textColor);
        }
    }

}

