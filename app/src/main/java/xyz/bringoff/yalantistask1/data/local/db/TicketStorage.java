package xyz.bringoff.yalantistask1.data.local.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import rx.schedulers.Schedulers;
import xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable;
import xyz.bringoff.yalantistask1.data.model.Ticket;
import xyz.bringoff.yalantistask1.data.model.TicketMapper;

public class TicketStorage implements ITicketStorage {

    private final BriteDatabase mDatabase;

    public TicketStorage(DbHelper openHelper) {
        SqlBrite sqlBrite = SqlBrite.create();
        mDatabase = sqlBrite.wrapDatabaseHelper(openHelper, Schedulers.io());
    }

    @Override
    public void putTicket(Ticket ticket) {
        ContentValues ticketValues = TicketMapper.toContentValues(ticket);
        mDatabase.insert(TicketTable.TABLE_NAME, ticketValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void putTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            putTicket(ticket);
        }
    }
}
