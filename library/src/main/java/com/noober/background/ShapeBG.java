package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import org.xmlpull.v1.XmlPullParserException;

import java.lang.reflect.Field;

import static android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT;

/**
 * @Author hanbo
 * @Since 2018/9/14
 */
public class ShapeBG implements BackgroundFactory.IBGProcesser {


    @Override
    public boolean process(View view, Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.background);
        try {
            if (a.getIndexCount() == 0) {
                return false;
            }
            boolean hasSolidColor = a.hasValue(R.styleable.background_bg_solid_color);
            int[] gradientColor = getGradientColor(a);

            if (!hasSolidColor && gradientColor == null) {
                return false;
            }

            GradientDrawable drawable;
            if (hasSolidColor) {
                drawable = new GradientDrawable();
                drawable.setColor(a.getColor(R.styleable.background_bg_solid_color, Color.RED));
            } else {
                drawable = new GradientDrawable(getOrientation(a), gradientColor);
                int type = a.getInt(R.styleable.background_bg_gradient_type, 0);
                drawable.setGradientType(type); //渐变类型
                if (type == GradientDrawable.RADIAL_GRADIENT) {    //光晕渐变必须设半径，默认100像素
                    drawable.setCornerRadius(a.getDimension(R.styleable.background_bg_gradient_gradient_radius, 100));
                }
            }
            drawable.setShape(a.getInt(R.styleable.background_bg_shape, 0));    //shape形状,默认矩形


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                a.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    //处理圆角、边线
    private static GradientDrawable _getDrawable(GradientDrawable drawable, boolean isGrad, TypedArray typedArray) throws Exception {

        float[] cornerRadius = new float[8];

        float sizeWidth = 0;
        float sizeHeight = 0;

        float strokeWidth = -1;
        float strokeDashWidth = 0;
        int strokeColor = 1;
        float strokeGap = 0;

        float centerX = 0.5f;
        float centerY = 0.5f;

        Rect padding = new Rect();

        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.background_bg_corners_radius) {         //优先级低于array
                drawable.setCornerRadius(typedArray.getDimension(attr, 0));
            } else if (attr == R.styleable.background_bg_corners_bl_radius) {
                cornerRadius[6] = typedArray.getDimension(attr, 0);
                cornerRadius[7] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_corners_br_radius) {
                cornerRadius[4] = typedArray.getDimension(attr, 0);
                cornerRadius[5] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_corners_tl_radius) {
                cornerRadius[0] = typedArray.getDimension(attr, 0);
                cornerRadius[1] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_corners_tr_radius) {
                cornerRadius[2] = typedArray.getDimension(attr, 0);
                cornerRadius[3] = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_gradient_useLevel) {
                drawable.setUseLevel(typedArray.getBoolean(attr, false));
            } else if (attr == R.styleable.background_bg_padding_left) {
                padding.left = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_padding_top) {
                padding.top = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_padding_right) {
                padding.right = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_padding_bottom) {
                padding.bottom = (int) typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_size_width) {
                sizeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_size_height) {
                sizeHeight = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_stroke_width) {
                strokeWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_stroke_color) {
                strokeColor = typedArray.getColor(attr, 0);
            } else if (attr == R.styleable.background_bg_stroke_dashWidth) {
                strokeDashWidth = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_stroke_dashGap) {
                strokeGap = typedArray.getDimension(attr, 0);
            } else if (attr == R.styleable.background_bg_gradient_centerX) {
                centerX = typedArray.getFloat(attr, -1);
            } else if (attr == R.styleable.background_bg_gradient_centerY) {
                centerY = typedArray.getFloat(attr, -1);
            }
        }

        //矩形的圆角，挨个设置的，优先级高于统一角度
        if (hasSetRadius(cornerRadius)) {
            drawable.setCornerRadii(cornerRadius);
        }
        //size宽高
        if (typedArray.hasValue(R.styleable.background_size_width) &&
                typedArray.hasValue(R.styleable.background_size_height)) {
            drawable.setSize((int) sizeWidth, (int) sizeHeight);
        }
        //设置stroke边线
        if (typedArray.hasValue(R.styleable.background_bg_stroke_width) &&
                typedArray.hasValue(R.styleable.background_bg_stroke_color)) {
            drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
        }

        //渐变中心点坐标相对位置，如0.5f 0.5f
        if (typedArray.hasValue(R.styleable.background_bg_gradient_centerX) &&
                typedArray.hasValue(R.styleable.background_bg_gradient_centerY)) {
            drawable.setGradientCenter(centerX, centerY);
        }

        //pading暂时不设置
        if (typedArray.hasValue(R.styleable.background_bg_padding_left) &&
                typedArray.hasValue(R.styleable.background_bg_padding_top) &&
                typedArray.hasValue(R.styleable.background_bg_padding_right) &&
                typedArray.hasValue(R.styleable.background_bg_padding_bottom)) {
            /*Field paddingField = drawable.getClass().getField("mPadding");
            paddingField.setAccessible(true);
            paddingField.set(drawable, padding);*/
        }
        return drawable;
    }

    //线性渐变的方向
    private static GradientDrawable.Orientation getOrientation(TypedArray typedArray) throws Exception {

        GradientDrawable.Orientation mOrientation = GradientDrawable.Orientation.TOP_BOTTOM;

        if (typedArray.hasValue(R.styleable.background_bg_gradient_angle)) {
            int gradientAngle = typedArray.getInteger(R.styleable.background_bg_gradient_angle, 0);
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

    //获取渐变色
    private static int[] getGradientColor(TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.background_bg_gradient_startColor) &&
                typedArray.hasValue(R.styleable.background_bg_gradient_endColor)) {
            int[] colors;
            if (typedArray.hasValue(R.styleable.background_bg_gradient_centerColor)) {
                colors = new int[3];
                colors[0] = typedArray.getColor(R.styleable.background_bg_gradient_startColor, Color.RED);
                colors[1] = typedArray.getColor(R.styleable.background_bg_gradient_centerColor, Color.RED);
                colors[2] = typedArray.getColor(R.styleable.background_bg_gradient_endColor, Color.RED);
            } else {
                colors = new int[2];
                colors[0] = typedArray.getColor(R.styleable.background_bg_gradient_startColor, Color.RED);
                colors[1] = typedArray.getColor(R.styleable.background_bg_gradient_endColor, Color.RED);
            }
            return colors;
        }
        return null;
    }


    //判断是否有矩形圆角的单独设置
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


    static class GDBuilder {
        float[] cornerRadius = new float[8];

        float sizeWidth = 0;
        float sizeHeight = 0;

        float strokeWidth = -1;
        float strokeDashWidth = 0;
        int strokeColor = 1;
        float strokeGap = 0;

        float centerX = 0;
        float centerY = 0;

        int startColor = 0;
        int centerColor = 0;
        int endColor = 0;

        int gradientType = LINEAR_GRADIENT;
        int gradientAngle = 0;
        Rect padding = new Rect();

        int mShape;
        int mSolodColor;

        public void setShape(@GradientDrawable.Shape int shape) {
            mShape = shape;
        }

        public void setSolodColor(int solodColor) {
            mSolodColor = solodColor;
            startColor = 0;
            centerColor = 0;
            endColor = 0;
        }
    }

}
