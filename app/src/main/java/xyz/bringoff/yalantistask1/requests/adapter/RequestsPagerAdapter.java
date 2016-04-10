package xyz.bringoff.yalantistask1.requests.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.requests.fragment.RequestListFragment;
import xyz.bringoff.yalantistask1.requests.fragment.RequestRecyclerFragment;

public class RequestsPagerAdapter extends FragmentPagerAdapter {

    public static final int TAB_COUNT = 3;

    private Context mContext;

    public RequestsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            case 1:
                return RequestRecyclerFragment.newInstance();
            case 2:
                return RequestListFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.in_work);
            case 1:
                return mContext.getString(R.string.done);
            case 2:
                return mContext.getString(R.string.waiting);
        }
        return super.getPageTitle(position);
    }
}
