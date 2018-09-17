package com.noober.background.utils;

import android.util.SparseIntArray;

import com.noober.background.R;

public class TypeValueHelper {

    public static final SparseIntArray sAppearanceValues = new SparseIntArray();
    public static final SparseIntArray sAppearancePressValues = new SparseIntArray();
    static {
        sAppearanceValues.put(R.styleable.background_bg_shape, R.styleable.background_bg_shape);
        sAppearanceValues.put(R.styleable.background_bg_solid_color, R.styleable.background_bg_solid_color);
        sAppearanceValues.put(R.styleable.background_bg_corners_radius, R.styleable.background_bg_corners_radius);
        sAppearanceValues.put(R.styleable.background_bg_corners_bl_radius, R.styleable.background_bg_corners_bl_radius);
        sAppearanceValues.put(R.styleable.background_bg_corners_br_radius, R.styleable.background_bg_corners_br_radius);
        sAppearanceValues.put(R.styleable.background_bg_corners_tl_radius, R.styleable.background_bg_corners_tl_radius);
        sAppearanceValues.put(R.styleable.background_bg_corners_tr_radius, R.styleable.background_bg_corners_tr_radius);
        sAppearanceValues.put(R.styleable.background_bg_gradient_angle, R.styleable.background_bg_gradient_angle);
        sAppearanceValues.put(R.styleable.background_bg_gradient_centerX, R.styleable.background_bg_gradient_centerX);
        sAppearanceValues.put(R.styleable.background_bg_gradient_centerY, R.styleable.background_bg_gradient_centerY);
        sAppearanceValues.put(R.styleable.background_bg_gradient_centerColor, R.styleable.background_bg_gradient_centerColor);
        sAppearanceValues.put(R.styleable.background_bg_gradient_endColor, R.styleable.background_bg_gradient_endColor);
        sAppearanceValues.put(R.styleable.background_bg_gradient_startColor, R.styleable.background_bg_gradient_startColor);
        sAppearanceValues.put(R.styleable.background_bg_gradient_gradient_radius, R.styleable.background_bg_gradient_gradient_radius);
        sAppearanceValues.put(R.styleable.background_bg_gradient_type, R.styleable.background_bg_gradient_type);
        sAppearanceValues.put(R.styleable.background_gradient_useLevel, R.styleable.background_gradient_useLevel);
        sAppearanceValues.put(R.styleable.background_bg_padding_left, R.styleable.background_bg_padding_left);
        sAppearanceValues.put(R.styleable.background_bg_padding_top, R.styleable.background_bg_padding_top);
        sAppearanceValues.put(R.styleable.background_bg_padding_right, R.styleable.background_bg_padding_right);
        sAppearanceValues.put(R.styleable.background_bg_padding_bottom, R.styleable.background_bg_padding_bottom);
        sAppearanceValues.put(R.styleable.background_size_width, R.styleable.background_size_width);
        sAppearanceValues.put(R.styleable.background_size_height, R.styleable.background_size_height);
        sAppearanceValues.put(R.styleable.background_bg_stroke_width, R.styleable.background_bg_stroke_width);
        sAppearanceValues.put(R.styleable.background_bg_stroke_color, R.styleable.background_bg_stroke_color);
        sAppearanceValues.put(R.styleable.background_bg_stroke_dashWidth, R.styleable.background_bg_stroke_dashWidth);
        sAppearanceValues.put(R.styleable.background_bg_stroke_dashGap, R.styleable.background_bg_stroke_dashGap);
        sAppearanceValues.put(R.styleable.background_ripple_enable, R.styleable.background_ripple_enable);
        sAppearanceValues.put(R.styleable.background_ripple_color, R.styleable.background_ripple_color);


    }


    static void put(int style){
        sAppearanceValues.put(style, style);
    }
}
