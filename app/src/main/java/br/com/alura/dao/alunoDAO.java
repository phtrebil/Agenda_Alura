package br.com.alura.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.model.Aluno;

public class alunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    public void salva(Aluno aluno1) {
        alunos.add(aluno1);
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}
