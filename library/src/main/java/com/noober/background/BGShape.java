package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;

import org.xmlpull.v1.XmlPullParserException;

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

        Drawable normal = getNormalDrawable(context, attrs, comm);
        if (normal == null) {
            safeRecycle(comm);
            return false;
        }

        Drawable pressed = getPressedDrawable(context, attrs, comm);
        Drawable checkOrSelect = getCheckOrSelectedDrawable(context, attrs, comm);

        if (pressed == null && checkOrSelect == null) {
            view.setBackgroundDrawable(normal);
            safeRecycle(comm);
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
        safeRecycle(comm);
        return true;
    }


    //默认背景
    private Drawable getNormalDrawable(Context context, AttributeSet attrs, TypedArray other) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_shape);
        if (a.getIndexCount() == 0) {
            safeRecycle(a);
            return null;
        }

        boolean hasSolidColor = a.hasValue(R.styleable.mc_bg_shape_bgs_solid_color);
        boolean hasGradientColor = a.hasValue(R.styleable.mc_bg_shape_bgs_gradient_startColor) &&
                a.hasValue(R.styleable.mc_bg_shape_bgs_gradient_endColor);

        if (!hasSolidColor && !hasGradientColor) {
            safeRecycle(a);
            return null;
        }

        int strokeColor = a.getColor(R.styleable.mc_bg_shape_bgs_stroke_color, 0);

        if (hasSolidColor) {    //solidColor优先级高
            int solidColor = a.getColor(R.styleable.mc_bg_shape_bgs_solid_color, 0);
            safeRecycle(a);
            return newDrawable(solidColor, strokeColor, other);
        }

        int[] colors;
        int startColor = a.getColor(R.styleable.mc_bg_shape_bgs_gradient_startColor, Color.RED);
        int endColor = a.getColor(R.styleable.mc_bg_shape_bgs_gradient_endColor, Color.RED);
        if (a.hasValue(R.styleable.mc_bg_shape_bgs_gradient_centerColor)) {
            colors = new int[3];
            colors[0] = startColor;
            colors[1] = a.getColor(R.styleable.mc_bg_shape_bgs_gradient_centerColor, Color.RED);
            colors[2] = endColor;
        } else {
            colors = new int[2];
            colors[0] = startColor;
            colors[1] = endColor;
        }
        safeRecycle(a);
        return newDrawable(colors, strokeColor, other);
    }


    //checked或者selected背景
    private Drawable getCheckOrSelectedDrawable(Context context, AttributeSet attrs, TypedArray other) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_shape_selected_or_checked);
        if (a.getIndexCount() == 0) {
            safeRecycle(a);
            return null;
        }

        boolean hasSolidColor = a.hasValue(R.styleable.mc_bg_shape_selected_or_checked_bgs_solid_color_cs);
        boolean hasGradientColor = a.hasValue(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_startColor_cs) &&
                a.hasValue(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_endColor_cs);

        if (!hasSolidColor && !hasGradientColor) {
            safeRecycle(a);
            return null;
        }

        int strokeColor = a.getColor(R.styleable.mc_bg_shape_selected_or_checked_bgs_stroke_color_cs, 0);

        if (hasSolidColor) {    //solidColor优先级高
            int solidColor = a.getColor(R.styleable.mc_bg_shape_selected_or_checked_bgs_solid_color_cs, 0);
            safeRecycle(a);
            return newDrawable(solidColor, strokeColor, other);
        }

        int[] colors;
        int startColor = a.getColor(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_startColor_cs, Color.RED);
        int endColor = a.getColor(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_endColor_cs, Color.RED);
        if (a.hasValue(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_centerColor_cs)) {
            colors = new int[3];
            colors[0] = startColor;
            colors[1] = a.getColor(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_centerColor_cs, Color.RED);
            colors[2] = endColor;
        } else {
            colors = new int[2];
            colors[0] = startColor;
            colors[1] = endColor;
        }
        safeRecycle(a);
        return newDrawable(colors, strokeColor, other);
    }

    //pressed背景
    private Drawable getPressedDrawable(Context context, AttributeSet attrs, TypedArray other) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.mc_bg_shape_pressed);
        if (a.getIndexCount() == 0) {
            safeRecycle(a);
            return null;
        }

        boolean hasSolidColor = a.hasValue(R.styleable.mc_bg_shape_pressed_bgs_solid_color_pressed);
        boolean hasGradientColor = a.hasValue(R.styleable.mc_bg_shape_pressed_bgs_gradient_startColor_pressed) &&
                a.hasValue(R.styleable.mc_bg_shape_pressed_bgs_gradient_endColor_pressed);

        if (!hasSolidColor && !hasGradientColor) {
            safeRecycle(a);
            return null;
        }

        int strokeColor = a.getColor(R.styleable.mc_bg_shape_pressed_bgs_stroke_color_pressed, 0);

        if (hasSolidColor) {    //solidColor优先级高
            int solidColor = a.getColor(R.styleable.mc_bg_shape_pressed_bgs_solid_color_pressed, 0);
            safeRecycle(a);
            return newDrawable(solidColor, strokeColor, other);
        }

        int[] colors;
        int startColor = a.getColor(R.styleable.mc_bg_shape_pressed_bgs_gradient_startColor_pressed, Color.RED);
        int endColor = a.getColor(R.styleable.mc_bg_shape_pressed_bgs_gradient_endColor_pressed, Color.RED);
        if (a.hasValue(R.styleable.mc_bg_shape_pressed_bgs_gradient_centerColor_pressed)) {
            colors = new int[3];
            colors[0] = startColor;
            colors[1] = a.getColor(R.styleable.mc_bg_shape_pressed_bgs_gradient_centerColor_pressed, Color.RED);
            colors[2] = endColor;
        } else {
            colors = new int[2];
            colors[0] = startColor;
            colors[1] = endColor;
        }
        safeRecycle(a);
        return newDrawable(colors, strokeColor, other);
    }

    /**
     * @param solodColor  实心颜色
     * @param strokeColor 边线颜色
     * @param a           设置其他属性用的，对应R.styleable.mc_bg_shape_other集合
     * @return
     */
    private Drawable newDrawable(int solodColor, int strokeColor, TypedArray a) {
        try {
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(solodColor);
            drawable.setShape(a.getInt(R.styleable.mc_bg_shape_other_bgs_shape, 0));    //shape形状,默认矩形
            _setShapeDrawable(drawable, strokeColor, a);
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param gradientColor 渐变色
     * @param strokeColor   边线色
     * @param a             设置其他属性用的，对应R.styleable.mc_bg_shape_other集合
     */
    private Drawable newDrawable(int[] gradientColor, int strokeColor, TypedArray a) {
        try {
            GradientDrawable drawable = new GradientDrawable(getOrientation(a), gradientColor);
            int type = a.getInt(R.styleable.mc_bg_shape_other_bgs_gradient_type, 0);
            drawable.setGradientType(type); //渐变类型
            if (type == GradientDrawable.RADIAL_GRADIENT) {    //光晕渐变必须设半径，默认100像素
                drawable.setCornerRadius(a.getDimension(R.styleable.mc_bg_shape_other_bgs_gradient_gradient_radius, 100));
            }
            drawable.setShape(a.getInt(R.styleable.mc_bg_shape_other_bgs_shape, GradientDrawable.RECTANGLE));    //shape形状,默认矩形
            _setShapeDrawable(drawable, strokeColor, a);
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //处理圆角、边线
    private static void  _setShapeDrawable(GradientDrawable drawable, int strokeColor, TypedArray typedArray) throws Exception {
        float[] cornerRadius = new float[8];

        float sizeWidth = 0;
        float sizeHeight = 0;

        float strokeWidth = -1;
        float strokeDashWidth = 0;
        float strokeGap = 0;

        float centerX = 0.5f;
        float centerY = 0.5f;

        Rect padding = new Rect();

        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.mc_bg_shape_other_bgs_corners_radius) {         //优先级低于array
                drawable.setCornerRadius(typedArray.getDimension(attr, 0));
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_corners_radius_lb) {
                cornerRadius[6] = typedArray.getDimension(attr, 0);
                cornerRadius[7] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_corners_radius_rb) {
                cornerRadius[4] = typedArray.getDimension(attr, 0);
                cornerRadius[5] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_corners_radius_lt) {
                cornerRadius[0] = typedArray.getDimension(attr, 0);
                cornerRadius[1] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_corners_radius_rt) {
                cornerRadius[2] = typedArray.getDimension(attr, 0);
                cornerRadius[3] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_gradient_useLevel) {
                drawable.setUseLevel(typedArray.getBoolean(attr, false));
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_padding_left) {
                padding.left = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_padding_top) {
                padding.top = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_padding_right) {
                padding.right = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_padding_bottom) {
                padding.bottom = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_size_width) {
                sizeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_size_height) {
                sizeHeight = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_stroke_width) {
                strokeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_stroke_dashWidth) {
                strokeDashWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_stroke_dashGap) {
                strokeGap = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_gradient_centerX) {
                centerX = typedArray.getFloat(attr, -1);
            } else if (attr == R.styleable.mc_bg_shape_other_bgs_gradient_centerY) {
                centerY = typedArray.getFloat(attr, -1);
            }
        }

        //矩形的圆角，挨个设置的，优先级高于统一角度
        if (hasSetRadius(cornerRadius)) {
            drawable.setCornerRadii(cornerRadius);
        }
        //size宽高
        if (typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_size_width) &&
                typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_size_height)) {
            drawable.setSize((int) sizeWidth, (int) sizeHeight);
        }
        //设置stroke边线
        if (typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_stroke_width) && strokeColor != 0) {
            drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
        }

        //渐变中心点坐标相对位置，如0.5f 0.5f
        if (typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_gradient_centerX) &&
                typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_gradient_centerY)) {
            drawable.setGradientCenter(centerX, centerY);
        }

        //pading暂时不设置
        if (typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_padding_left) &&
                typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_padding_top) &&
                typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_padding_right) &&
                typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_padding_bottom)) {
            /*Field paddingField = drawable.getClass().getField("mPadding");
            paddingField.setAccessible(true);
            paddingField.set(drawable, padding);*/
        }
    }

    /**
     * 线性渐变的方向,不会返回空
     * 1.只和线性渐变有关
     * 2.默认从上到下
     * 3.根据角度计算方向
     */
    private static GradientDrawable.Orientation getOrientation(TypedArray typedArray) throws Exception {
        GradientDrawable.Orientation mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;

        if (typedArray.hasValue(R.styleable.mc_bg_shape_other_bgs_gradient_angle)) {
            int gradientAngle = typedArray.getInteger(R.styleable.mc_bg_shape_other_bgs_gradient_angle, 0);
            gradientAngle %= 360;
            if (gradientAngle % 45 != 0) {
                throw new XmlPullParserException(typedArray.getPositionDescription()
                        + "<gradient> tag requires 'angle' attribute to "
                        + "be a multiple of 45");
            }
            switch (gradientAngle) {
                case 0:
                    mOrientation = GradientDrawable.Orientation.LEFT_RIGHT;
                    break;
                case 45:
                    mOrientation = GradientDrawable.Orientation.BL_TR;
                    break;
                case 90:
                    mOrientation = GradientDrawable.Orientation.BOTTOM_TOP;
                    break;
                case 135:
                    mOrientation = GradientDrawable.Orientation.BR_TL;
                    break;
                case 180:
                    mOrientation = GradientDrawable.Orientation.RIGHT_LEFT;
                    break;
                case 225:
                    mOrientation = GradientDrawable.Orientation.TR_BL;
                    break;
                case 270:
                    mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;
                    break;
                case 315:
                    mOrientation = GradientDrawable.Orientation.TL_BR;
                    break;
            }
        }

        return mOrientation;
    }

    //判断是否有矩形圆角的单独设置左上、右下等等
    private static boolean hasSetRadius(float[] radius) {
        boolean hasSet = false;
        for (float f : radius) {
            if (f != 0.0f) {
                hasSet = true;
                break;
            }
        }
        return hasSet;
    }

}
