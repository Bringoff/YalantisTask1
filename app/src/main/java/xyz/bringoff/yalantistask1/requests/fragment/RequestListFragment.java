package xyz.bringoff.yalantistask1.requests.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import xyz.bringoff.yalantistask1.Injection;
import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.Request;
import xyz.bringoff.yalantistask1.data.RequestDataSourceInterface;
import xyz.bringoff.yalantistask1.details.DetailsActivity;
import xyz.bringoff.yalantistask1.requests.RequestListActivity;
import xyz.bringoff.yalantistask1.requests.adapter.RequestListAdapter;

public class RequestListFragment extends BaseRequestListFragment
        implements AdapterView.OnItemClickListener, RequestDataSourceInterface.LoadRequestsCallback {

    private RequestDataSourceInterface mDataSource;
    private ListView mRequestListView;
    private RequestListAdapter mAdapter;
    private FloatingActionButton mFab;

    public RequestListFragment() {
        // Required empty public constructor
    }

    public static RequestListFragment newInstance() {
        return new RequestListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataSource = Injection.provideRequestDataSource();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_list, container, false);
        mRequestListView = (ListView) view.findViewById(R.id.request_list_view);
        mRequestListView.setOnItemClickListener(this);
        mAdapter = new RequestListAdapter(getActivity());
        mRequestListView.setAdapter(mAdapter);
        ViewCompat.setNestedScrollingEnabled(mRequestListView, true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mFab != null) {
            mFab.attachToListView(mRequestListView);
        }
        mDataSource.getRequests(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof RequestListActivity) {
            mFab = ((RequestListActivity) getActivity()).getFab();
        }
    }

    @Override
    protected void notifyAdapterAboutRequests() {
        mAdapter.setRequests(requests);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(DetailsActivity.getStartIntent(getActivity(), requests.get(position).getId()));
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
