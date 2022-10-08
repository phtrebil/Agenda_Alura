package br.com.alura.DataBase;

import static br.com.alura.DataBase.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.DataBase.conversor.Conversor;
import br.com.alura.DataBase.dao.AlunoDao;
import br.com.alura.model.Aluno;

@Database(entities ={Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({Conversor.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String DB = "agenda.db";


    public abstract AlunoDao getRoomAlunoDao();

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, DB)
                .allowMainThreadQueries()
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }

}
