package br.com.notes.ui.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;

import br.com.notes.model.Note;
import br.com.notes.ui.listener.NoteListener;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView txtDate, txtRecord;
    CardView cardView;
    Context context;
    ImageView imgClose;


    public NoteViewHolder(@NonNull View itemView, Context context) {
        super(itemView);


        txtDate = itemView.findViewById(R.id.id_txt_date);
        txtRecord = itemView.findViewById(R.id.id_txt_record);
        cardView = itemView.findViewById(R.id.id_card_view);
        imgClose = itemView.findViewById(R.id.id_img_close);
        this.context = context;
    }

    public void bindData(Note note, NoteListener listener){

        txtDate.setText(note.getDate());
        txtRecord.setText(note.getRecord());

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                        .Builder(context)
                        .setTitle(R.string.titulo_remover)
                        .setMessage(R.string.mensagem_remover)
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDeleteClick(note);
                            }
                        })
                        .setNeutralButton(R.string.nao,null)
                        .show();
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickList(note);
            }
        });



    }
}
