package com.noober.background;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author hanbo
 * @date 2018/11/16
 */
public class PPInject {
    private LayoutInflaterWrapper mInflater;

    public PPInject(BackgroundFactory backgroundFactory) {

    }

    public Object getInflater(Activity activity) {
        if (mInflater == null) {
            mInflater = new LayoutInflaterWrapper(LayoutInflater.from(activity.getBaseContext()).cloneInContext(activity), activity);
            setFactory3(new BackgroundFactory());
        }
        return mInflater;
    }


    public void setFactory3(Factory3 factory3) {
        mInflater.setFactory3(factory3);
    }

    public void setExtraFactory2(LayoutInflater.Factory2 extraFactory2) {
        mInflater.setFactory2(extraFactory2);
    }


    private static class LayoutInflaterWrapper extends LayoutInflater {
        private ExpandableFactory mEF;
        private LayoutInflater mDelegate;
        private boolean mFactorySet;


        LayoutInflaterWrapper(LayoutInflater original, Context newContext) {
            super(original, newContext);
            mDelegate = original;
            mDelegate.setFactory2(mEF = new ExpandableFactory());
        }

        @Override
        public void setFactory(final Factory factory) {
            mEF.f2s.add(new Factory2() {
                @Override
                public View onCreateView(View view, String s, Context context, AttributeSet attributeSet) {
                    return onCreateView(s, context, attributeSet);
                }

                @Override
                public View onCreateView(String s, Context context, AttributeSet attributeSet) {
                    return factory.onCreateView(s, context, attributeSet);
                }
            });
        }

        @Override
        public void setFactory2(Factory2 factory) {
            mEF.f2s.add(factory);
        }

        public void setFactory3(Factory3 factory) {
            mEF.f3s.add(factory);
        }

        @Override
        public Context getContext() {
            return mDelegate.getContext();
        }

        @Override
        public Filter getFilter() {
            return mDelegate.getFilter();
        }

        @Override
        public View inflate(int resource, @Nullable ViewGroup root) {
            return mDelegate.inflate(resource, root);
        }

        @Override
        public View inflate(int resource, @Nullable ViewGroup root, boolean attachToRoot) {
            return mDelegate.inflate(resource, root, attachToRoot);
        }

        @Override
        public View inflate(XmlPullParser parser, @Nullable ViewGroup root) {
            return mDelegate.inflate(parser, root);
        }

        @Override
        public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
            return mDelegate.inflate(parser, root, attachToRoot);
        }

        @Override
        public void setFilter(Filter filter) {
            super.setFilter(filter);
            if (mDelegate != null) {
                mDelegate.setFilter(filter);
            }
        }

        @Override
        public LayoutInflater cloneInContext(Context newContext) {
            return new LayoutInflaterWrapper(this, newContext);
        }
    }


    private static class ExpandableFactory implements LayoutInflater.Factory2 {
        CopyOnWriteArrayList<LayoutInflater.Factory2> f2s = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<Factory3> f3s = new CopyOnWriteArrayList<>();

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            View view = null;

            if (!f2s.isEmpty()) {
                for (int i = 0; i < f2s.size(); i++) {
                    view = f2s.get(i).onCreateView(parent, name, context, attrs);
                    if (view != null) {
                        break;
                    }
                }
            }

            if (view != null && !f3s.isEmpty()) {
                for (int i = 0; i < f3s.size(); i++) {
                    f3s.get(i).onDecorateView(view, name, context, attrs, parent);
                }
            }

            return view;
        }


        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return onCreateView(null, name, context, attrs);
        }

    }


    public interface Factory3 {
        void onDecorateView(View createdView, String name, Context context, AttributeSet attrs, View parent);
    }
}