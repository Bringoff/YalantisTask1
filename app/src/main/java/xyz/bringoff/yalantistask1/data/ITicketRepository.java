package xyz.bringoff.yalantistask1.data;

import java.util.List;

import rx.Observable;
import xyz.bringoff.yalantistask1.data.model.Ticket;

public interface ITicketRepository {

    Observable<Ticket> getTicket(int ticketId);

    Observable<List<Ticket>> getTickets();

    Observable<List<Ticket>> getTickets(String ticketStatus);
}
