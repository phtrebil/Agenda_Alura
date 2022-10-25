package br.com.alura.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.DataBase.dao.TelefoneDao;
import br.com.alura.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDao dao;
    private final TextView telefone;
    private final int alunoId;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDao dao, TextView telefone, int alunoId) {
        this.dao = dao;
        this.telefone = telefone;
        this.alunoId = alunoId;
    }


    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        telefone.setText(primeiroTelefone.getNumero());

    }
}
