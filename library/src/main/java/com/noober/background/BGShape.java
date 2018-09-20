package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;


/**
 * Shape类型背景，同时处理了selector的情况
 *
 * @Author hanbo
 * @Since 2018/9/14
 */
class BGShape extends BackgroundFactory.IBGProcesser {


    @Override
    public boolean process(View view, Context context, AttributeSet attrs) {
        TypedArray comm = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_shape_other);  //配置shape除颜色以外其他属性的如形状、size、padding等等

        //默认背景
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_shape);
        if (a.getIndexCount() == 0) {
            safeRecycles(a, comm);
            return false;
        }

        GDBuilder cg = null;
        if (comm.getIndexCount() != 0) {
            cg = GDBuilder.cloneOrNew(null);
            for (int i = 0; i < comm.getIndexCount(); i++) {
                int attr = comm.getIndex(i);
                GDBuilder.Action action = AttrHelper.COMM.get(attr);
                try {
                    action.process(comm, attr, cg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        Drawable normal = getDrawable(AttrHelper.NORMAL, a, GDBuilder.cloneOrNew(cg));
        if (normal == null) {
            safeRecycles(a, comm);
            return false;
        }

        TypedArray pressedTa = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_shape_pressed);
        Drawable pressed = getDrawable(AttrHelper.PRESSED, pressedTa, GDBuilder.cloneOrNew(cg));
        TypedArray cta = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_shape_selected_or_checked);
        Drawable checkOrSelect = getDrawable(AttrHelper.CHECK_SELECT, cta, GDBuilder.cloneOrNew(cg));

        if (pressed == null && checkOrSelect == null) {
            view.setBackgroundDrawable(normal);
            safeRecycles(a, comm, pressedTa, cta);
            return true;
        }

        StateListDrawable stateListDrawable = new StateListDrawable();
        if (pressed != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressed);
        }

        if (checkOrSelect != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_selected}, checkOrSelect);
        }

        stateListDrawable.addState(new int[0], normal); //默认背景放最后一位，不然无效果

        view.setBackgroundDrawable(stateListDrawable);
        safeRecycles(a, comm, pressedTa, cta);
        return true;
    }


    private GradientDrawable getDrawable(SparseArray<GDBuilder.Action> ss, TypedArray main, GDBuilder gb) {
        if (main.getIndexCount() == 0) {
            return null;
        }

        for (int i = 0; i < main.getIndexCount(); i++) {
            int attr = main.getIndex(i);
            GDBuilder.Action action = ss.get(attr);
            if (action != null) {
                try {
                    action.process(main, attr, gb);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return gb.build();
    }


}
