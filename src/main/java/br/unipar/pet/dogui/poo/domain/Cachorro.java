/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author andersonbosing
 */
@Getter
@Setter //Annotations 
@ToString // ToString
@AllArgsConstructor //Construtor com todos os argumentos
@NoArgsConstructor //Contrutor vazio
public class Cachorro {
    private int id;
    private String nome;
    private Raca raca;
    private Pelagem pelagem;
    private Cor cor;
    private Double tamanho;
    private boolean stPerfume;
    private Date dtNascimento;
}
