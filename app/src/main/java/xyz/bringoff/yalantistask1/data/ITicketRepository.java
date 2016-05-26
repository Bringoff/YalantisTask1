package xyz.bringoff.yalantistask1.data;

import java.util.List;

import rx.Observable;
import xyz.bringoff.yalantistask1.data.model.Ticket;

public interface ITicketRepository {

    int PAGE_SIZE = 20;

    Observable<Ticket> getTicket(int ticketId);

    Observable<List<Ticket>> getTickets(int page);

    Observable<List<Ticket>> getTickets(String ticketStatusIdName, int page);
}
