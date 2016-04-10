package xyz.bringoff.yalantistask1.requests.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.requests.adapter.RequestRecyclerAdapter;
import xyz.bringoff.yalantistask1.utils.DummyDataSources;

public class RequestRecyclerFragment extends BaseRequestListFragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_request_list, container, false);
        mRequestsRecyclerView = (RecyclerView) view.findViewById(R.id.request_recycler_view);
        mRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RequestRecyclerAdapter(getActivity());
        mRequestsRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setRequests(DummyDataSources.getDummyRequests());
    }

    @Override
    protected void notifyAdapterAboutRequests() {
        mAdapter.setRequests(requests);
    }
}
