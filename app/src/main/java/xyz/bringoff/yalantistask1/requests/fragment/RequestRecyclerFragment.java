package xyz.bringoff.yalantistask1.requests.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.bringoff.yalantistask1.Injection;
import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.Request;
import xyz.bringoff.yalantistask1.data.RequestDataSource;
import xyz.bringoff.yalantistask1.details.DetailsActivity;
import xyz.bringoff.yalantistask1.requests.adapter.OnItemClickListener;
import xyz.bringoff.yalantistask1.requests.adapter.RequestRecyclerAdapter;

public class RequestRecyclerFragment extends BaseRequestListFragment
        implements OnItemClickListener, RequestDataSource.LoadRequestsCallback {

    private RequestDataSource mDataSource;
    private RecyclerView mRequestsRecyclerView;
    private RequestRecyclerAdapter mAdapter;

    public RequestRecyclerFragment() {
        // Required empty public constructor
    }

    public static RequestRecyclerFragment newInstance() {
        return new RequestRecyclerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataSource = Injection.provideRequestDataSource();
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

        mDataSource.getRequests(this);
    }

    @Override
    protected void notifyAdapterAboutRequests() {
        mAdapter.setRequests(requests);
    }

    @Override
    public void onItemClicked(int position) {
        Request request = requests.get(position);
        startActivity(DetailsActivity.getStartIntent(getActivity(), request.getId()));
    }

    @Override
    public void onRequestsLoaded(List<Request> requests) {
        setRequests(requests);
    }

    @Override
    public void onDataNotAvailable() {
        // TODO: 11.04.2016 show error notification
    }
}
