package xyz.bringoff.yalantistask1.data.local;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import xyz.bringoff.yalantistask1.data.ITicketRepository;
import xyz.bringoff.yalantistask1.data.local.db.DbOpenHelper;
import xyz.bringoff.yalantistask1.data.local.db.DbScheme;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.data.model.TicketMapper;

public class TicketLocalRepository implements ITicketRepository {

    private final BriteDatabase mDatabase;

    public TicketLocalRepository(DbOpenHelper openHelper) {
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabase = sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }

    @Override
    public Observable<Ticket> getTicket(int ticketId) {
        return mDatabase.createQuery(DbScheme.TicketTable.TABLE_NAME,
                "SELECT * FROM " + DbScheme.TicketTable.TABLE_NAME +
                        " WHERE" + DbScheme.TicketTable._ID + " = " + ticketId)
                .mapToOne(new Func1<Cursor, Ticket>() {
                    @Override
                    public Ticket call(Cursor cursor) {
                        return TicketMapper.fromCursor(cursor);
                    }
                });
    }

    @Override
    public Observable<List<Ticket>> getTickets() {
        return getTicketsRaw("SELECT * FROM " + DbScheme.TicketTable.TABLE_NAME);
    }

    @Override
    public Observable<List<Ticket>> getTickets(String ticketStatus) {
        return getTicketsRaw("SELECT * FROM " + DbScheme.TicketTable.TABLE_NAME +
                " WHERE" + DbScheme.TicketTable.COLUMN_STATUS + " = " + ticketStatus);
    }

    private Observable<List<Ticket>> getTicketsRaw(String query) {
        return mDatabase.createQuery(DbScheme.TicketTable.TABLE_NAME, query)
                .mapToList(new Func1<Cursor, Ticket>() {
                    @Override
                    public Ticket call(Cursor cursor) {
                        return TicketMapper.fromCursor(cursor);
                    }
                });
    }
}
