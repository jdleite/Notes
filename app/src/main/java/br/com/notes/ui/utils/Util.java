package br.com.notes.ui.utils;

import android.widget.TextView;

import java.time.Year;
import java.util.Calendar;

public class Util {
    public static String DIA = "dia";
    public static String MES = "mês";
    public static  String ANO = "ano";


    public String setDate() {
        Calendar calendar = Calendar.getInstance();

        DIA = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        MES = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        ANO = String.valueOf(calendar.get(Calendar.YEAR));


        return DIA + "/" + MES + "/" + ANO;
    }
}
