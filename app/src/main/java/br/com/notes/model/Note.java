package br.com.notes.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String record;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public boolean validateId(){

        return id > 0;
    }


}
