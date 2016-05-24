package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import xyz.bringoff.yalantistask1.data.local.db.ITicketStorage;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.data.model.TicketMapper;
import xyz.bringoff.yalantistask1.data.remote.EContactApiService;
import xyz.bringoff.yalantistask1.data.remote.entity.TicketEntity;

public class TicketRepository implements ITicketRepository {

    private static final String TAG = "TicketRepository";

    private static TicketRepository INSTANCE = null;

    private EContactApiService mApiService;
    private ITicketStorage mTicketStorage;

    private Ticket mStubTicket;

    private TicketRepository(EContactApiService apiService, ITicketStorage ticketStorage) {
        mApiService = apiService;
        mTicketStorage = ticketStorage;

        mStubTicket = new Ticket();
        mStubTicket.setId(123);
        mStubTicket.setCreatingDate(System.currentTimeMillis() / 1000);
        mStubTicket.setRegisteringDate(System.currentTimeMillis() / 1000);
        mStubTicket.setDeadlineDate(System.currentTimeMillis() / 1000);
        mStubTicket.setStatus("Status");
        mStubTicket.setDescription("body");
    }

    public static TicketRepository getInstance(EContactApiService apiService, ITicketStorage ticketStorage) {
        if (INSTANCE == null) {
            synchronized (TicketRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TicketRepository(apiService, ticketStorage);
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
    public Observable<List<Ticket>> getTickets(String ticketStatus) {
        if (ticketStatus == null) {
            return mApiService.getTickets().map(getTicketMappingFunc()).doOnError(logOnErrorAction());
        } else {
            return mApiService.getTickets(ticketStatus).map(getTicketMappingFunc())
                    .doOnError(logOnErrorAction());
        }
    }

    @NonNull
    private Func1<List<TicketEntity>, List<Ticket>> getTicketMappingFunc() {
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
