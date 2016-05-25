package xyz.bringoff.yalantistask1.ui.tickets;

import java.lang.ref.WeakReference;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.model.Ticket;

public class TicketsPresenter implements TicketsMVP.Presenter {

    private WeakReference<TicketsMVP.View> mView;
    private CompositeSubscription mCompositeSubscription;
    private ITicketRepository mTicketRepository;
    private String mTicketsStatus;

    private List<Ticket> mTickets;

    public TicketsPresenter(ITicketRepository ticketRepository, String ticketsStatus) {
        mTicketsStatus = ticketsStatus;
        mCompositeSubscription = new CompositeSubscription();
        mTicketRepository = ticketRepository;
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
        if (getView() == null) {
            return;
        }
        getView().hideProgress();
        getView().showTickets(mTickets);
    }

    private void loadTickets() {
        mCompositeSubscription.add(mTicketRepository.getTickets(mTicketsStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Ticket>>() {
                    @Override
                    public void onCompleted() {
                        getView().hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(List<Ticket> tickets) {
                        mTickets = tickets;
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
