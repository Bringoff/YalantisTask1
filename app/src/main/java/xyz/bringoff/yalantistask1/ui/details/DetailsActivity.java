package xyz.bringoff.yalantistask1.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import xyz.bringoff.yalantistask1.Injection;
import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.entity.Ticket;
import xyz.bringoff.yalantistask1.utils.DateUtils;
import xyz.bringoff.yalantistask1.utils.recyclerutils.HorizontalSpaceItemDecoration;
import xyz.bringoff.yalantistask1.view.CaptionValueView;

public class DetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = "DetailsActivity";

    private static final String EXTRA_REQUEST_ID = "request_id";

    private Ticket mTicket;

    private TextView mStatusTextView;
    private CaptionValueView mCreatedView;
    private CaptionValueView mRegisteredView;
    private CaptionValueView mSolveToView;
    private CaptionValueView mResponsibleView;
    private TextView mDescriptionTextView;
    private TextView mTypeTextView;

    public static Intent getStartIntent(@NonNull Context context, @NonNull String requestId) {
        return new Intent(context, DetailsActivity.class).putExtra(EXTRA_REQUEST_ID, requestId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initToolbar();

        initViews();

        ITicketRepository dataSource = Injection.provideRequestRepository();
        if (getIntent() != null) {
            final String id = getIntent().getStringExtra(EXTRA_REQUEST_ID);
            dataSource.getTicket(id);
        }
    }

    private void bindRequest() {
        mTypeTextView.setText(mTicket.getType().getName());
        mStatusTextView.setText(mTicket.getState().getName());
        mCreatedView.setValue(DateUtils.unixToMediumDateString(this, mTicket.getCreatedDate()));
        mRegisteredView.setValue(DateUtils.unixToMediumDateString(this, mTicket.getStartDate()));
        mSolveToView.setValue(DateUtils.unixToMediumDateString(this, mTicket.getDeadline()));
        mDescriptionTextView.setText(mTicket.getBody());
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
        /*ImagesRecyclerAdapter adapter = new ImagesRecyclerAdapter(this, mTicket.getFiles());
        recyclerView.setAdapter(adapter);*/

        mTypeTextView = (TextView) findViewById(R.id.type_text_view);
        mStatusTextView = (TextView) findViewById(R.id.status_text_view);
        mCreatedView = (CaptionValueView) findViewById(R.id.created_captionvalue_view);
        mRegisteredView = (CaptionValueView) findViewById(R.id.registered_captionvalue_view);
        mSolveToView = (CaptionValueView) findViewById(R.id.solve_to_captionvalue_view);
        mResponsibleView = (CaptionValueView) findViewById(R.id.responsible_captionvalue_view);
        mDescriptionTextView = (TextView) findViewById(R.id.detailed_description_text_view);

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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
