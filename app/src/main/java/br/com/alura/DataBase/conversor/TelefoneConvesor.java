package br.com.alura.DataBase.conversor;

import androidx.room.TypeConverter;

import br.com.alura.model.Telefone;
import br.com.alura.model.TipoTelefone;

public class TelefoneConvesor {

    @TypeConverter
    public String paraString(TipoTelefone tipo){
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone paraTipoTelefone (String valor){
        if(valor != null){
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }

}
