package br.com.notes.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;

import br.com.notes.database.NoteDatabase;
import br.com.notes.database.dao.NoteDao;
import br.com.notes.model.Note;
import br.com.notes.ui.utils.Util;
import br.com.notes.validator.StandardValidator;
import br.com.notes.validator.ValidatorNote;

public class TextDialogFragment extends DialogFragment {

    private StartField startField = new StartField();
    private NoteDao noteDao;
    private Note note;
    private Context context;
    private Util util = new Util();
    private View view;
    private RefreshList refreshList;
    private final List<StandardValidator> validatorList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.activity_form_fragment, container, false);


        context = getActivity().getApplicationContext();
        NoteDatabase noteDatabase = NoteDatabase.getInstance(context);
        noteDao = noteDatabase.getNoteDatabase();



        noteField();
        loadNote();
        btnSave();
        btnCancel();


        return view;
    }

    private class StartField {
        EditText edtNote;
        Button btnSave, btnCancel;
    }

    private void noteField() {
        startField.edtNote = view.findViewById(R.id.id_edt_notes);
        AddStandadValidation(startField.edtNote);

    }





    private void loadNote() {


        Bundle bundle = getArguments();

        if (bundle != null) {
            note = (Note) this.getArguments().getSerializable("note");
            fillFields();
        } else {

            note = new Note();
        }


    }

    private void fillFields() {
        startField.edtNote.setText(note.getRecord());
    }

    private void btnSave() {
        startField.btnSave = view.findViewById(R.id.id_btn_save);

        startField.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValidForm = ValidFilld();
                if (isValidForm){
                    finishForm();
                    refreshList.refresh();
                    getDialog().dismiss();
                }



            }
        });

    }

    public interface RefreshList {
        void refresh();

    }

    private void btnCancel() {
        startField.btnCancel = view.findViewById(R.id.id_btn_cancel);
        startField.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void finishForm() {
        setNote();
        if (note.validateId()) {
            noteDao.update(note);
        } else {
            noteDao.save(note);
        }

    }

    private void setNote() {
        String record = startField.edtNote.getText().toString();
        note.setRecord(record);

        note.setDate(util.setDate());
    }

    private void AddStandadValidation(EditText editText) {
        StandardValidator validator = new StandardValidator(editText);
        validatorList.add(validator);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });
    }

    private boolean ValidFilld() {

        for (ValidatorNote validatorNote : validatorList) {

            if (!validatorNote.isValid()) {
                return false;
            }
        }
        return true;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

            refreshList = (RefreshList) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " deve ser implementado AtualizaListener");
        }
    }


}
