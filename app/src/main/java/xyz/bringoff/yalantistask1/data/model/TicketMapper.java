package xyz.bringoff.yalantistask1.data.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import xyz.bringoff.yalantistask1.data.remote.entity.AddressEntity;
import xyz.bringoff.yalantistask1.data.remote.entity.TicketEntity;

import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_ADDRESS;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_CREATING_DATE;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_DEADLINE_DATE;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_DESCRIPTION;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_IMAGE_NAMES;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_LIKES_COUNT;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_REGISTERING_DATE;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_RESPONSIBLE;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_STATUS;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable.COLUMN_TYPE;
import static xyz.bringoff.yalantistask1.data.local.db.DbScheme.TicketTable._ID;

public class TicketMapper {

    private static final String TAG = "TicketMapper";

    public static Ticket fromEntity(TicketEntity ticketEntity) {
        Ticket model = new Ticket();

        model.setId(ticketEntity.getId());
        model.setStatusName(ticketEntity.getState().getName());
        model.setType(ticketEntity.getType().getName());
        model.setDescription(ticketEntity.getBody());

        AddressEntity address = ticketEntity.getUserEntity().getAddressEntity();
        model.setAddress(String.format("%1$s, %2$s, %3$s",
                address.getCity().getName(), address.getStreet().getName(),
                address.getHouse().getName()));

        StringBuilder responsible = new StringBuilder();
        List<TicketEntity.Performer> performers = ticketEntity.getPerformers();
        for (int i = 0; i < performers.size(); i++) {
            TicketEntity.Performer performer = performers.get(i);
            responsible.append(performer.getOrganization());
            if (i < performers.size() - 1) {
                responsible.append(", ");
            }
        }
        model.setResponsible(responsible.toString());
        model.setCreatingDate(ticketEntity.getCreatedDate());
        model.setRegisteringDate(ticketEntity.getStartDate());
        model.setDeadlineDate(ticketEntity.getDeadline());
        model.setLikesCount(ticketEntity.getLikesCounter());
        return model;
    }

    public static Ticket fromCursor(Cursor cursor) {
        Ticket model = new Ticket();

        model.setId(cursor.getInt(cursor.getColumnIndex(_ID)));
        model.setStatusName(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)));
        model.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
        model.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
        model.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
        model.setResponsible(cursor.getString(cursor.getColumnIndex(COLUMN_RESPONSIBLE)));
        model.setCreatingDate(cursor.getLong(cursor.getColumnIndex(COLUMN_CREATING_DATE)));
        model.setRegisteringDate(cursor.getLong(cursor.getColumnIndex(COLUMN_REGISTERING_DATE)));
        model.setDeadlineDate(cursor.getLong(cursor.getColumnIndex(COLUMN_DEADLINE_DATE)));
        model.setLikesCount(cursor.getInt(cursor.getColumnIndex(COLUMN_LIKES_COUNT)));
        try {
            JSONArray json = new JSONObject(cursor.getString(
                    cursor.getColumnIndex(COLUMN_IMAGE_NAMES))).optJSONArray(COLUMN_IMAGE_NAMES);
            List<String> names = new ArrayList<>(json.length());
            for (int i = 0; i < json.length(); i++) {
                names.add(json.getString(i));
            }
            model.setImageNames(names);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            model.setImageNames(new ArrayList<String>());
        }

        return model;
    }

    public static ContentValues toContentValues(Ticket model) {
        ContentValues cv = new ContentValues();

        cv.put(_ID, model.getId());
        cv.put(COLUMN_STATUS, model.getStatusName());
        cv.put(COLUMN_TYPE, model.getType());
        cv.put(COLUMN_DESCRIPTION, model.getDescription());
        cv.put(COLUMN_ADDRESS, model.getAddress());
        cv.put(COLUMN_RESPONSIBLE, model.getResponsible());
        cv.put(COLUMN_CREATING_DATE, model.getCreatingDate());
        cv.put(COLUMN_REGISTERING_DATE, model.getRegisteringDate());
        cv.put(COLUMN_DEADLINE_DATE, model.getDeadlineDate());
        cv.put(COLUMN_LIKES_COUNT, model.getLikesCount());
        try {
            cv.put(COLUMN_IMAGE_NAMES, new JSONObject()
                    .put(COLUMN_IMAGE_NAMES, new JSONArray(model.getImageNames())).toString());
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            cv.put(COLUMN_IMAGE_NAMES, "");
        }
        return cv;
    }
}
