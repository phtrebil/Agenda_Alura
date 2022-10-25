package br.com.alura.asynctask;

import br.com.alura.DataBase.dao.AlunoDao;
import br.com.alura.DataBase.dao.TelefoneDao;
import br.com.alura.model.Aluno;
import br.com.alura.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {
    private final AlunoDao alunoDAO;
    private final Aluno aluno;
    private final Telefone telefone;
    private final Telefone celular;
    private final TelefoneDao telefoneDAO;

    public SalvaAlunoTask(AlunoDao alunoDAO, Aluno aluno, Telefone telefone, Telefone celular, TelefoneDao telefoneDAO, FinalizadoListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefone = telefone;
        this.celular = celular;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoId = alunoDAO.salva(aluno).intValue();
        vinculaAlunoComTelefone(alunoId, telefone, celular);
        telefoneDAO.salva(telefone, celular);
        return null;
    }
}
