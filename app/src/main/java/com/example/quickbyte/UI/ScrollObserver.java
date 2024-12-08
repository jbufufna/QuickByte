package com.example.quickbyte.UI;
import android.view.View;
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

        // Set scroll listener on ScrollView
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (callback != null && scrollY > oldScrollY) { // Only trigger on downward scroll

                    System.out.println("Scrolling down");

                    if (scrollY - lastTriggeredY >= scrollHeight) {
                        // Trigger the callback
                        callback.onScroll(scrollX, scrollY, oldScrollX, oldScrollY);

                        // Update the last triggered position
                        lastTriggeredY = scrollY;
                    }
                }
            }
        });
    }

    // Interface for the callback
    public interface OnScrollCallback {
        void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }
}
