package xyz.bringoff.yalantistask1.ui.details;

import android.util.Log;

import java.lang.ref.WeakReference;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.model.Ticket;

public class DetailsPresenter implements DetailsMVP.Presenter {

    private static final String LOG_TAG = "DetailsPresenter";

    private final ITicketRepository mTicketRepository;
    private WeakReference<DetailsMVP.View> mView;
    private CompositeSubscription mCompositeSubscription;
    private int mTicketId;
    private Ticket mTicket;

    public DetailsPresenter(ITicketRepository ticketRepository, int ticketId) {
        mTicketRepository = ticketRepository;
        mCompositeSubscription = new CompositeSubscription();
        mTicketId = ticketId;
    }

    private DetailsMVP.View getView() {
        return mView.get();
    }

    @Override
    public void bindView(DetailsMVP.View view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void onShowTicket() {
        subscribeForTicket();
    }

    private void subscribeForTicket() {
        mCompositeSubscription.add(mTicketRepository.getTicket(mTicketId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Ticket>() {
                    @Override
                    public void call(Ticket ticket) {
                        mTicket = ticket;
                        getView().showTicket(mTicket);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(LOG_TAG, throwable.getMessage(), throwable);
                        getView().showError(throwable);
                    }
                }));
    }

    @Override
    public void onAttach() {
    }

    @Override
    public void onDetach() {
        mCompositeSubscription.clear();
    }
}
