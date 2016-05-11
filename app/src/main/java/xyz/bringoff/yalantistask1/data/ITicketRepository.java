package xyz.bringoff.yalantistask1.data;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import xyz.bringoff.yalantistask1.data.entity.Ticket;

public interface ITicketRepository {

    Observable<List<Ticket>> getTickets();

    Observable<List<Ticket>> getTickets(String requestStatus);

    Observable<Ticket> getTicket(@NonNull String ticketId);
}
