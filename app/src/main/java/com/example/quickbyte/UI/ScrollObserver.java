package com.example.quickbyte.UI;

import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.example.quickbyte.Facade.Facade;

public class ScrollObserver {

    private static ScrollObserver instance;

    private static int lastTriggeredY = 0;


    public static synchronized ScrollObserver getInstance() {
        if (instance == null) {
            instance = new ScrollObserver();
        }
        return instance;
    }


    /**
     * Observes downward scrolling on a ScrollView and triggers a callback every X pixels.
     *
     * @param scrollView   The ScrollView to observe.
     * @param scrollHeight The threshold scroll height in pixels (e.g., size of each card).
     * @param callback     The callback to execute every X pixels scrolled downward.
     */
    public void observeDownward(ScrollView scrollView, int scrollHeight, OnScrollCallback callback) {
        // Do nothing if the scrollHeight is less than or equal to 0
        if (scrollHeight <= 0) {
            return;
        }

        // Ensure the listener is added after the layout is complete
        scrollView.post(() -> {
            scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
                int scrollY = scrollView.getScrollY(); // Get current scroll position

                // Trigger the callback every time we surpass a new multiple of scrollHeight
                if (scrollY - lastTriggeredY >= scrollHeight) {
                    lastTriggeredY += scrollHeight; // Update to the new triggered threshold

                    // Trigger the callback
                    callback.onScrollChanged();
                }
            });
        });
    }

    // Interface for the callback
    public interface OnScrollCallback {
        void onScrollChanged();
    }
}
