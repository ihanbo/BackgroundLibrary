package com.noober.background;

import android.util.SparseArray;

import static com.noober.background.DrawableBuilder.CORNER_RADIUS_ALL;
import static com.noober.background.DrawableBuilder.CORNER_RADIUS_LB;
import static com.noober.background.DrawableBuilder.CORNER_RADIUS_LT;
import static com.noober.background.DrawableBuilder.CORNER_RADIUS_RB;
import static com.noober.background.DrawableBuilder.CORNER_RADIUS_RT;
import static com.noober.background.DrawableBuilder.DASH_GAP;
import static com.noober.background.DrawableBuilder.DASH_WIDTH;
import static com.noober.background.DrawableBuilder.GRADIENT_ANGLE;
import static com.noober.background.DrawableBuilder.GRADIENT_CENTER_COLOR;
import static com.noober.background.DrawableBuilder.GRADIENT_CENTER_X;
import static com.noober.background.DrawableBuilder.GRADIENT_CENTER_Y;
import static com.noober.background.DrawableBuilder.GRADIENT_END_COLOR;
import static com.noober.background.DrawableBuilder.GRADIENT_START_COLOR;
import static com.noober.background.DrawableBuilder.GRADIENT_TYPE;
import static com.noober.background.DrawableBuilder.PADDING_BOTTOM;
import static com.noober.background.DrawableBuilder.PADDING_LEFT;
import static com.noober.background.DrawableBuilder.PADDING_RIGHT;
import static com.noober.background.DrawableBuilder.PADDING_TOP;
import static com.noober.background.DrawableBuilder.RADIAL_GRADIENT_RADIUS;
import static com.noober.background.DrawableBuilder.SHAPE_TYPE;
import static com.noober.background.DrawableBuilder.SIZE_HEIGHT;
import static com.noober.background.DrawableBuilder.SIZE_WIDTH;
import static com.noober.background.DrawableBuilder.SOLID_COLOR;
import static com.noober.background.DrawableBuilder.STROKE_COLOR;
import static com.noober.background.DrawableBuilder.STROKE_WIDTH;
import static com.noober.background.DrawableBuilder.USE_LEVEL;

/**
 * shapedrawable的属性
 *
 * @Author hanbo
 * @Since 2018/9/19
 */
class AttrHelper {

    final static SparseArray<DrawableBuilder.Action> COMM = new SparseArray<>(32);
    final static SparseArray<DrawableBuilder.Action> NORMAL = new SparseArray<>(16);
    final static SparseArray<DrawableBuilder.Action> PRESSED = new SparseArray<>(16);
    final static SparseArray<DrawableBuilder.Action> CHECK_SELECT = new SparseArray<>(16);


    static {

        addComm(COMM);

        NORMAL.put(R.styleable.mc_bg_shape_bgs_solid_color, SOLID_COLOR);
        NORMAL.put(R.styleable.mc_bg_shape_bgs_stroke_color, STROKE_COLOR);
        NORMAL.put(R.styleable.mc_bg_shape_bgs_gradient_startColor, GRADIENT_START_COLOR);
        NORMAL.put(R.styleable.mc_bg_shape_bgs_gradient_centerColor, GRADIENT_CENTER_COLOR);
        NORMAL.put(R.styleable.mc_bg_shape_bgs_gradient_endColor, GRADIENT_END_COLOR);


        PRESSED.put(R.styleable.mc_bg_shape_pressed_bgs_solid_color_pressed, SOLID_COLOR);
        PRESSED.put(R.styleable.mc_bg_shape_pressed_bgs_stroke_color_pressed, STROKE_COLOR);
        PRESSED.put(R.styleable.mc_bg_shape_pressed_bgs_gradient_startColor_pressed, GRADIENT_START_COLOR);
        PRESSED.put(R.styleable.mc_bg_shape_pressed_bgs_gradient_centerColor_pressed, GRADIENT_CENTER_COLOR);
        PRESSED.put(R.styleable.mc_bg_shape_pressed_bgs_gradient_endColor_pressed, GRADIENT_END_COLOR);

        CHECK_SELECT.put(R.styleable.mc_bg_shape_selected_or_checked_bgs_solid_color_cs, SOLID_COLOR);
        CHECK_SELECT.put(R.styleable.mc_bg_shape_selected_or_checked_bgs_stroke_color_cs, STROKE_COLOR);
        CHECK_SELECT.put(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_startColor_cs, GRADIENT_START_COLOR);
        CHECK_SELECT.put(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_centerColor_cs, GRADIENT_CENTER_COLOR);
        CHECK_SELECT.put(R.styleable.mc_bg_shape_selected_or_checked_bgs_gradient_endColor_cs, GRADIENT_END_COLOR);

    }


    private static void addComm(SparseArray<DrawableBuilder.Action> ss) {
        ss.put(R.styleable.mc_bg_shape_other_bgs_shape, SHAPE_TYPE);
        ss.put(R.styleable.mc_bg_shape_other_bgs_corners_radius, CORNER_RADIUS_ALL);
        ss.put(R.styleable.mc_bg_shape_other_bgs_corners_radius_lb, CORNER_RADIUS_LB);
        ss.put(R.styleable.mc_bg_shape_other_bgs_corners_radius_rb, CORNER_RADIUS_RB);
        ss.put(R.styleable.mc_bg_shape_other_bgs_corners_radius_lt, CORNER_RADIUS_LT);
        ss.put(R.styleable.mc_bg_shape_other_bgs_corners_radius_rt, CORNER_RADIUS_RT);
        ss.put(R.styleable.mc_bg_shape_other_bgs_gradient_type, GRADIENT_TYPE);
        ss.put(R.styleable.mc_bg_shape_other_bgs_gradient_useLevel, USE_LEVEL);
        ss.put(R.styleable.mc_bg_shape_other_bgs_gradient_angle, GRADIENT_ANGLE);
        ss.put(R.styleable.mc_bg_shape_other_bgs_gradient_centerX, GRADIENT_CENTER_X);
        ss.put(R.styleable.mc_bg_shape_other_bgs_gradient_centerY, GRADIENT_CENTER_Y);
        ss.put(R.styleable.mc_bg_shape_other_bgs_gradient_gradient_radius, RADIAL_GRADIENT_RADIUS);
        ss.put(R.styleable.mc_bg_shape_other_bgs_padding_left, PADDING_LEFT);
        ss.put(R.styleable.mc_bg_shape_other_bgs_padding_top, PADDING_TOP);
        ss.put(R.styleable.mc_bg_shape_other_bgs_padding_right, PADDING_RIGHT);
        ss.put(R.styleable.mc_bg_shape_other_bgs_padding_bottom, PADDING_BOTTOM);
        ss.put(R.styleable.mc_bg_shape_other_bgs_size_width, SIZE_WIDTH);
        ss.put(R.styleable.mc_bg_shape_other_bgs_size_height, SIZE_HEIGHT);
        ss.put(R.styleable.mc_bg_shape_other_bgs_stroke_width, STROKE_WIDTH);
        ss.put(R.styleable.mc_bg_shape_other_bgs_stroke_dashWidth, DASH_WIDTH);
        ss.put(R.styleable.mc_bg_shape_other_bgs_stroke_dashGap, DASH_GAP);
    }
}
