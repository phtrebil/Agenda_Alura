package br.com.alura.ui;

import static br.com.alura.ui.contantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.DataBase.AgendaDatabase;
import br.com.alura.DataBase.dao.AlunoDao;
import br.com.alura.DataBase.dao.TelefoneDao;
import br.com.alura.R;
import br.com.alura.model.Aluno;
import br.com.alura.model.Telefone;
import br.com.alura.model.TipoTelefone;

public class FormularioAluno extends AppCompatActivity {

    private static final String TITULO_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoCelular;
    private EditText campoEmail;
    private AlunoDao alunoDAO;
    private Aluno aluno;
    private TelefoneDao telefoneDAO;
    private List<Telefone> telefonesDoAluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aluno);
        inicializacaoDosCampos();
        AgendaDatabase database = AgendaDatabase.getInstance(this);
        alunoDAO = database.getRoomAlunoDao();
        telefoneDAO = database.getTelefoneDao();
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
        if (itemId == R.id.menu_salvar) {
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
        preencheCamposDeTelefone();
    }

    private void preencheCamposDeTelefone() {
        telefonesDoAluno = telefoneDAO
                .buscaTodosTelefonesDoAluno(aluno.getId());
        for (Telefone telefone :
                telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO){
                campoTelefone.setText(telefone.getNumero());
            } else {
                campoCelular.setText(telefone.getNumero());
            }
        }
    }

    private void finalizaFormulario() {
        preencheAluno();

        Telefone telefone = criaTelefone(campoTelefone, TipoTelefone.FIXO);
        Telefone celular = criaTelefone(campoCelular, TipoTelefone.CELULAR);

        if (aluno.temIdValido()) {
            editaAluno(telefone, celular);

        } else {
            salvaAluno(telefone, celular);
        }
        finish();
    }

    @NonNull
    private Telefone criaTelefone(EditText campoTelefone, TipoTelefone fixo) {
        String numeroTelefone = campoTelefone.getText().toString();
        Telefone telefone = new Telefone(numeroTelefone, fixo);
        return telefone;
    }

    private void salvaAluno(Telefone telefone, Telefone celular) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefone, celular);
        telefoneDAO.salva(telefone, celular);
    }

    private void editaAluno(Telefone telefone, Telefone celular) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefone, celular);

        atualizaIdTelefone(telefone, celular);
        telefoneDAO.atualiza(telefone, celular);
    }

    private void atualizaIdTelefone(Telefone telefone, Telefone celular) {
        for (Telefone telefoneAluno :
        telefonesDoAluno){
            if(telefoneAluno.getTipo() == TipoTelefone.FIXO){
                telefone.setId(telefoneAluno.getId());
            }else{
                celular.setId(telefoneAluno.getId());
            }
        }
    }

    private void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone :
                telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.nome);
        campoTelefone = findViewById(R.id.telefone);
        campoCelular = findViewById(R.id.celular);
        campoEmail = findViewById(R.id.email);
    }


    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String celular = campoCelular.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);

    }
}