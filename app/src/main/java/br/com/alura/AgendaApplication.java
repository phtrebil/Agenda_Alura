package br.com.alura;

import android.app.Application;

import br.com.alura.dao.alunoDAO;
import br.com.alura.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoTeste();
    }

    private void criaAlunoTeste() {
        alunoDAO dao = new alunoDAO();
        dao.salva(new Aluno("Pedro", "123544567", "phtrebil@gmail.com"));
        dao.salva(new Aluno("Julia","123354864", "julialal@fka,a.kasl."));
    }
}
