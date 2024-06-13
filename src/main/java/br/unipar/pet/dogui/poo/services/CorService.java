/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.CorRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author andersonbosing
 */
public class CorService {
    
    //Insert de uma nova cor
    public Cor insert(Cor cor) throws SQLException, NegocioException {
        validate(cor);
        CorRepository corRepository = new CorRepository();
        cor = corRepository.insert(cor);
        
        return cor;
    }
    
    //Update de cor
    public Cor edit(Cor cor) throws SQLException, NegocioException {
        validate(cor);
        CorRepository corRepository = new CorRepository();
        cor = corRepository.update(cor);
        
        return cor;
        
    }
    
    //Busca uma unica cor pela pk 
    public Cor findById(int id) throws SQLException {
        
        CorRepository corRepository = new CorRepository();
        Cor cor = corRepository.findById(id);
        
        return cor;
        
    }
    
    //Busca todas as Cores cadastradas no banco
    public ArrayList<Cor> findAll() throws SQLException {
        
        CorRepository corRepository = new CorRepository();
        ArrayList<Cor> resultado = corRepository.findAll();
        
        return resultado;
    }
    
    //Valida os atributos de Cor
    private void validate(Cor cor) throws NegocioException {
        if (cor.getDescricao() == null) {
            throw new NegocioException("A descrição da Cor deve ser Informada.");
        }
        if (cor.getDescricao().isBlank()) {
            throw new NegocioException("A descrição da Cor deve ser Informada.");
        }
        if (cor.getDescricao().length() < 4) {
            throw new NegocioException("A descrição da Cor deve possuir 4 "
                    + "ou mais caracteres.");
        }
        if (cor.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Cor não deve possuir "
                    + "mais do que 60 caracteres");
        }
    }
    
    public void delete(int id) throws SQLException {
        CorRepository corRepository = new CorRepository();
        corRepository.delete(id);
    }
    
}
