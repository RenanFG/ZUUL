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
    Ambiente geradorDicas;
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
        tesouroLocation = amb.toString();
        geradorDicas= amb;
    }
     public void plantarChave(){
        Collections.shuffle(listaRNG);
        Ambiente amb= listaRNG.get(0);
        amb.colocarChave();
        chaveLocation= amb.toString();
    }
     public String kdTesouro(){
         return tesouroLocation;
     }
     public String kdChave(){
         return chaveLocation;
     }
     public String gerarDicas(){
        ArrayList <String> l = new ArrayList<>();
        geradorDicas.ambVizinho(l);
        Collections.shuffle(l);
        return l.get(0);
     }
     public void imprimirVizinhos(){ // funcao teste, deleta depois por favor
         ArrayList <String> l = new ArrayList<>();
        geradorDicas.ambVizinho(l);
        l.stream().forEach((s) -> {
            System.out.println(s);
        });
     }
}


