package xyz.bringoff.yalantistask1.requests;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.requests.adapter.RequestsPagerAdapter;

public class RequestListActivity extends AppCompatActivity {

    private ViewPager mRequestFragmentsViewPager;
    private RequestsPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.all_requests));
        setContentView(R.layout.activity_request_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRequestFragmentsViewPager = (ViewPager) findViewById(R.id.requests_view_pager);
        mPagerAdapter = new RequestsPagerAdapter(this, getSupportFragmentManager());
        mRequestFragmentsViewPager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.requests_tab_layout);
        mTabLayout.setupWithViewPager(mRequestFragmentsViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.requests_menu, menu);
        return true;
    }
}
