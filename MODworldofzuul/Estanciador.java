/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package rpgcorp.zuul;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author renan
 */
public class Estanciador {
    ArrayList<Ambiente> listaRNG;
    String tesouroLocation;
    String chaveLocation;
    
    public Estanciador(){
        listaRNG= new ArrayList<Ambiente>();
    }
    public void add(Ambiente e){
        listaRNG.add(e);
    }
    public void plantarTesouro(){
        Collections.shuffle(listaRNG);
        Ambiente amb= listaRNG.get(0);
        amb.colocarTesouro();
        tesouroLocation = amb.getDescricao();
    }
     public void plantarChave(){
        Collections.shuffle(listaRNG);
        Ambiente amb= listaRNG.get(0);
        amb.colocarChave();
        chaveLocation= amb.getDescricao();
    }
     public String kdTesouro(){
         return tesouroLocation;
     }
     public String kdChave(){
         return chaveLocation;
     }
}


