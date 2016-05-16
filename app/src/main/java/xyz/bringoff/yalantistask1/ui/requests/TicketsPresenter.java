package xyz.bringoff.yalantistask1.ui.requests;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import xyz.bringoff.yalantistask1.Injection;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.entity.Ticket;

public class TicketsPresenter implements TicketsMVP.Presenter {

    private WeakReference<TicketsMVP.View> mView;
    private CompositeSubscription mCompositeSubscription;
    private ITicketRepository mTicketRepository;
    private String mTicketsStatus;

    private List<Ticket> mTickets;

    public TicketsPresenter(String ticketsStatus) {
        mTicketsStatus = ticketsStatus;
        mCompositeSubscription = new CompositeSubscription();
        mTicketRepository = Injection.provideTicketRepository();
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

    private void showTickets() {
        getView().hideProgress();
        getView().showTickets(mTickets);
    }

    private void loadTickets() {
        mCompositeSubscription.add(mTicketRepository.getTickets(mTicketsStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Ticket>>() {
                    @Override
                    public void call(List<Ticket> tickets) {
                        mTickets = tickets;
                        showTickets();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getView().showError(throwable);
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
