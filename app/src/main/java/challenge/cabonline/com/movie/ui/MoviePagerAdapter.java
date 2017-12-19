package challenge.cabonline.com.movie.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class MoviePagerAdapter extends FragmentStatePagerAdapter {

    int PAGE_COUNT = 3;

    String[] tabTitles = {"Top", "Popular", "Now Playing"};
    String[] categories = {"top", "popular", "now"};


    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MovieListFragment fragment = new MovieListFragment();
        fragment.setCategory(categories[position]);

        return fragment.newInstance(position + 1, categories[position]);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
