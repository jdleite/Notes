package br.com.notes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.notes.database.dao.NoteDao;
import br.com.notes.model.Note;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "test";


    public abstract NoteDao getNoteDatabase();

    public static NoteDatabase getInstance(Context context){

        return Room.databaseBuilder(context,NoteDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

    }





}
