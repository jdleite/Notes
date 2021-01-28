package br.com.notes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;

import java.util.List;

import br.com.notes.model.Note;
import br.com.notes.ui.listener.NoteListener;
import br.com.notes.ui.viewHolder.NoteViewHolder;


public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private final List<Note> noteList;
    private final NoteListener noteListener;

    public NoteAdapter(NoteListener noteListener, List<Note> noteList) {
        this.noteList = noteList;
        this.noteListener = noteListener;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_note_row, parent, false);


        return new NoteViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note note = noteList.get(position);
        holder.bindData(note, noteListener);

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void load(List<Note> noteList){
        this.noteList.clear();
        this.noteList.addAll(noteList);
        notifyDataSetChanged();

    }
}
