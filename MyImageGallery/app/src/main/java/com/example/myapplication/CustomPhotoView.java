package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.github.chrisbanes.photoview.PhotoView;

public class CustomPhotoView extends PhotoView {

    private OnThreeFingerSwipeListener threeFingerSwipeListener;
    private float startX = 0;
    private float startY = 0;
    private boolean isThreeFingerGesture = false;
    private static final int SWIPE_THRESHOLD = 100;

    public CustomPhotoView(Context context) {
        super(context);
    }

    public CustomPhotoView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public CustomPhotoView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int pointerCount = event.getPointerCount();
        if (pointerCount == 3) {
            handleThreeFingerGesture(event);
            return true;
        }
        if (pointerCount >= 4) {
            return true;
        }
        if (pointerCount == 1) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if (pointerCount == 2) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerCount = event.getPointerCount();

        if (pointerCount == 1 || pointerCount == 2) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if (event.getAction() == MotionEvent.ACTION_UP ||
                event.getAction() == MotionEvent.ACTION_CANCEL) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }

        return super.onTouchEvent(event);
    }

    private void handleThreeFingerGesture(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() == 3) {
                    isThreeFingerGesture = true;
                    startX = event.getX(0);
                    startY = event.getY(0);
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (isThreeFingerGesture && event.getPointerCount() == 3) {
                    float currentX = event.getX(0);
                    float deltaX = currentX - startX;
                    float deltaY = Math.abs(event.getY(0) - startY);

                    // Vuốt ngang
                    if (Math.abs(deltaX) > SWIPE_THRESHOLD && deltaY < SWIPE_THRESHOLD * 0.5f) {
                        if (threeFingerSwipeListener != null) {
                            if (deltaX > 0) {
                                threeFingerSwipeListener.onSwipeRight();
                            } else {
                                threeFingerSwipeListener.onSwipeLeft();
                            }
                        }
                        isThreeFingerGesture = false;
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                isThreeFingerGesture = false;
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
    }

    public interface OnThreeFingerSwipeListener {
        void onSwipeLeft();
        void onSwipeRight();
    }

    public void setOnThreeFingerSwipeListener(OnThreeFingerSwipeListener listener) {
        this.threeFingerSwipeListener = listener;
    }
}