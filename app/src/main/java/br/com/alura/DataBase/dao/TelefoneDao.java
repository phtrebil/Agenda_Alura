package br.com.alura.DataBase.dao;

import androidx.room.Dao;
import androidx.room.Query;

import br.com.alura.model.Telefone;

@Dao
public interface TelefoneDao {

    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);


}
