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
     * @param scrollHeight The threshold scroll height in pixels (size of each card).
     * @param callback     The callback to execute every X pixels scrolled downward.
     */
    public void observeDownward(ScrollView scrollView, int scrollHeight, OnScrollCallback callback) {
        // Do nothing if the scrollHeight is less than or equal to 0
        if (scrollHeight <= 0) {
            return;
        }

        // Make sure the listener is added after the layout is complete
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        int scrollY = scrollView.getScrollY(); // Get current scroll position

                        System.out.println("Scroll changed");

                        // Only execute the callback if scrolling down
                        if (scrollY > lastTriggeredY) {
                            System.out.println("Scrolled down");
                            lastTriggeredY = scrollY;  // Update last scroll position to current

                            // Trigger the callback
                            callback.onScrollChanged();
                        }
                    }
                });
            }
        });
    }

    // Interface for the callback
    public interface OnScrollCallback {
        void onScrollChanged();
    }
}
