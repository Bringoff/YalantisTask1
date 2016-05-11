package xyz.bringoff.yalantistask1.ui.requests.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.bringoff.yalantistask1.Injection;
import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.entity.Ticket;
import xyz.bringoff.yalantistask1.ui.base.BaseFragment;
import xyz.bringoff.yalantistask1.ui.details.DetailsActivity;
import xyz.bringoff.yalantistask1.ui.requests.adapter.OnItemClickListener;
import xyz.bringoff.yalantistask1.ui.requests.adapter.RequestRecyclerAdapter;

public class RequestsFragment extends BaseFragment implements OnItemClickListener {

    private static final String TAG = "RequestsFragment";

    private static final String KEY_STATUS = "status";

    private ITicketRepository mDataSource;
    private RecyclerView mRequestsRecyclerView;
    private RequestRecyclerAdapter mAdapter;

    private String mStatus;
    private List<Ticket> mTickets;

    public RequestsFragment() {
    }

    public static RequestsFragment newInstance(String ticketsStatus) {
        Bundle args = new Bundle();
        args.putString(KEY_STATUS, ticketsStatus);
        RequestsFragment fragment = new RequestsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataSource = Injection.provideRequestRepository();
        mStatus = getArguments().getString(KEY_STATUS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_request_list, container, false);
        mRequestsRecyclerView = (RecyclerView) view.findViewById(R.id.request_recycler_view);
        mRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RequestRecyclerAdapter(getActivity(), this);
        mRequestsRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCompositeSubscription().add(mDataSource.getTickets(mStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Ticket>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);

                        Toast.makeText(RequestsFragment.this.getActivity(),
                                e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<Ticket> tickets) {
                        setTickets(tickets);
                    }
                }));
    }

    public void setTickets(List<Ticket> tickets) {
        this.mTickets = tickets;
        notifyAdapterAboutRequests();
    }

    private void notifyAdapterAboutRequests() {
        mAdapter.setTickets(mTickets);
    }

    @Override
    public void onItemClicked(int position) {
        Ticket ticket = mTickets.get(position);
        startActivity(DetailsActivity.getStartIntent(getActivity(), ticket.getTicketId()));
    }
}
