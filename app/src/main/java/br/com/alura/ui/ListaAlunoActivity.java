package br.com.alura.ui;

import static br.com.alura.ui.contantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.R;
import br.com.alura.dao.alunoDAO;
import br.com.alura.model.Aluno;

public class ListaAlunoActivity extends AppCompatActivity {

    public static final String TITULO_LISTA = "Lista de Alunos";
    private final alunoDAO dao = new alunoDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_LISTA);
        View fab = findViewById(R.id.fab_add);
        configuraFabInsereAluno(fab);
        dao.salva(new Aluno("Pedro", "123544567", "phtrebil@gmail.com"));
        dao.salva(new Aluno("Julia","123354864", "julialal@fka,a.kasl."));

    }

    private void configuraFabInsereAluno(View fab) {
        fab.setOnClickListener((view -> {
            startActivity(new Intent(this, Add_Aluno.class));
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista(dao);
    }

    private void configuraLista(alunoDAO dao) {
        ListView listaDeAlunos = findViewById(R.id.lista);
        final  List<Aluno> alunos = dao.todos();
        configuraAdapter(listaDeAlunos, alunos);
        configuraListenerDeClickPorItem(listaDeAlunos);
    }

    private void configuraListenerDeClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(alunoEscolhido);
                Log.i("aluno", "" + posicao);

            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunoActivity.this, Add_Aluno.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDeAlunos, List<Aluno> alunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                alunos));
    }
}