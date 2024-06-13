package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Pelagem;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.PelagemRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class PelagemService {
    public Pelagem insert(Pelagem pelagem) throws SQLException, NegocioException {
        validate(pelagem);

        return new PelagemRepository().insert(pelagem);
    }

    public Pelagem edit(Pelagem pelagem) throws SQLException, NegocioException {
        validate(pelagem);
        validateUpdate(pelagem);
        
        PelagemRepository pelagemRepository = new PelagemRepository();
        return pelagemRepository.update(pelagem);
    }

    public Pelagem findById(int id) throws SQLException {
        return new PelagemRepository().findById(id);
    }

    public ArrayList<Pelagem> findAll() throws SQLException {
        return new PelagemRepository().findAll();
    }

    private void validate(Pelagem pelagem) throws NegocioException {
        if (pelagem.getDescricao() == null || pelagem.getDescricao().isBlank())
            throw new NegocioException("A descrição da Pelagem deve ser Informada.");
        else if (pelagem.getDescricao().length() < 4)
            throw new NegocioException("A descrição da Pelagem deve possuir 4 ou mais caracteres.");
        else if (pelagem.getDescricao().length() > 60)
            throw new NegocioException("A descrição da Pelagem não deve possuir mais do que 60 caracteres");
    }
    
    private void validateUpdate(Pelagem pelagem) throws NegocioException {
        if (pelagem.getId() == 0) {
            throw new NegocioException("Informe um Código Válido para atualização da pelagem");
        }
    }
    
    public void delete(int id) throws SQLException {
        new PelagemRepository().delete(id);
    }
}
