package br.com.alura.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.model.Aluno;

public class alunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno1) {
        aluno1.setId(contadorDeIds);
        alunos.add(aluno1);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }


    public void edita(Aluno aluno) {
        Aluno alunoEcontrado = buscaAlunoPeloId(aluno);
        if (alunoEcontrado != null) {
            int posicaoDoAluno = alunos.indexOf(alunoEcontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    @Nullable
    private Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }
}
