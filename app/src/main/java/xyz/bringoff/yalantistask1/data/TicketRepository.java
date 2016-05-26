package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;
import xyz.bringoff.yalantistask1.data.local.db.ITicketStorage;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.data.model.TicketMapper;
import xyz.bringoff.yalantistask1.data.remote.ApiConstants;
import xyz.bringoff.yalantistask1.data.remote.EContactApiService;
import xyz.bringoff.yalantistask1.data.remote.entity.TicketEntity;

public class TicketRepository implements ITicketRepository {

    private static final String TAG = "TicketRepository";

    private static volatile TicketRepository INSTANCE = null;

    private EContactApiService mApiService;
    private ITicketStorage mTicketStorage;
    private ITicketRepository mLocalTicketRepository;

    public TicketRepository(EContactApiService apiService, ITicketRepository localTicketRepository,
                            ITicketStorage ticketStorage) {
        mApiService = apiService;
        mLocalTicketRepository = localTicketRepository;
        mTicketStorage = ticketStorage;
    }

    public static TicketRepository getInstance(EContactApiService apiService,
                                               ITicketRepository localTicketRepository,
                                               ITicketStorage ticketStorage) {
        if (INSTANCE == null) {
            synchronized (TicketRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TicketRepository(apiService, localTicketRepository, ticketStorage);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<Ticket>> getTickets() {
        return getTickets(null);
    }

    @Override
    public Observable<List<Ticket>> getTickets(String ticketStatusIdName) {
        String apiTicketStatus = getApiStatus(ticketStatusIdName);

        return Observable.merge(mLocalTicketRepository.getTickets(ticketStatusIdName),
                mApiService.getTickets(apiTicketStatus).map(getTicketsMappingFunc())
                        .doOnNext(new Action1<List<Ticket>>() {
                                      @Override
                                      public void call(List<Ticket> tickets) {
                                          mTicketStorage.putTickets(tickets);
                                      }
                                  }
                        )).doOnError(logOnErrorAction());
    }

    @Nullable
    private String getApiStatus(String ticketStatus) {
        String apiTicketStatus = null;
        switch (ticketStatus) {
            case Ticket.STATUS_IN_PROGRESS:
                apiTicketStatus = ApiConstants.TicketStateFilter.IN_PROGRESS;
                break;
            case Ticket.STATUS_DONE:
                apiTicketStatus = ApiConstants.TicketStateFilter.DONE;
                break;
            case Ticket.STATUS_PENDING:
                apiTicketStatus = ApiConstants.TicketStateFilter.PENDING;
                break;
        }
        return apiTicketStatus;
    }

    @NonNull
    private Func1<List<TicketEntity>, List<Ticket>> getTicketsMappingFunc() {
        return new Func1<List<TicketEntity>, List<Ticket>>() {
            @Override
            public List<Ticket> call(List<TicketEntity> ticketEntities) {
                List<Ticket> tickets = new ArrayList<>();
                for (TicketEntity ticketEntity : ticketEntities) {
                    tickets.add(TicketMapper.fromEntity(ticketEntity));
                }
                return tickets;
            }
        };
    }

    @Override
    public Observable<Ticket> getTicket(int ticketId) {
        return mLocalTicketRepository.getTicket(ticketId);
    }

    @NonNull
    private Action1<Throwable> logOnErrorAction() {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                Log.e(TAG, throwable.getMessage(), throwable);
                throw Exceptions.propagate(throwable);
            }
        };
    }
}
