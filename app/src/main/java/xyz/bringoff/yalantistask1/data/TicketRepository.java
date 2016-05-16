package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import xyz.bringoff.yalantistask1.data.entity.Ticket;
import xyz.bringoff.yalantistask1.data.remote.EContactApiService;

public class TicketRepository implements ITicketRepository {

    private static final String TAG = "TicketRepository";

    private static TicketRepository INSTANCE = null;

    private EContactApiService mApiService;

    private Ticket mStubTicket;

    private TicketRepository() {
        mStubTicket = new Ticket();
        mStubTicket.setBody("body");
        mStubTicket.setCategory(new Ticket.Category(2, "Category"));
        mStubTicket.setCreatedDate(System.currentTimeMillis());
        mStubTicket.setStartDate(System.currentTimeMillis());
        mStubTicket.setDeadline(System.currentTimeMillis());

    }

    public static TicketRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (TicketRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TicketRepository();
                }
            }
        }
        return INSTANCE;
    }

    public EContactApiService getApiService() {
        return mApiService;
    }

    public void setApiService(EContactApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Observable<List<Ticket>> getTickets() {
        return getTickets(null);
    }

    @Override
    public Observable<List<Ticket>> getTickets(String ticketStatus) {
        if (ticketStatus == null) {
            return mApiService.getTickets().doOnError(logOnErrorAction());
        } else {
            return mApiService.getTickets(ticketStatus).doOnError(logOnErrorAction());
        }
    }

    @Override
    public Observable<Ticket> getTicket(int ticketId) {
        return Observable.just(mStubTicket);
    }

    @NonNull
    private Action1<Throwable> logOnErrorAction() {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, throwable.getMessage(), throwable);
            }
        };
    }
}
