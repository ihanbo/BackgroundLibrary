package com.noober.background;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.noober.background.utils.TypeValueHelper;


public class BackgroundFactory implements LayoutInflater.Factory2 {


    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app."
    };

    //AppCompatActivity用的来创建view
    private LayoutInflater.Factory mViewCreateFactory;
    //用于创建view
    private LayoutInflater mInflater;

    public BackgroundFactory(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public void setInterceptFactory(LayoutInflater.Factory2 factory) {
        mViewCreateFactory = factory;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return onCreateView(null, name, context, attrs);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = null;
        if (mViewCreateFactory != null) {
            view = mViewCreateFactory.onCreateView(name, context, attrs);
        }

        if (view == null) {
            view = createView(mInflater, name, attrs);
        }

        if (view == null) {
            return null;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.background);
        TypedArray pressTa = context.obtainStyledAttributes(attrs, R.styleable.bg_selector);

        try {

            int attrCount = typedArray.getIndexCount();
            if (attrCount == 0) {
                return view;
            }

            Drawable drawable = DrawableFactory.create(typedArray);

            if (drawable != null) {
                view.setBackgroundDrawable(drawable);
            }


//            if (pressTa.getIndexCount() > 0) {
//                StateListDrawable stateListDrawable = getStateListDrawable(drawable, getDrawable(typedArray), pressTa);
//                view.setClickable(true);
//                view.setBackgroundDrawable(stateListDrawable);
//            } else {
//                view.setBackgroundDrawable(drawable);
//            }
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
            pressTa.recycle();
        }

        return view;
    }


    private View createView(LayoutInflater inflater, String name, AttributeSet attrs) {
        for (String prefix : sClassPrefixList) {
            try {
                View view = inflater.createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }
        return null;
    }





}
