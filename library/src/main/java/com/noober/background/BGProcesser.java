package com.noober.background;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 背景处理
 *
 * @Author hanbo
 * @Since 2018/9/18
 */
public class BGProcesser extends BackgroundFactory.IBGProcesser {

    private BGSelector mBGSelector;
    private BGShape mBGShape;

    public BGProcesser() {
        mBGSelector = new BGSelector();
        mBGShape = new BGShape();
    }

    @Override
    public boolean process(View view, Context context, AttributeSet attrs) {
        boolean processed = mBGSelector.process(view, context, attrs);
        if (!processed) {
            processed = mBGShape.process(view, context, attrs);
        }
        return processed;
    }
}
