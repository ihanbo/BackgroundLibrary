package com.noober.background;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
            stateListDrawable.addState(new int[]{android.R.attr.state_drag_can_accept}, d2);
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


    public StateListDrawable cc() {
        StateListDrawable sd = new StateListDrawable();
        sd.addState(new int[]{com.android.internal.R.attr.state_pressed}, new ColorDrawable(Color.BLUE));
        sd.addState(new int[]{com.android.internal.R.attr.state_focused}, new ColorDrawable(Color.RED));
        sd.addState(new int[0], new ColorDrawable(Color.YELLOW));
        return sd;
    }
    public ColorStateList bb() {
        int[][] stateSpecList = new int[2][2];
        stateSpecList[0][0] = com.android.internal.R.attr.state_pressed;
        stateSpecList[0][1] = com.android.internal.R.attr.state_selected;
        stateSpecList[1] = new int[0];
        int[] color = new int[2];
        color[0] = Color.GREEN;
        color[1] = Color.RED;
        ColorStateList colorStateList2 = new ColorStateList(stateSpecList, color);
        return colorStateList2;
    }


}
