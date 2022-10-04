package br.com.alura.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.room.Room;

import br.com.alura.DataBase.DataBase;
import br.com.alura.DataBase.dao.RoomAlunoDao;
import br.com.alura.dao.alunoDAO;
import br.com.alura.model.Aluno;

public class ListaAlunoView {

    private final ListaDeAlunosAdapter adapter;
    private final RoomAlunoDao dao;
    private final Context context;

    public ListaAlunoView(Context context) {
        this.context = context;
        this.adapter = new ListaDeAlunosAdapter(this.context);
        dao = Room.databaseBuilder(context, DataBase.class, "agenda.db").allowMainThreadQueries().build().getRoomAlunoDao();

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
        adapter.atualiza(dao.todos());
    }

    public void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
