package br.com.alura.DataBase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.alura.model.Telefone;

@Dao
public interface TelefoneDao {

    @Query("SELECT * FROM Telefone " +
            "JOIN aluno a " +
            "WHERE alunoId = :alunoId " +
            "LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId")
    List<Telefone> buscaTodosTelefonesDoAluno(int alunoId);

    @Insert
    void salva(Telefone... telefones);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
