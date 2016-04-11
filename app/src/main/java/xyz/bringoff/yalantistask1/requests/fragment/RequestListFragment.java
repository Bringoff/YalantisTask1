package xyz.bringoff.yalantistask1.requests.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.details.DetailsActivity;
import xyz.bringoff.yalantistask1.requests.adapter.RequestListAdapter;
import xyz.bringoff.yalantistask1.utils.DummyDataSources;

public class RequestListFragment extends BaseRequestListFragment implements AdapterView.OnItemClickListener {

    private ListView mRequestListView;
    private RequestListAdapter mAdapter;

    public RequestListFragment() {
        // Required empty public constructor
    }

    public static RequestListFragment newInstance() {
        return new RequestListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_list, container, false);
        mRequestListView = (ListView) view.findViewById(R.id.request_list_view);
        mRequestListView.setOnItemClickListener(this);
        mAdapter = new RequestListAdapter(getActivity());
        setRequests(DummyDataSources.getDummyRequests());
        mRequestListView.setAdapter(mAdapter);
        ViewCompat.setNestedScrollingEnabled(mRequestListView, true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void notifyAdapterAboutRequests() {
        mAdapter.setRequests(requests);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(DetailsActivity.getStartIntent(getActivity()));
    }
}
