package br.com.alura.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.DataBase.dao.TelefoneDao;
import br.com.alura.model.Aluno;
import br.com.alura.model.Telefone;

public class BuscaTodosOsTelefonesDoAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDao telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontradoListener listener;

    public BuscaTodosOsTelefonesDoAlunoTask(TelefoneDao telefoneDAO, Aluno aluno, TelefonesDoAlunoEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoTelefoneEncontrado(telefones);
    }

    public interface TelefonesDoAlunoEncontradoListener {
        void quandoTelefoneEncontrado(List<Telefone> telefones);
    }
}
