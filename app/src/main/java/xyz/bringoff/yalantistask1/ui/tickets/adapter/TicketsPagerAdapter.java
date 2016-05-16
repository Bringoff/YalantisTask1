package xyz.bringoff.yalantistask1.ui.tickets.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.remote.ApiConstants;
import xyz.bringoff.yalantistask1.ui.tickets.TicketsFragment;

public class TicketsPagerAdapter extends FragmentPagerAdapter {

    public static final int TAB_COUNT = 3;

    private Context mContext;

    public TicketsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TicketsFragment.newInstance(ApiConstants.TicketStateFilter.IN_PROGRESS);
            case 1:
                return TicketsFragment.newInstance(ApiConstants.TicketStateFilter.DONE);
            case 2:
                return TicketsFragment.newInstance(ApiConstants.TicketStateFilter.PENDING);
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
