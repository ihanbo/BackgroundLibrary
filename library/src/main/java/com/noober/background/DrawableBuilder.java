package com.noober.background;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * 同于构建ShapeDrawable
 *
 * @Author hanbo
 * @Since 2018/9/19
 */
class DrawableBuilder implements Cloneable {


    private static final int STROKE_WIDTH_DEFAULT = 3;
    private static final int RADIAL_RADIUS_DEFAULT = 50;


    static final Action SOLID_COLOR = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int color = a.getColor(attr, 0);
            if (color != 0) {
                builder.setSolidColor(color);
            }
        }
    };

    static final Action GRADIENT_START_COLOR = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int color = a.getColor(attr, 0);
            if (color != 0) {
                builder.setGradientStartColor(color);
            }
        }
    };

    static final Action GRADIENT_CENTER_COLOR = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int color = a.getColor(attr, 0);
            if (color != 0) {
                builder.setGradientCenterColor(color);
            }
        }
    };

    static final Action GRADIENT_END_COLOR = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int color = a.getColor(attr, 0);
            if (color != 0) {
                builder.setGradientEndColor(color);
            }
        }
    };

    static final Action SHAPE_TYPE = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int shape = a.getInt(attr, GradientDrawable.RECTANGLE);
            builder.setShape(shape);
        }
    };

    static final Action GRADIENT_TYPE = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int type = a.getInt(attr, GradientDrawable.LINEAR_GRADIENT);
            builder.setGradientType(type);
        }
    };

    static final Action STROKE_COLOR = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int color = a.getColor(attr, 0);
            if (color != 0) {
                builder.setStrokeColor(color);
            }
        }
    };

    static final Action RADIAL_GRADIENT_RADIUS = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float radius = a.getDimension(attr, RADIAL_RADIUS_DEFAULT);
            builder.setGradientRadius(radius);
        }
    };

    static final Action STROKE_WIDTH = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float width = a.getDimension(attr, STROKE_WIDTH_DEFAULT);
            builder.setStrokeWidth(width);
        }
    };


    static final Action CORNER_RADIUS_ALL = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float width = a.getDimension(attr, 0);
            builder.setAllCornerRadius(width);
        }
    };

    static final Action CORNER_RADIUS_LT = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setCornerRadiusLeftTop(dimen);
        }
    };

    static final Action CORNER_RADIUS_RT = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setCornerRadiusRightTop(dimen);

        }
    };

    static final Action CORNER_RADIUS_LB = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setCornerRadiusLeftBottom(dimen);
        }
    };

    static final Action CORNER_RADIUS_RB = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setCornerRadiusRightBottom(dimen);
        }
    };

    static final Action USE_LEVEL = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            builder.setUseLevel(a.getBoolean(attr, false));
        }
    };

    static final Action PADDING_LEFT = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setPaddingLeft(dimen);
        }
    };

    static final Action PADDING_TOP = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setPaddingTop(dimen);
        }
    };

    static final Action PADDING_RIGHT = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setPaddingRight(dimen);
        }
    };

    static final Action PADDING_BOTTOM = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setPaddingBottom(dimen);
        }
    };

    static final Action SIZE_WIDTH = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, ViewGroup.LayoutParams.MATCH_PARENT);
            builder.setSizeWidth(dimen);
        }
    };

    static final Action SIZE_HEIGHT = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, ViewGroup.LayoutParams.MATCH_PARENT);
            builder.setSizeHeight(dimen);
        }
    };

    static final Action DASH_WIDTH = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setDashWidth(dimen);
        }
    };

    static final Action DASH_GAP = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getDimension(attr, 0);
            builder.setDashGap(dimen);
        }
    };

    static final Action GRADIENT_CENTER_X = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getFloat(attr, 0.5f);
            builder.setGradientCenterX(dimen);
        }
    };

    static final Action GRADIENT_CENTER_Y = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            float dimen = a.getFloat(attr, 0.5f);
            builder.setGradientCenterY(dimen);
        }
    };


    static final Action GRADIENT_ANGLE = new Action() {
        @Override
        public void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception {
            int angle = a.getInt(attr, 0);
            builder.setGradientAngle(angle);
        }
    };


    private boolean hasSolidColor;
    private boolean hasStrokeColor;
    private boolean mHasPadding;
    private boolean mHasCornerRadiusSingleSet;

    private int mShape = GradientDrawable.RECTANGLE;
    private boolean mUseLevel = false;

    //矩形圆角
    private float mAllCornerRadius;
    private float[] cornerRadius = new float[8];

    //边线
    private int strokeColor;
    private float mStrokeWidth = STROKE_WIDTH_DEFAULT;
    private float mDashWidth;
    private float mDashGap;

    private int mSolidColor;

    //渐变类型
    private int mGradientType = GradientDrawable.LINEAR_GRADIENT;
    //radial光晕渐变的半径，光晕渐变必设
    private float mGradientRadius = RADIAL_RADIUS_DEFAULT;
    private GradientDrawable.Orientation orientation = GradientDrawable.Orientation.LEFT_RIGHT;

    private int mGradientStartColor;
    private int mGradientCenterColor;
    private int mGradientEndColor;

    private float mGradientCenetrX = 0.5f;
    private float mGradientCenterY = 0.5f;

    private RectF mPadding = new RectF();

    private float mSizeWidth;
    private float mSizeHeight;

    @Override
    public DrawableBuilder clone() throws CloneNotSupportedException {
        DrawableBuilder newOne = (DrawableBuilder) super.clone();
        newOne.mPadding = new RectF(mPadding);
        newOne.cornerRadius = cornerRadius.clone();
        return newOne;
    }

    public static DrawableBuilder cloneOrNew(DrawableBuilder drawableBuilder) {
        if (drawableBuilder == null) {
            return new DrawableBuilder();
        }

        DrawableBuilder newOne;
        try {
            newOne = drawableBuilder.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            newOne = new DrawableBuilder();
        }
        return newOne;
    }

    private DrawableBuilder() {
    }

    private void setStrokeColor(@ColorInt int strokeColor) {
        if (strokeColor == 0) {
            return;
        }
        hasStrokeColor = true;
        this.strokeColor = strokeColor;
    }

    private void setCornerRadiusLeftTop(float cornerRadiusLeftTop) {
        mHasCornerRadiusSingleSet = true;
        cornerRadius[0] = cornerRadiusLeftTop;
        cornerRadius[1] = cornerRadiusLeftTop;
    }

    private void setCornerRadiusRightTop(float cornerRadiusRightTop) {
        mHasCornerRadiusSingleSet = true;
        cornerRadius[2] = cornerRadiusRightTop;
        cornerRadius[3] = cornerRadiusRightTop;
    }

    private void setCornerRadiusLeftBottom(float cornerRadiusLeftBottom) {
        mHasCornerRadiusSingleSet = true;
        cornerRadius[6] = cornerRadiusLeftBottom;
        cornerRadius[7] = cornerRadiusLeftBottom;
    }

    private void setCornerRadiusRightBottom(float cornerRadiusRightBottom) {
        mHasCornerRadiusSingleSet = true;
        cornerRadius[4] = cornerRadiusRightBottom;
        cornerRadius[5] = cornerRadiusRightBottom;
    }

    private void setStrokeWidth(float strokeWidth) {
        mStrokeWidth = strokeWidth;
    }

    private void setAllCornerRadius(float allCornerRadius) {
        mAllCornerRadius = allCornerRadius;
    }

    private void setSolidColor(int solidColor) {
        if (solidColor == 0) {
            return;
        }
        mSolidColor = solidColor;
        hasSolidColor = true;
    }

    private void setUseLevel(boolean useLevel) {
        mUseLevel = useLevel;
    }

    private void setPaddingLeft(float paddingLeft) {
        mHasPadding = true;
        mPadding.left = paddingLeft;
    }

    private void setPaddingTop(float paddingTop) {
        mHasPadding = true;
        mPadding.top = paddingTop;
    }

    private void setPaddingRight(float paddingRight) {
        mHasPadding = true;
        mPadding.right = paddingRight;
    }

    private void setPaddingBottom(float paddingBottom) {
        mHasPadding = true;
        mPadding.bottom = paddingBottom;
    }

    private void setSizeWidth(float sizeWidth) {
        mSizeWidth = sizeWidth;
    }

    private void setSizeHeight(float sizeHeight) {
        mSizeHeight = sizeHeight;
    }

    private void setDashWidth(float dashWidth) {
        mDashWidth = dashWidth;
    }

    private void setDashGap(float dashGap) {
        mDashGap = dashGap;
    }

    private void setGradientStartColor(int gradientStartColor) {
        mGradientStartColor = gradientStartColor;
    }

    private void setGradientCenterColor(int gradientCenterColor) {
        mGradientCenterColor = gradientCenterColor;
    }

    private void setGradientEndColor(int gradientEndColor) {
        mGradientEndColor = gradientEndColor;
    }

    private void setShape(int shape) {
        mShape = shape;
    }

    private void setGradientType(int gradientType) {
        mGradientType = gradientType;
    }

    private void setGradientRadius(float gradientRadius) {
        mGradientRadius = gradientRadius;
    }

    private void setGradientCenterX(float gradientCenetrx) {
        mGradientCenetrX = gradientCenetrx;
    }

    private void setGradientCenterY(float gradientCenterY) {
        mGradientCenterY = gradientCenterY;
    }

    private void setGradientAngle(int gradientAngle) {
        orientation = getOrientation(gradientAngle);
    }

    private boolean hasGradientColor() {
        return mGradientStartColor != 0 && mGradientEndColor != 0;
    }


    public GradientDrawable build() {
        if (!hasSolidColor && !hasStrokeColor && !hasGradientColor()) {
            return null;
        }

        GradientDrawable drawable;
        if (hasSolidColor) {
            drawable = new GradientDrawable();
            drawable.setColor(mSolidColor);
        } else if (hasStrokeColor) {
            drawable = new GradientDrawable();
            drawable.setColor(Color.TRANSPARENT);
        } else {
            int[] colors;
            if (mGradientCenterColor == 0) {
                colors = new int[]{mGradientStartColor, mGradientEndColor};
            } else {
                colors = new int[]{mGradientStartColor, mGradientCenterColor, mGradientEndColor};
            }
            drawable = new GradientDrawable(orientation, colors);
            drawable.setGradientType(mGradientType);
            if (mGradientType == GradientDrawable.RADIAL_GRADIENT) {    //光晕渐变必须设半径，默认100像素
                drawable.setGradientRadius(mGradientRadius);
            }
        }

        drawable.setUseLevel(mUseLevel);
        drawable.setShape(mShape);
        drawable.setGradientCenter(mGradientCenetrX, mGradientCenterY);
        if (hasStrokeColor) {
            drawable.setStroke((int) mStrokeWidth, strokeColor, mDashWidth, mDashGap);
        }
        if (mSizeWidth != 0 && mSizeHeight != 0) {
            drawable.setSize((int) mSizeWidth, (int) mSizeHeight);
        }

        if (mHasCornerRadiusSingleSet) {
            drawable.setCornerRadii(cornerRadius);
        } else {
            drawable.setCornerRadius(mAllCornerRadius);
        }

        if (mHasPadding) {
            trySetPadding(drawable);
        }
        return drawable;
    }

    private void trySetPadding(GradientDrawable drawable) {
        try {
            Field paddingField = drawable.getClass().getField("mPadding");
            paddingField.setAccessible(true);
            paddingField.set(drawable, mPadding);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private static GradientDrawable.Orientation getOrientation(int gradientAngle) {
        GradientDrawable.Orientation orientation = GradientDrawable.Orientation.TOP_BOTTOM;
        gradientAngle %= 360;
        if (gradientAngle % 45 != 0) {
            return orientation;
        }
        switch (gradientAngle) {
            case 0:
                orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case 45:
                orientation = GradientDrawable.Orientation.BL_TR;
                break;
            case 90:
                orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case 135:
                orientation = GradientDrawable.Orientation.BR_TL;
                break;
            case 180:
                orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case 225:
                orientation = GradientDrawable.Orientation.TR_BL;
                break;
            case 270:
                orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case 315:
                orientation = GradientDrawable.Orientation.TL_BR;
                break;
        }
        return orientation;
    }


    interface Action {
        void process(TypedArray a, int attr, DrawableBuilder builder) throws Exception;
    }

}


