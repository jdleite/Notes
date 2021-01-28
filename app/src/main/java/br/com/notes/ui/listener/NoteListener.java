package br.com.notes.ui.listener;

import br.com.notes.model.Note;

public interface NoteListener {

    void onClickList(Note note);

    void onDeleteClick(Note note);
}
