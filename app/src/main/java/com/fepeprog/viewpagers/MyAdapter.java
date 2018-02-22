package com.fepeprog.viewpagers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by fepeprog on 2/20/18.
 */

public class MyAdapter extends FragmentPagerAdapter {

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return FragmentPage.newInstance("Home");
        if(position == 1)
            return FragmentHome.newInstance();
        if(position == 2)
            return FragmentPage.newInstance("Notifications");
        else
            return FragmentPage.newInstance("Error");
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return ("Home");
        if(position == 1)
            return "Hoome";
        if(position == 2)
            return ("Notifications");
        else
            return ("Error");
    }
}
