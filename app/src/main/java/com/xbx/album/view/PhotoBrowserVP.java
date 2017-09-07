package com.xbx.album.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Eric on 2016/8/24.
 */
public class PhotoBrowserVP extends ViewPager {

    public PhotoBrowserVP(Context context) {
        super(context);
    }

    public PhotoBrowserVP(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
        } catch (ArrayIndexOutOfBoundsException e) {

        }
        return false;
    }
}
