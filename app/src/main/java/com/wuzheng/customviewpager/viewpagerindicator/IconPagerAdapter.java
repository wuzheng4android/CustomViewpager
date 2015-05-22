package com.wuzheng.customviewpager.viewpagerindicator;

public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index_activity} in the adapter.
     */
    int getIconResId(int index);

    // From PagerAdapter
    int getCount();
}
