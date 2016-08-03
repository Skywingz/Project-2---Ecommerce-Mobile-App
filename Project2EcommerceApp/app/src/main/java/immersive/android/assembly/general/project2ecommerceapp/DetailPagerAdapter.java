package immersive.android.assembly.general.project2ecommerceapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;

/**
 * Created by Skywingz on 7/28/16.
 */
public class DetailPagerAdapter extends FragmentStatePagerAdapter {

    private int pageCount;
    private String heroName;



    public DetailPagerAdapter(FragmentManager fm, int pages, String name) {
        super(fm);
        this.pageCount = pages;
        this.heroName = name;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailTabFragment.getInstance(position, heroName);
    }

    @Override
    public int getItemPosition(Object object) {
//        return FragmentPagerAdapter.POSITION_NONE;
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            default:
            case 0:
                return "DETAILS";
            case 1:
                return "STORY";
            case 2:
                return "REVIEWS";
        }
    }

}
