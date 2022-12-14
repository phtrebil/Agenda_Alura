package br.com.alura.ui;

import static br.com.alura.ui.contantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.R;
import br.com.alura.model.Aluno;

public class ListaAlunoActivity extends AppCompatActivity {

    public static final String TITULO_LISTA = "Lista de Alunos";
    private ListaAlunoView listaAlunoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        listaAlunoView = new ListaAlunoView(this);
        setTitle(TITULO_LISTA);
        View fab = findViewById(R.id.fab_add);
        configuraFabInsereAluno(fab);
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_listaalunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menu_remover) {
            listaAlunoView.dialogoConfirmaRemocao(item);

        }
        return super.onContextItemSelected(item);
    }



    private void configuraFabInsereAluno(View fab) {
        fab.setOnClickListener((view -> startActivity(new Intent(this, FormularioAlunoActivity.class))));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunoView.atualizaAlunos();
    }



    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.lista);
        listaAlunoView.configuraAdapter(listaDeAlunos);
        configuraListenerDeClickPorItem(listaDeAlunos);
        registerForContextMenu(listaDeAlunos);
    }



    private void configuraListenerDeClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaAluno(alunoEscolhido);
            Log.i("aluno", "" + posicao);

        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunoActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormularioActivity);
    }


}