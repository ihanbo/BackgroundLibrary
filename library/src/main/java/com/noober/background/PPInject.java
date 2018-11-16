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
        }
        return mInflater;
    }


    public void setFactory3(Factory3 factory3) {
        if (mInflater.mEF != null) {
            mInflater.mEF.mFactory3 = factory3;
        }
    }

    public void setExtraFactory2(LayoutInflater.Factory2 extraFactory2) {
        if (mInflater.mEF != null) {
            mInflater.mEF.mExtraFactory2 = extraFactory2;
        }
    }


    private static class LayoutInflaterWrapper extends LayoutInflater {
        private ExpandableFactory mEF;
        private LayoutInflater mDelegate;
        private boolean mFactorySet;


        LayoutInflaterWrapper(LayoutInflater original, Context newContext) {
            super(original, newContext);
            mDelegate = original;
        }

        @Override
        public void setFactory(Factory factory) {
            if (mFactorySet) {
                throw new IllegalStateException("A factory has already been set on this LayoutInflater");
            }
            if (factory == null) {
                throw new NullPointerException("Given factory can not be null");
            }
            mFactorySet = true;
            super.setFactory(mEF = new ExpandableFactory(factory));
        }

        @Override
        public void setFactory2(Factory2 factory) {
            if (mFactorySet) {
                throw new IllegalStateException("A factory has already been set on this LayoutInflater");
            }
            if (factory == null) {
                throw new NullPointerException("Given factory can not be null");
            }
            mFactorySet = true;
            super.setFactory2(mEF = new ExpandableFactory(factory));
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
        LayoutInflater.Factory2 mExtraFactory2;
        Factory3 mFactory3;

        LayoutInflater.Factory2 mDelegate2; //代理的
        LayoutInflater.Factory mDelegate;   //代理的
        boolean isFactoty2;

        ExpandableFactory(LayoutInflater.Factory2 delegate) {
            mDelegate2 = delegate;
            isFactoty2 = true;
        }

        ExpandableFactory(LayoutInflater.Factory factory) {
            mDelegate = factory;
            isFactoty2 = false;
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            View view = null;

            if (mExtraFactory2 != null) {
                view = mExtraFactory2.onCreateView(parent, name, context, attrs);
            }

            if (view == null) {
                if (isFactoty2) {
                    view = mDelegate2.onCreateView(parent, name, context, attrs);
                } else {
                    view = mDelegate.onCreateView(name, context, attrs);
                }
            }

            if (view != null && mFactory3 != null) {
                mFactory3.onDecorateView(view, name, context, attrs, parent);
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