package xyz.bringoff.yalantistask1.ui.tickets;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.ui.tickets.adapter.TicketsPagerAdapter;

public class TicketsActivity extends AppCompatActivity {

    private ViewPager mTicketsFragmentsViewPager;
    private TicketsPagerAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.all_tickets));
        setContentView(R.layout.activity_ticket_list);

        initViews();
    }

    private void initViews() {
        initToolbar();

        mFab = (FloatingActionButton) findViewById(R.id.fab);

        initDrawer();

        mTicketsFragmentsViewPager = (ViewPager) findViewById(R.id.tickets_view_pager);
        mPagerAdapter = new TicketsPagerAdapter(this, getSupportFragmentManager());
        mTicketsFragmentsViewPager.setAdapter(mPagerAdapter);
        mTabLayout = (TabLayout) findViewById(R.id.tickets_tab_layout);
        mTabLayout.setupWithViewPager(mTicketsFragmentsViewPager);

        TextView authorsTextView = (TextView) findViewById(R.id.authors_text_view);
        authorsTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_home);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tickets_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
