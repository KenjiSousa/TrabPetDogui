package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.CachorroRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CachorroService {
    public Cachorro insert(Cachorro cachorro) throws SQLException, NegocioException {
        validate(cachorro);
        
        return new CachorroRepository().insert(cachorro);
    }

    public Cachorro edit(Cachorro cachorro) throws SQLException, NegocioException {
        validate(cachorro);
        validateUpdate(cachorro);
        
        return new CachorroRepository().update(cachorro);
    }

    public Cachorro findById(int id) throws SQLException {
        return new CachorroRepository().findById(id);
    }

    public ArrayList<Cachorro> findAll() throws SQLException {
        return new CachorroRepository().findAll();
    }

    private void validate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getNome() == null || cachorro.getNome().isBlank())
            throw new NegocioException("O nome do Cachorro deve ser Informado.");
        else if (cachorro.getNome().length() < 2)
            throw new NegocioException("O nome do Cachorro deve possuir 2 ou mais caracteres.");
        else if (cachorro.getNome().length() > 60)
            throw new NegocioException("O nome do Cachorro não deve possuir mais do que 60 caracteres.");
        else if (cachorro.getTamanho() == null)
            throw new NegocioException("O tamanho do Cachorro deve ser informado.");
        else if ((cachorro.getTamanho() < 0.01 || cachorro.getTamanho() > 0.99))
            throw new NegocioException("O tamanho do Cachorro deve estar entre 0,01 e 0,99.");
        else if (cachorro.getDtNascimento() == null)
            throw new NegocioException("A data de nascimento do Cachorro deve ser informada.");
        else if (cachorro.getDtNascimento().compareTo(new Date()) > 0)
            throw new NegocioException("A data de nascimento do Cachorro não deve ser futura.");
        else if (cachorro.getRaca() == null)
            throw new NegocioException("A raça do Cachorro deve ser informada.");
        else if (cachorro.getPelagem() == null)
            throw new NegocioException("A pelagem do Cachorro deve ser informada.");
        else if (cachorro.getCor() == null)
            throw new NegocioException("A cor do Cachorro deve ser informada.");
    }
    
    private void validateUpdate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getId() == 0)
            throw new NegocioException("Informe um Código Válido para atualização do cachorro");
    }
    
    public void delete(int id) throws SQLException {
        new CachorroRepository().delete(id);
    }
}
