package br.com.notes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.notes.model.Note;

@Dao
public interface NoteDao {

    @Insert
    Long save(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM note ORDER BY ID DESC")
    List<Note> listAll();



}
