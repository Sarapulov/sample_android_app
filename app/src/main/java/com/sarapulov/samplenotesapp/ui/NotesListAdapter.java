package com.sarapulov.samplenotesapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sarapulov.samplenotesapp.R;
import com.sarapulov.samplenotesapp.data.Note;

import java.util.List;


public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.VHNoteItem> {

    private List<Note> noteList;
    private OnNoteSelectedListener onNoteSelectedListener;

    public NotesListAdapter(List<Note> noteList, OnNoteSelectedListener onNoteSelectedListener) {
        this.noteList = noteList;
        this.onNoteSelectedListener = onNoteSelectedListener;
    }

    @Override
    public VHNoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHNoteItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(VHNoteItem holder, int position) {
        final Note note = noteList.get(position);

        holder.titleTextView.setText(note.getTitle());
        holder.descriptionTextView.setText(note.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoteSelectedListener.onNoteSelected(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    static class VHNoteItem extends RecyclerView.ViewHolder {

        TextView descriptionTextView;
        TextView titleTextView;

        VHNoteItem(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.notes_list_row_title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.notes_list_row_description);
        }

    }
}
