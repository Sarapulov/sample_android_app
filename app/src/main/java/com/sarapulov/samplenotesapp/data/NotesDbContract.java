package com.sarapulov.samplenotesapp.data;

import android.provider.BaseColumns;

public class NotesDbContract {

    private NotesDbContract() {

    }

    public static final class NotesEntry implements BaseColumns {

        public static final String TABLE_NAME = "note";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";

    }
}
