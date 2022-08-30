package br.com.alura.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.R;
import br.com.alura.dao.alunoDAO;

public class ListaAlunoActivity extends AppCompatActivity {

    public static final String TITULO_LISTA = "Lista de Alunos";
    private final alunoDAO dao = new alunoDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_LISTA);
        View fab = findViewById(R.id.fab_add);
        configuraFab(fab);

    }

    private void configuraFab(View fab) {
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
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                dao.todos()));
    }
}