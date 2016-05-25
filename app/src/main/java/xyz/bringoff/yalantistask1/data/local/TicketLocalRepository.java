package xyz.bringoff.yalantistask1.data.local;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.local.db.DbHelper;
import xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.data.model.TicketMapper;

public class TicketLocalRepository implements ITicketRepository {

    private final BriteDatabase mDatabase;

    public TicketLocalRepository(DbHelper openHelper) {
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabase = sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }

    @Override
    public Observable<Ticket> getTicket(int ticketId) {
        return mDatabase.createQuery(TicketTable.TABLE_NAME,
                "SELECT * FROM " + TicketTable.TABLE_NAME +
                        " WHERE " + TicketTable._ID + " = " + ticketId + ";")
                .mapToOne(new Func1<Cursor, Ticket>() {
                    @Override
                    public Ticket call(Cursor cursor) {
                        return TicketMapper.fromCursor(cursor);
                    }
                });
    }

    @Override
    public Observable<List<Ticket>> getTickets() {
        return getTicketsRaw("SELECT * FROM " + TicketTable.TABLE_NAME + ";");
    }

    @Override
    public Observable<List<Ticket>> getTickets(String ticketStatus) {
        if (ticketStatus == null) {
            return getTickets();
        }
        return getTicketsRaw("SELECT * FROM " + TicketTable.TABLE_NAME +
                " WHERE " + TicketTable.COLUMN_STATUS + " = '" + ticketStatus + "';");
    }

    private Observable<List<Ticket>> getTicketsRaw(String query) {
        return mDatabase.createQuery(TicketTable.TABLE_NAME, query)
                .toSortedList().flatMap(new Func1<List<SqlBrite.Query>, Observable<List<Ticket>>>() {
                    @Override
                    public Observable<List<Ticket>> call(List<SqlBrite.Query> queries) {
                        List<Ticket> tickets = new ArrayList<>();
                        for (SqlBrite.Query query : queries) {
                            tickets.add(TicketMapper.fromCursor(query.run()));
                        }
                        return Observable.just(tickets);
                    }
                });

    }
}
