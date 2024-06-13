/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.pet.dogui.poo;

import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.domain.Pelagem;
import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.services.CachorroService;
import br.unipar.pet.dogui.poo.services.CorService;
import br.unipar.pet.dogui.poo.services.PelagemService;
import br.unipar.pet.dogui.poo.services.RacaService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author andersonbosing
 */
public class PetDoguiPoo {
    public static void main(String[] args) {
        try {
            Cor cor = insereCor();
            Pelagem pelagem = inserePelagem();
            Raca raca = insereRaca();
            Cachorro cachorro = insereCachorro(cor, pelagem, raca);
            deleteCachorro(cachorro);
            deleteRaca(raca);
            deletePelagem(pelagem);
            deleteCor(cor);
        } catch (SQLException ex) {
            System.out.println("Ops, algo deu errado com o banco de dados\n"+ex.getMessage());
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Ops, algo deu errado, contate o suporte\n"+ex.getMessage());
        }
    }

    private static Cor insereCor() throws SQLException, NegocioException {
        CorService corService = new CorService();

        ArrayList<Cor> resultado = corService.findAll();
        System.out.println("Antes da inserção em \"Cor\": " + resultado);

        Cor cor = new Cor();
        cor.setDescricao("Laranja");
        cor = corService.insert(cor);
        resultado = corService.findAll();
        System.out.println("Após inserção em \"Cor\": " + resultado);

        return cor;
    }

    private static Pelagem inserePelagem() throws SQLException, NegocioException {
        PelagemService pelagemService = new PelagemService();

        ArrayList<Pelagem> resultado = pelagemService.findAll();
        System.out.println("Antes da inserção em \"Pelagem\": " + resultado);

        Pelagem pelagem = new Pelagem();
        pelagem.setDescricao("Liso");
        pelagem = pelagemService.insert(pelagem);
        resultado = pelagemService.findAll();
        System.out.println("Após inserção em \"Pelagem\": " + resultado);

        return pelagem;
    }

    private static Raca insereRaca() throws SQLException, NegocioException {
        RacaService racaService = new RacaService();

        ArrayList<Raca> resultado = racaService.findAll();
        System.out.println("Antes da inserção em \"Raça\": " + resultado);

        Raca raca = new Raca();
        raca.setDescricao("Doberman");
        raca = racaService.insert(raca);
        resultado = racaService.findAll();
        System.out.println("Após inserção em \"Raça\": " + resultado);

        return raca;
    }

    private static Cachorro insereCachorro(Cor cor, Pelagem pelagem, Raca raca) throws SQLException, NegocioException {
        CachorroService cachorroService = new CachorroService();

        ArrayList<Cachorro> resultado = cachorroService.findAll();
        System.out.println("Antes da inserção em \"Cachorro\": " + resultado);

        Cachorro cachorro = new Cachorro();
        cachorro.setNome("Pingo");
        cachorro.setTamanho(.5);
        cachorro.setStPerfume(true);

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(2017, Calendar.JANUARY, 13);
        cal.getTime();
        cachorro.setDtNascimento(cal.getTime());

        cachorro.setRaca(raca);
        cachorro.setPelagem(pelagem);
        cachorro.setCor(cor);
        cachorro = cachorroService.insert(cachorro);
        resultado = cachorroService.findAll();
        System.out.println("Após inserção em \"Cachorro\": " + resultado);

        return cachorro;
    }

    private static void deleteCachorro(Cachorro cachorro) throws SQLException {
        CachorroService cachorroService = new CachorroService();
        cachorroService.delete(cachorro.getId());
        ArrayList<Cachorro> resultado = cachorroService.findAll();
        System.out.println("Após a deleção em \"Cachorro\": " + resultado);
    }

    private static void deleteRaca(Raca raca) throws SQLException {
        RacaService racaService = new RacaService();
        racaService.delete(raca.getId());
        ArrayList<Raca> resultado = racaService.findAll();
        System.out.println("Após a deleção em \"Raça\": " + resultado);
    }

    private static void deletePelagem(Pelagem pelagem) throws SQLException {
        PelagemService pelagemService = new PelagemService();
        pelagemService.delete(pelagem.getId());
        ArrayList<Pelagem> resultado = pelagemService.findAll();
        System.out.println("Após a deleção em \"Pelagem\": " + resultado);
    }

    private static void deleteCor(Cor cor) throws SQLException {
        CorService corService = new CorService();
        corService.delete(cor.getId());
        ArrayList<Cor> resultado = corService.findAll();
        System.out.println("Após a deleção em \"Cor\": " + resultado);
    }
}
