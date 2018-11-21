package com.sarapulov.samplenotesapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NotesDataSource {

    private static TreeMap<Long, Note> noteMap = new TreeMap<>();

    private NotesDbHelper notesDbHelper;

    public NotesDataSource(Context context) {
        notesDbHelper = new NotesDbHelper(context);
    }

    public void saveNote(Note note) {
        SQLiteDatabase db = notesDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesDbContract.NotesEntry.COLUMN_NAME_TITLE, note.getTitle());
        values.put(NotesDbContract.NotesEntry.COLUMN_NAME_DESCRIPTION, note.getDescription());

        db.insert(NotesDbContract.NotesEntry.TABLE_NAME, null, values);
        db.close();
    }

    public List<Note> getNotes() {
        List<Note> notes = new ArrayList<>();

        SQLiteDatabase db = notesDbHelper.getReadableDatabase();

        String[] projection = {
                NotesDbContract.NotesEntry._ID,
                NotesDbContract.NotesEntry.COLUMN_NAME_TITLE,
                NotesDbContract.NotesEntry.COLUMN_NAME_DESCRIPTION,
        };

        Cursor c = db.query(NotesDbContract.NotesEntry.TABLE_NAME, projection, null, null, null, null, "_ID DESC");

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                Long id = c.getLong(c.getColumnIndexOrThrow(NotesDbContract.NotesEntry._ID));
                String title = c.getString(c.getColumnIndexOrThrow(NotesDbContract.NotesEntry.COLUMN_NAME_TITLE));
                String description = c.getString(c.getColumnIndexOrThrow(NotesDbContract.NotesEntry.COLUMN_NAME_DESCRIPTION));

                Note note = new Note(id, title, description);
                notes.add(note);
            }
        }

        if (c != null) {
            c.close();
        }

        db.close();

        return notes;
    }

    public void deleteNote(long id) {
        SQLiteDatabase db = notesDbHelper.getWritableDatabase();

        String selection = NotesDbContract.NotesEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        db.delete(NotesDbContract.NotesEntry.TABLE_NAME, selection, selectionArgs);

        db.close();
    }
}
