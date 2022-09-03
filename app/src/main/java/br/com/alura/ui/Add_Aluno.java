package br.com.alura.ui;

import static br.com.alura.ui.contantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.R;
import br.com.alura.dao.alunoDAO;
import br.com.alura.model.Aluno;

public class Add_Aluno extends AppCompatActivity {

    private static final String TITULO_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final alunoDAO dao = new alunoDAO();
    private Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aluno);
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_addaluno_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampo();
        } else {
            setTitle(TITULO_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampo() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.nome);
        campoTelefone = findViewById(R.id.telefone);
        campoEmail = findViewById(R.id.email);
    }


    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);

    }
}