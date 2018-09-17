package com.noober.background;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public class BackgroundLibrary {

    public static void inject(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();
        inject(inflater, activity);
    }


    public static void inject(android.support.v4.app.Fragment fragment) {
        LayoutInflater inflater = fragment.getLayoutInflater();
        inject(inflater, fragment.getContext());
    }

    public static void inject(Context context) {
        LayoutInflater inflater;
        if (context instanceof Activity) {
            inflater = ((Activity) context).getLayoutInflater();
        } else {
            inflater = LayoutInflater.from(context);
        }
        inject(inflater, context);
    }

    public static void inject(LayoutInflater inflater, Context context) {
        BackgroundFactory factory = new BackgroundFactory(inflater);
        if (context instanceof AppCompatActivity) {
            factory.setInterceptFactory(new CompatFactory((AppCompatActivity) context));
        }
        inflater.setFactory(factory);
    }


    static class CompatFactory implements LayoutInflater.Factory2 {
        private AppCompatDelegate delegate;

        public CompatFactory(AppCompatActivity compatActivity) {
            this.delegate = compatActivity.getDelegate();
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return onCreateView(null, name, context, attrs);
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return delegate.createView(parent, name, context, attrs);
        }

    }
}
