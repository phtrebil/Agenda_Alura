package br.com.alura.DataBase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.alura.model.Aluno;

@Dao
public interface RoomAlunoDao {

    @Insert
    void salva(Aluno aluno);

    @Query("SELECT * FROM Aluno")
    List<Aluno> todos();

    @Delete
    void remove(Aluno aluno);
}
