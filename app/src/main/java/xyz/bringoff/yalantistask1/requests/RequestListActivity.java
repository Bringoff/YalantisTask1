package xyz.bringoff.yalantistask1.requests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.requests.fragment.RequestListFragment;

public class RequestListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_list);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, RequestListFragment.newInstance())
                .commit();
    }
}
