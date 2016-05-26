package xyz.bringoff.yalantistask1.ui.tickets;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.model.Ticket;

public class TicketsPresenter implements TicketsMVP.Presenter {

    private static final String TAG = "TicketsPresenter";

    private WeakReference<TicketsMVP.View> mView;
    private CompositeSubscription mCompositeSubscription;
    private ITicketRepository mTicketRepository;
    private String mTicketsStatusIdName;

    private List<Ticket> mTickets;
    private int mPage = 0;

    public TicketsPresenter(ITicketRepository ticketRepository, String ticketsStatusIdName) {
        mTicketsStatusIdName = ticketsStatusIdName;
        mCompositeSubscription = new CompositeSubscription();
        mTicketRepository = ticketRepository;

        mTickets = new ArrayList<>();
    }

    public TicketsMVP.View getView() {
        return mView.get();
    }

    @Override
    public void bindView(TicketsMVP.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onShowTickets() {
        getView().showProgress();
        if (mTickets == null || mTickets.isEmpty()) {
            loadTickets();
        } else {
            showTickets();
        }
    }

    @Override
    public void onShowMore() {
        mPage++;
        getView().showProgress();
        loadTickets();
    }

    @Override
    public void onRefresh() {
        mPage = 0;
        mTickets.clear();
        loadTickets();
    }

    private void showTickets() {
        if (getView() == null) {
            return;
        }
        getView().hideProgress();
        getView().showTickets(mTickets);
    }

    private void loadTickets() {
        mCompositeSubscription.add(mTicketRepository.getTickets(mTicketsStatusIdName, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Ticket>>() {
                    @Override
                    public void onCompleted() {
                        getView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(List<Ticket> tickets) {
                        for (Ticket ticket : tickets) {
                            if (!mTickets.contains(ticket)) {
                                mTickets.add(ticket);
                            }
                        }
                        showTickets();
                    }
                }));
    }

    @Override
    public void onShowTicketDetails(int ticketPosition) {
        Ticket ticket = mTickets.get(ticketPosition);
        getView().showDetails(ticket.getId());
    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onDetach() {
        mCompositeSubscription.clear();
    }
}
