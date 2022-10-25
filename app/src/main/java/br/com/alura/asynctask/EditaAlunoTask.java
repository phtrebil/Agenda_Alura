package br.com.alura.asynctask;

import java.util.List;

import br.com.alura.DataBase.dao.AlunoDao;
import br.com.alura.DataBase.dao.TelefoneDao;
import br.com.alura.model.Aluno;
import br.com.alura.model.Telefone;
import br.com.alura.model.TipoTelefone;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {

    private final AlunoDao alunoDAO;
    private final Aluno aluno;
    private final Telefone telefone;
    private final Telefone celular;
    private final TelefoneDao telefoneDAO;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(AlunoDao alunoDAO,
                          Aluno aluno,
                          Telefone telefone,
                          Telefone celular,
                          TelefoneDao telefoneDAO, List<Telefone> telefonesDoAluno, FinalizadoListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefone = telefone;
        this.celular = celular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefone, celular);

        atualizaIdTelefone(telefone, celular);
        telefoneDAO.atualiza(telefone, celular);
        return null;
    }

    void atualizaIdTelefone(Telefone telefone, Telefone celular) {
        for (Telefone telefoneAluno :
                telefonesDoAluno){
            if(telefoneAluno.getTipo() == TipoTelefone.FIXO){
                telefone.setId(telefoneAluno.getId());
            }else{
                celular.setId(telefoneAluno.getId());
            }
        }
    }
}
