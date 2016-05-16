package xyz.bringoff.yalantistask1.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import xyz.bringoff.yalantistask1.R;

public class DetailsActivity extends AppCompatActivity {

    private static final String EXTRA_REQUEST_ID = "request_id";

    public static Intent getStartIntent(@NonNull Context context, @NonNull String requestId) {
        return new Intent(context, DetailsActivity.class).putExtra(EXTRA_REQUEST_ID, requestId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initToolbar();
        if (savedInstanceState == null) {
            initFragment(getIntent().getIntExtra(EXTRA_REQUEST_ID, 0));
        }
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initFragment(int ticketId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance(ticketId))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
