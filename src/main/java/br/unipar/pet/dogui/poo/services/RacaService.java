package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.RacaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class RacaService {
    public Raca insert(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        
        return new RacaRepository().insert(raca);
    }

    public Raca edit(Raca raca) throws SQLException, NegocioException {
        validate(raca);
        validateUpdate(raca);
        
        return new RacaRepository().update(raca);
    }

    public Raca findById(int id) throws SQLException {
        return new RacaRepository().findById(id);
    }

    public ArrayList<Raca> findAll() throws SQLException {
        return new RacaRepository().findAll();
    }
    
    //Valida os atributos de Cor
    private void validate(Raca raca) throws NegocioException {
        if (raca.getDescricao() == null || raca.getDescricao().isBlank())
            throw new NegocioException("A descrição da Raca deve ser Informada.");
        else if (raca.getDescricao().length() < 3)
            throw new NegocioException("A descrição da Raca deve possuir 3 ou mais caracteres.");
        else if (raca.getDescricao().length() > 60)
            throw new NegocioException("A descrição da Raca não deve possuir mais do que 60 caracteres");
    }
    
    private void validateUpdate(Raca raca) throws NegocioException {
        if (raca.getId() == 0) {
            throw new NegocioException("Informe um Código Válido para atualização da raca");
        }
    }
    
    public void delete(int id) throws SQLException {
        new RacaRepository().delete(id);
    }
}
