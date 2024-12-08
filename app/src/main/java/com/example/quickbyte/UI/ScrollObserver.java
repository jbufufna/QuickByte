package com.example.quickbyte.UI;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

public class ScrollObserver {

    private static int lastTriggeredY = 0;

    /**
     * Observes downward scrolling on a ScrollView and triggers a callback every X pixels.
     *
     * @param scrollView   The ScrollView to observe.
     * @param scrollHeight The threshold scroll height in pixels (size of each card).
     * @param callback     The callback to execute every X pixels scrolled downward.
     */
    public static void observeDownward(ScrollView scrollView, int scrollHeight, OnScrollCallback callback) {
        // Do nothing if the scrollHeight is less than or equal to 0
        if (scrollHeight <= 0) {
            return;
        }

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY(); // Get current scroll position

                if (scrollY > lastTriggeredY) {
                    System.out.println("Scrolled down");  // Only print if scrolling downward
                    lastTriggeredY = scrollY;  // Update last scroll position to current

                    callback.onScrollChanged();
                }
            }
        });
    }

    // Interface for the callback
    public interface OnScrollCallback {
        void onScrollChanged();
    }
}
