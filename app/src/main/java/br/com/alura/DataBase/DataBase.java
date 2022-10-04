package br.com.alura.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import br.com.alura.DataBase.dao.RoomAlunoDao;
import br.com.alura.model.Aluno;

@Database(entities ={Aluno.class}, version = 1, exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract RoomAlunoDao getRoomAlunoDao();
}
