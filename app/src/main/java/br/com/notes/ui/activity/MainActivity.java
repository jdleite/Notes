package br.com.notes.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.notes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.notes.database.NoteDatabase;
import br.com.notes.database.dao.NoteDao;
import br.com.notes.model.Note;
import br.com.notes.ui.adapter.NoteAdapter;
import br.com.notes.ui.listener.NoteListener;

public class MainActivity extends AppCompatActivity implements TextDialogFragment.RefreshList {
    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<>();
    private NoteListener listener;
    private RecyclerView recyclerView;
    private NoteDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.id_recycler_view);
        NoteDatabase database = NoteDatabase.getInstance(this);
        dao = database.getNoteDatabase();

        fab();
        noteListener();


    }

    private void fab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {


            TextDialogFragment fragment = new TextDialogFragment();
            fragment.show(getSupportFragmentManager(), "note");


        });
    }


    private void getList() {

        adapter = new NoteAdapter(listener, noteList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.load(dao.listAll());


    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }

    @Override
    public void refresh() {

        onResume();

    }

    public void noteListener() {
        listener = new NoteListener() {
            @Override
            public void onClickList(Note note) {
                openFormEditMode(note);


            }

            @Override
            public void onDeleteClick(Note note) {
                dao.delete(note);
                refresh();


            }
        };
    }

    public void openFormEditMode(Note note) {


        Bundle bundle = new Bundle();
        bundle.putSerializable("note", note);
        TextDialogFragment fragment = new TextDialogFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().commit();
        fragment.show(getSupportFragmentManager(), "note");


    }


}