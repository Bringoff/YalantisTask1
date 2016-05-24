package xyz.bringoff.yalantistask1.ui.tickets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import xyz.bringoff.yalantistask1.Injection;
import xyz.bringoff.yalantistask1.PresenterHolder;
import xyz.bringoff.yalantistask1.R;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.ui.base.BaseFragment;
import xyz.bringoff.yalantistask1.ui.details.DetailsActivity;
import xyz.bringoff.yalantistask1.ui.tickets.adapter.OnItemClickListener;
import xyz.bringoff.yalantistask1.ui.tickets.adapter.TicketRecyclerAdapter;
import xyz.bringoff.yalantistask1.utils.popup.PopupInformer;

public class TicketsFragment extends BaseFragment
        implements TicketsMVP.View, OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String KEY_STATUS = "status";

    @BindView(R.id.progress)
    ProgressBar mProgressBar;
    @BindView(R.id.tickets_recycler_view)
    RecyclerView mTicketsRecyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout mRefreshLayout;
    private TicketRecyclerAdapter mAdapter;

    private PopupInformer mPopupInformer;

    private TicketsMVP.Presenter mPresenter;
    private PresenterHolder mPresenterHolder;

    public TicketsFragment() {
    }

    public static TicketsFragment newInstance(String ticketsStatus) {
        Bundle args = new Bundle();
        args.putString(KEY_STATUS, ticketsStatus);
        TicketsFragment fragment = new TicketsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getString(KEY_STATUS) == null) {
            throw new IllegalArgumentException(
                    "This fragment requires tickets status as one of its arguments");
        }

        mPresenterHolder = Injection.providePresenterHolder();

        mPopupInformer = Injection.provideSnackbarInformer();

        if (savedInstanceState != null) {
            mPresenter = mPresenterHolder.getPresenter(TicketsMVP.Presenter.class);
        }
        if (mPresenter == null) {
            mPresenter = new TicketsPresenter(Injection.provideTicketRepository(getContext()),
                    getArguments().getString(KEY_STATUS));
        }
        mPresenter.bindView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mTicketsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new TicketRecyclerAdapter(getActivity(), this);
        mTicketsRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ticket_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.onAttach();
        mPresenter.onShowTickets();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onDetach();
    }

    @Override
    public void onItemClicked(int position) {
        mPresenter.onShowTicketDetails(position);
    }

    @Override
    public void showTickets(List<Ticket> tickets) {
        mAdapter.setTickets(tickets);
    }

    @Override
    public void showDetails(int ticketId) {
        startActivity(DetailsActivity.getStartIntent(getActivity(), ticketId));
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(Throwable errorCause) {
        hideProgress();
        mPopupInformer.showLongPopup(getView(), errorCause.getLocalizedMessage());
    }

    @Override
    public void onRefresh() {
        mPresenter.onShowTickets();
    }
}
