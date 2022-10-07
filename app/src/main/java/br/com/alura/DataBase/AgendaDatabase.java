package br.com.alura.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.DataBase.dao.RoomAlunoDao;
import br.com.alura.model.Aluno;

@Database(entities ={Aluno.class}, version = 2, exportSchema = false)

public abstract class AgendaDatabase extends RoomDatabase {

    public static final String DB = "agenda.db";

    public abstract RoomAlunoDao getRoomAlunoDao();

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, DB)
                .allowMainThreadQueries()
                .addMigrations(
                        new Migration(1, 2) {
                            @Override
                            public void migrate(@NonNull SupportSQLiteDatabase database) {
                                database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                            }
                        }
                )
                .build();
    }

}
