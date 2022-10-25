package br.com.alura.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.DataBase.dao.AlunoDao;
import br.com.alura.model.Aluno;
import br.com.alura.ui.ListaDeAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private AlunoDao dao;
    private ListaDeAlunosAdapter adapter;

    public BuscaAlunosTask(AlunoDao dao, ListaDeAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        List<Aluno> todosAlunos = dao.todos();
        return todosAlunos;
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza((List<Aluno>) todosAlunos);
    }
}
