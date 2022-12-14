package br.com.alura.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.DataBase.AgendaDatabase;
import br.com.alura.DataBase.dao.TelefoneDao;
import br.com.alura.R;
import br.com.alura.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import br.com.alura.model.Aluno;

public class ListaDeAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDao dao;

    public ListaDeAlunosAdapter(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getTelefoneDao();

    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(i);
        vinculaInfo(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vinculaInfo(View viewCriada, Aluno aluno) {
        TextView alunoNome = viewCriada.findViewById(R.id.aluno_item_nome);
        alunoNome.setText(aluno.getNome());
        TextView alunoTelefone = viewCriada.findViewById(R. id.aluno_item_telefone);
        new BuscaPrimeiroTelefoneDoAlunoTask(dao, aluno.getId(), telefoneEncontrado->{
            alunoTelefone.setText(telefoneEncontrado.getNumero());
        }).execute();

    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }


    public void remove(Aluno aluno){
        alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualiza(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }
}

