package com.example.quickbyte.UI;
import android.view.View;
import android.widget.ScrollView;

public class ScrollObserver {

    private static int lastTriggeredY = 0;

    /**
     * Observes downward scrolling on a ScrollView and triggers a callback every X pixels.
     *
     * @param scrollView   The ScrollView to observe.
     * @param threshold    The pixel threshold to trigger the callback.
     * @param callback     The callback to execute every X pixels scrolled downward.
     */
    public static void observeDownward(ScrollView scrollView, int threshold, OnScrollCallback callback) {
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (callback != null && scrollY > oldScrollY) { // Only trigger on downward scroll
                    if (scrollY - lastTriggeredY >= threshold) {
                        // Trigger the callback
                        callback.onScroll(scrollX, scrollY, oldScrollX, oldScrollY);

                        // Update the last triggered position
                        lastTriggeredY = scrollY;
                    }
                }
            }
        });
    }
}
