package br.com.alura.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.R;
import br.com.alura.dao.alunoDAO;
import br.com.alura.model.Aluno;

public class Add_Aluno extends AppCompatActivity {

    public static final String TITULO = "Novo aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final alunoDAO dao = new alunoDAO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aluno);
        setTitle(TITULO);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button salvar = findViewById(R.id.salvar);
        salvar.setOnClickListener(view -> {
            Aluno Aluno1 = criaAluno();
            salvaAluno(Aluno1);

        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.nome);
        campoTelefone = findViewById(R.id.telefone);
        campoEmail = findViewById(R.id.email);
    }

    private void salvaAluno(Aluno Aluno1) {
        dao.salva(Aluno1);
        finish();
    }

    @NonNull
    private Aluno criaAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }
}