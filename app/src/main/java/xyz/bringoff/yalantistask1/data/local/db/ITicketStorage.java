package xyz.bringoff.yalantistask1.data.local.db;

import java.util.List;

import xyz.bringoff.yalantistask1.data.model.Ticket;

public interface ITicketStorage {

    void putTicket(Ticket ticket);

    void putTickets(List<Ticket> tickets);
}
