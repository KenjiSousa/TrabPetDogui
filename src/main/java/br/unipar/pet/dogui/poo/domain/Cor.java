/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.domain;

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
public class Cor {
    private int id;
    private String descricao;
}
