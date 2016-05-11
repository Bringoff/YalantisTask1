package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import xyz.bringoff.yalantistask1.data.entity.Ticket;
import xyz.bringoff.yalantistask1.data.remote.EContactApiService;

public class TicketRepository implements ITicketRepository {

    private static TicketRepository INSTANCE = null;

    private EContactApiService mApiService;

    private TicketRepository() {
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
    public Observable<List<Ticket>> getTickets(String requestStatus) {
        if (requestStatus == null) {
            return mApiService.getTickets();
        } else {
            return mApiService
                    .getTickets(requestStatus);
        }
    }

    @Override
    public Observable<Ticket> getTicket(@NonNull String ticketId) {
        return null;
    }

}
