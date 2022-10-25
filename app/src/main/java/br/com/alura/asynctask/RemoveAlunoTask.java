package br.com.alura.asynctask;

import android.os.AsyncTask;
import br.com.alura.DataBase.dao.AlunoDao;
import br.com.alura.model.Aluno;
import br.com.alura.ui.ListaDeAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void>{

    private final AlunoDao dao;
    private final ListaDeAlunosAdapter adapter;
    private final Aluno aluno;

    public RemoveAlunoTask(AlunoDao dao, ListaDeAlunosAdapter adapter, Aluno aluno) {
        this.dao = dao;
        this.adapter = adapter;
        this.aluno = aluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        adapter.remove(aluno);
    }
}
