package xyz.bringoff.yalantistask1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mImageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        mImageUrls = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.image_urls)));

        initViews();
    }

    private void initToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.occasion_code_stub));
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.images_recycler_view);
        recyclerView.addItemDecoration(
                new HorizontalSpaceItemDecoration((int) getResources().getDimension(R.dimen.default_vertical_margin)));
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(lm);
        ImagesRecyclerAdapter adapter = new ImagesRecyclerAdapter(this, mImageUrls);
        recyclerView.setAdapter(adapter);
    }

    public void onEveryClick(View view) {
        showToast(view.getClass().getSimpleName());
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
