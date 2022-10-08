package br.com.alura.DataBase.conversor;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class Conversor {

    @TypeConverter
    public Long toLong(Calendar valor){
        if(valor != null){
        return valor.getTimeInMillis();
        }
        return null;
    }
    @TypeConverter
    public Calendar toCalendar(Long valor){
        Calendar momentoAtual = Calendar.getInstance();
        if (valor != null){
            momentoAtual.setTimeInMillis(valor);

        }
        return momentoAtual;
    }
}
