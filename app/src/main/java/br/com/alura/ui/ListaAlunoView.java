package br.com.alura.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import br.com.alura.DataBase.AgendaDatabase;
import br.com.alura.DataBase.dao.AlunoDao;
import br.com.alura.asynctask.BuscaAlunosTask;
import br.com.alura.asynctask.RemoveAlunoTask;
import br.com.alura.model.Aluno;

public class ListaAlunoView {

    private final ListaDeAlunosAdapter adapter;
    private final AlunoDao dao;
    private final Context context;

    public ListaAlunoView(Context context) {
        this.context = context;
        this.adapter = new ListaDeAlunosAdapter(this.context);
        dao = AgendaDatabase.getInstance(this.context).getRoomAlunoDao();

    }

    public void dialogoConfirmaRemocao(@NonNull MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que deseja remover o aluno?")
                .setPositiveButton("sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Aluno alunoEscolhido = (Aluno) adapter.getItem(menuInfo.position);
                    remove(alunoEscolhido);
                })
                .setNegativeButton("não", null)
                .show();
    }

    public void atualizaAlunos() {
        new BuscaAlunosTask(dao, adapter).execute();
    }

    public void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();

    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
