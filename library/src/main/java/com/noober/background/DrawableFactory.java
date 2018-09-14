package com.noober.background;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.noober.background.utils.TypeValueHelper;

/**
 * @Author hanbo
 * @Since 2018/9/14
 */
public class DrawableFactory {
    private static final int CKECKED = 1;
    private static final int PRESSED = 2;
    private static final int SELECTED = 4;

    public static Drawable create(TypedArray typedArray) {
        Drawable d1 = BGD.getDrawable1(typedArray);
        int sltype = typedArray.getInt(R.styleable.background_bg_sl_type, -1);
        if (sltype == -1) {
            return d1;
        }

        GradientDrawable d2 = BGD.getDrawable2(typedArray);
        return getStateListDrawable(sltype, d1, d2);
    }

    private static Drawable getStateListDrawable(int sltype, Drawable d1, GradientDrawable d2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        if ((sltype & CKECKED) !=0) {

        }

        if ((sltype & PRESSED) !=0) {

        }

        if ((sltype & SELECTED) !=0) {

        }
        return stateListDrawable;
    }

    private StateListDrawable getStateListDrawable(GradientDrawable drawable, GradientDrawable pressDrawable, TypedArray typedArray) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = TypeValueHelper.sAppearancePressValues.get(typedArray.getIndex(i), -1);
            if (attr == -1) {
                continue;
            }
            int typeIndex = typedArray.getIndex(i);

            if (attr == R.styleable.bg_selector_sl_pressed) {
                int color = typedArray.getColor(typeIndex, 0);
                pressDrawable.setColor(color);
                stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);
            } else if (attr == R.styleable.bg_selector_sl_unpressed) {
                int color = typedArray.getColor(typeIndex, 0);
                drawable.setColor(color);
                stateListDrawable.addState(new int[]{-android.R.attr.state_pressed}, drawable);
            }
        }
        return stateListDrawable;
    }
}
