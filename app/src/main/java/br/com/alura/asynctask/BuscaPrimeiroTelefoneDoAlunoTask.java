package br.com.alura.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.DataBase.dao.TelefoneDao;
import br.com.alura.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDao dao;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDao dao,
                                            int alunoId,
                                            PrimeiroTelefoneEncontradoListener listener) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);

    }
    public interface PrimeiroTelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}



