package br.com.alura;

import android.app.Application;

import androidx.room.Room;

import br.com.alura.DataBase.DataBase;
import br.com.alura.DataBase.dao.RoomAlunoDao;
import br.com.alura.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoTeste();
    }

    private void criaAlunoTeste() {
        DataBase dataBase = Room.databaseBuilder(this, DataBase.class, "agenda.db").allowMainThreadQueries()
                .build();
        RoomAlunoDao dao = dataBase.getRoomAlunoDao();
        dao.salva(new Aluno("Pedro", "123544567", "phtrebil@gmail.com"));
        dao.salva(new Aluno("Julia","123354864", "julialal@fka,a.kasl."));
    }
}
