package com.example.quickbyte.UI;

public interface OnScrollCallback {
    /**
     * Callback method triggered when the scroll threshold is met.
     *
     * @param scrollX   The current horizontal scroll position.
     * @param scrollY   The current vertical scroll position.
     * @param oldScrollX The previous horizontal scroll position.
     * @param oldScrollY The previous vertical scroll position.
     */
    void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
}

