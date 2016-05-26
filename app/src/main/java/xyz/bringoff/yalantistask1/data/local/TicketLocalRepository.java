package xyz.bringoff.yalantistask1.data.local;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.local.db.DatabaseHelper;
import xyz.bringoff.yalantistask1.data.local.db.DatabaseScheme.TicketTable;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.data.model.TicketMapper;

public class TicketLocalRepository implements ITicketRepository {

    private final BriteDatabase mDatabase;

    public TicketLocalRepository(DatabaseHelper openHelper) {
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabase = sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }

    @Override
    public Observable<Ticket> getTicket(int ticketId) {
        return mDatabase.createQuery(TicketTable.TABLE_NAME,
                "SELECT * FROM " + TicketTable.TABLE_NAME +
                        " WHERE " + TicketTable._ID + " = " + ticketId)
                .mapToOne(new Func1<Cursor, Ticket>() {
                    @Override
                    public Ticket call(Cursor cursor) {
                        return TicketMapper.fromCursor(cursor);
                    }
                });
    }

    @Override
    public Observable<List<Ticket>> getTickets() {
        return getTicketsRaw("SELECT * FROM " + TicketTable.TABLE_NAME);
    }

    @Override
    public Observable<List<Ticket>> getTickets(String ticketStatusIdName) {
        if (ticketStatusIdName == null) {
            return getTickets();
        }
        return getTicketsRaw("SELECT * FROM " + TicketTable.TABLE_NAME +
                " WHERE " + TicketTable.COLUMN_STATUS_ID_NAME + " = '" + ticketStatusIdName + "'");
    }

    private Observable<List<Ticket>> getTicketsRaw(String query) {
        return mDatabase.createQuery(TicketTable.TABLE_NAME, query)
                .mapToList(new Func1<Cursor, Ticket>() {
                    @Override
                    public Ticket call(Cursor cursor) {
                        return TicketMapper.fromCursor(cursor);
                    }
                });

    }
}
