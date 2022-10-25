package br.com.alura.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.model.Telefone;
import br.com.alura.model.TipoTelefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadoListener listener;

    protected BaseAlunoComTelefoneTask(FinalizadoListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.quandoFinalizada();
    }

    void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone :
                telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    public interface FinalizadoListener{
        void quandoFinalizada();
    }

}
