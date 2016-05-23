package xyz.bringoff.yalantistask1.data.local.db;

import android.provider.BaseColumns;

public final class DbScheme {

    public static class TicketTable implements BaseColumns {

        public static final String TABLE_NAME = "tickets";

        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_DESCRIPTION = "desc";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_RESPONSIBLE = "responsible";
        public static final String COLUMN_CREATING_DATE = "created";
        public static final String COLUMN_REGISTERING_DATE = "registered";
        public static final String COLUMN_DEADLINE_DATE = "deadline";
        public static final String COLUMN_LATITUDE = "lat";
        public static final String COLUMN_LONGITUDE = "lng";
        public static final String COLUMN_LIKES_COUNT = "likes";
        public static final String COLUMN_IMAGE_NAMES = "image_names";

        public static final String[] ALL_COLUMNS = {
                _ID,
                COLUMN_STATUS,
                COLUMN_TYPE,
                COLUMN_DESCRIPTION,
                COLUMN_ADDRESS,
                COLUMN_RESPONSIBLE,
                COLUMN_CREATING_DATE,
                COLUMN_REGISTERING_DATE,
                COLUMN_DEADLINE_DATE,
                COLUMN_LATITUDE,
                COLUMN_LONGITUDE,
                COLUMN_LIKES_COUNT,
                COLUMN_IMAGE_NAMES
        };

        public static final String CREATE_TABLE = String.format(
                "create table if not exists %s (%s primary key, %s text, %s text, %s text," +
                        " %s text, %s text, %s integer, %s integer, %s integer, %s real," +
                        " %s real, %s integer, %s text);",
                TABLE_NAME, _ID, COLUMN_STATUS, COLUMN_TYPE, COLUMN_DESCRIPTION, COLUMN_ADDRESS,
                COLUMN_RESPONSIBLE, COLUMN_CREATING_DATE, COLUMN_REGISTERING_DATE,
                COLUMN_DEADLINE_DATE, COLUMN_LATITUDE, COLUMN_LONGITUDE, COLUMN_LIKES_COUNT,
                COLUMN_IMAGE_NAMES);
    }
}
