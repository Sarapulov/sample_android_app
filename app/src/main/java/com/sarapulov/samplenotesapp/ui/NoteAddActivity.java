package com.sarapulov.samplenotesapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.sarapulov.samplenotesapp.R;
import com.sarapulov.samplenotesapp.data.Note;
import com.sarapulov.samplenotesapp.data.NotesDataSource;

public class NoteAddActivity extends AppCompatActivity {

    private NotesDataSource notesDataSource;

    private EditText titleEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        notesDataSource = new NotesDataSource(this);

        titleEditText = (EditText) findViewById(R.id.activity_note_add_title);
        descriptionEditText = (EditText) findViewById(R.id.activity_note_add_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_note_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_note_add_done:
                saveNote();
                Toast.makeText(getApplicationContext(), "Note saved!", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                finish();
             default:
                 return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        Note note = new Note(title, description);

        notesDataSource.saveNote(note);

    }
}
